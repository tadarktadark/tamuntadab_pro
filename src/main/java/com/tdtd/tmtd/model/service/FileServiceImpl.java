package com.tdtd.tmtd.model.service;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;
import com.tdtd.tmtd.model.mapper.IFileDao;
import com.tdtd.tmtd.vo.FileVo;

/**
 * 파일 관련 Service
 * @author 문희애
 *
 */
@Service
public class FileServiceImpl implements IFileService {
	
	@Autowired
	private IFileDao dao;

	//Google OCR 실행시 추출 범위 지정
	private static final Map<String, Rectangle> areas = new HashMap<>();
    
    static {
        areas.put("careName", new Rectangle(714, 534, 466, 104));
        areas.put("careContact", new Rectangle(1600, 534, 466, 104));
        areas.put("careSosok", new Rectangle(714, 654 ,466 ,104));
        areas.put("carePosition", new Rectangle(1600 ,654 ,466 ,104));
        areas.put("carePeriod", new Rectangle(714 ,773 ,1462 ,117));
        areas.put("careJob", new Rectangle(714 ,894 ,1462 ,117));
        areas.put("careIssuer", new Rectangle(714 ,1014 ,466 ,104));
        areas.put("careIssuerContact", new Rectangle(1600, 1014, 466, 104));
        areas.put("careDate", new Rectangle(927 ,2419 ,639 ,82));
        areas.put("careCompany", new Rectangle(576,2830,624,70));   
    }
	
    /**
	 * 파일 업로드시 DB 저장
	 */
    @Override
    public int insertFile(Map<String, Object> map) {
    	return dao.insertFile(map);
    }
    
    /**
	 * 파일 정보 조회
	 */
    @Override
    public FileVo getFile(String fileRekPk) {
    	return dao.getFile(fileRekPk);
    }
    
