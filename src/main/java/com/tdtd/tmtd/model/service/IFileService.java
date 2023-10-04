package com.tdtd.tmtd.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.tdtd.tmtd.vo.FileVo;

/**
 * 파일 관련 Service
 * 
 * @author 문희애
 *
 */
public interface IFileService {

	/**
	 * 파일 업로드시 DB 저장
	 */
	public int insertFile(Map<String, Object> map);

	/**
	 * 파일 정보 조회
	 */
	public FileVo getFile(String fileRekPk);

	/**
	 * 파일을 서버에 업로드
	 * 
	 * @return uploadInfo - originalName 원본파일명 / saveName 저장파일명 / path 저장경로 /
	 *         uploadDate 저장일
	 */
	public Map<String, Object> fileSave(MultipartFile file, HttpServletRequest request) throws IOException;

	/**
	 * 서버에 있는 파일 다운로드
	 * 
	 * @return File
	 */
	public File fileDownload(HttpServletRequest request, String saveFileName) throws IOException;

	/**
	 * Google OCR 실행 전 PDF를 PNG로 변환
	 * 
	 * @return convertedFileNames 변환된 PNG 파일 이름 리스트
	 */
	public List<String> convertPdfToPng(String pdfFilePath, String outputDir) throws Exception;

	/**
	 * Google OCR 실행 메소드
	 * 
	 * @return extractedTextMap 추출된 문자열 (key: careerVo 필드명)
	 */
	public Map<String, Object> extractTextFromAreas(String fileName);

	/**
	 * Google OCR 실행시 PNG파일의 내용을 byte로 변환하는 메소드
	 * 
	 * @param img PNG 파일
	 * @return 변환된 byteArray
	 */
	public void deletePngFiles(List<String> pngFileNames, String directory);

}
