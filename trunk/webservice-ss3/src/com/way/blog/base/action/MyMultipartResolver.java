package com.way.blog.base.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MyMultipartResolver extends CommonsMultipartResolver {
	public MultipartParsingResult parseRequest(HttpServletRequest request)
	throws MultipartException{
	String encoding = "UTF-8";
	FileUpload fileUpload = prepareFileUpload(encoding);
	final HttpSession session = request.getSession();
	fileUpload.setProgressListener(new ProgressListener(){

		public void update(long pBytesRead, long pContentLength, int pItems) {
			int percent = (int)(((float)pBytesRead/(float)pContentLength)*100);
			session.setAttribute("percent", percent+"%");
		}
		
	});
	List<FileItem> fileItems = null;
	try {
		fileItems = ((ServletFileUpload)fileUpload).parseRequest(request);
	} catch (FileUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return parseFileItems(fileItems, encoding);
}
}