    /**
     * 파일을 서버에 업로드
     * @return uploadInfo - originalName 원본파일명 / saveName 저장파일명 / path 저장경로 / uploadDate 저장일
     */
	@SuppressWarnings("resource")
	public Map<String, Object> fileSave(MultipartFile file, HttpServletRequest request) throws IOException {
	        String originalFileName = file.getOriginalFilename();
	        String saveFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));

	        InputStream inputStream = null;
	        OutputStream outputStream = null;

	        //1) 파일을 읽는다
	        inputStream = file.getInputStream();

	        //2) 저장 위치를 만든다
	        String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/storage");//상대경로

	        //3) 저장 위치가 없으면 만든다
	        File storage = new File(path);
	        if(!storage.exists()) {
	            storage.mkdir();
	        }

	       //4) 저장할 파일이 기존에 없다면 만들어주고 아니면 오버라이드 함
	       File newFile = new File(path+"/"+saveFileName);
	       if(!newFile.exists()) {
	           newFile.createNewFile();
	       }

	      //5) 파일의 쓸 위치를 지정해줌
	      outputStream = new FileOutputStream(newFile);

	      //6) 파일을 대상에 읽고 써줌
	      int read = 0;
	      byte[] b = new byte[(int)file.getSize()];
	      while ((read=inputStream.read(b))!= -1) {
	          outputStream.write(b,0,read);
	      }
	    
	      Map<String, Object> uploadInfo = new HashMap<>();
	      uploadInfo.put("originalName", originalFileName);
	      uploadInfo.put("saveName", saveFileName);
	      uploadInfo.put("path", path);
	      uploadInfo.put("uploadDate", LocalDateTime.now());
	    
	     return uploadInfo;  
	    }
	
		/**
		 * 서버에 있는 파일 다운로드
		 * @return File
		 */
		public File fileDownload(HttpServletRequest request, String saveFileName) throws IOException {
			// 파일 다운로드를 위한 물리적인 주소(가상으로 배포된 물리적인 주소)
			String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/storage");

			// 물리적인 주소에서 선택한 파일을 File객체로 만듦
			File file = new File(path + "/" + saveFileName);
			System.out.println("file 경로 :" + file.toString());

			if (!file.exists()) {
				throw new IOException("File not found: " + saveFileName);
			}

			return file;
		}

		/**
		 * Google OCR 실행 전 PDF를 PNG로 변환
		 * @return convertedFileNames 변환된 PNG 파일 이름 리스트
		 */
	    public List<String> convertPdfToPng(String pdfFilePath, String outputDir) throws Exception{
	    	File pdfFile = new File(pdfFilePath);
		    PDDocument document = PDDocument.load(pdfFile);

		    PDFRenderer pdfRenderer = new PDFRenderer(document);
		    List<String> convertedFileNames = new ArrayList<String>();

		    for (int page = 0; page < document.getNumberOfPages(); ++page) {
		        BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300); // 300 DPI로 변환 (해상도 조정 가능)

		        String uuid = UUID.randomUUID().toString(); // UUID 생성
		        String outputFileName = uuid + "_page_" + page + ".png"; // 파일명에 UUID 포함
		        
		        File outputFile = new File(outputDir + outputFileName);
		        Imaging.writeImage(bim, outputFile, ImageFormats.PNG, null);
		        convertedFileNames.add(outputFileName);
		    }

		    System.out.println("PDF 파일이 성공적으로 PNG 파일로 변환되었습니다.");

		    document.close();
		    return convertedFileNames;
	    }

	    /**
	     * Google OCR 실행 메소드
	     * @return extractedTextMap 추출된 문자열 (key: careerVo 필드명)
	     */
	    public Map<String,Object> extractTextFromAreas(String fileName){
	    	Map<String,Object> extractedTextMap = new HashMap<>();
	    	
	    	try (ImageAnnotatorClient client= ImageAnnotatorClient.create()) {
	            for (Map.Entry<String,Rectangle> entry : areas.entrySet()) {
	                String areaName = entry.getKey();
	                Rectangle rect = entry.getValue();

	                BufferedImage img = ImageIO.read(new File(fileName));

	                // 이미지를 잘라냄
	                BufferedImage croppedImg = img.getSubimage(rect.x,
	                                                           rect.y,
	                                                           rect.width,
	                                                           rect.height);

	                ByteString imgBytes = ByteString.copyFrom(toByteArray(croppedImg));

	                List<AnnotateImageRequest> requests= new ArrayList<>();
	                Image inputImage=Image.newBuilder().setContent(imgBytes).build();

	                Feature desiredFeature=Feature.newBuilder().setType(
	                		Feature.Type.DOCUMENT_TEXT_DETECTION).build();

	                 AnnotateImageRequest request=AnnotateImageRequest.newBuilder()
	                          .addFeatures(desiredFeature)
	                          .setImage(inputImage)
	                          .build();
	                          
	                   requests.add(request);

	                   BatchAnnotateImagesResponse response=
	                		   client.batchAnnotateImages(requests);
	                   
	                   List<AnnotateImageResponse> responses=response.getResponsesList();

	                   for (AnnotateImageResponse res : responses) {
	                       if (res.hasError()) {
	                           System.out.printf(
	                        		   "오류: %s%n",
	                        		   res.getError().getMessage());
	                           return extractedTextMap;  
	                       }
	                       String extractedText = res.getFullTextAnnotation().getText();
	                       
	                       extractedText = extractedText.replace("\n", " ");
	                       
	                       extractedTextMap.put(areaName, extractedText);
	                   }
	              }
	              
	              client.close();
	              
	           } catch (Exception e) {
	               e.printStackTrace();
	           }
	    	
	    	return extractedTextMap;
	     }
	    
	    /**
	     * Google OCR 실행시 PNG파일의 내용을 byte로 변환하는 메소드
	     * @param img PNG 파일
	     * @return 변환된 byteArray
	     */
	    private static byte[] toByteArray(BufferedImage img) throws IOException {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(img, "png", baos);
	        return baos.toByteArray();
	    }
	    
	    /**
	     * Google OCR 실행 후 PNG 파일 삭제 메소드
	     */
	    public void deletePngFiles(List<String> pngFileNames, String directory) {
	        for (String pngFileName : pngFileNames) {
	            File pngFileToDelete = new File(directory + "/" + pngFileName);
	            if(pngFileToDelete.delete()) {
	                System.out.println(pngFileName + " 파일이 성공적으로 삭제되었습니다.");
	            } else {
	                System.out.println(pngFileName + " 파일 삭제 실패.");
	            }
	        }
	    }
	
	
}
