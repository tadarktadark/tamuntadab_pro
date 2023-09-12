package com.tdtd.tmtd.model.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

	 public Map<String, Object> fileSave(MultipartFile file, HttpServletRequest request) throws IOException;
	 
	 public File fileDownload(HttpServletRequest request, String saveFileName) throws IOException;
	 
	 public List<String> convertPdfToPng(String pdfFilePath, String outputDir) throws Exception;
	 
	 public Map<String,Object> extractTextFromAreas(String fileName);
	 
	 public void deletePngFiles(List<String> pngFileNames, String directory);
	
}
