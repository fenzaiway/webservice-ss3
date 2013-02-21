package demo1;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItemFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class A {
	
	/**
	 * Servlet带进度条文件上传
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
	final HttpSession session  = request.getSession();
	FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setProgressListener(new ProgressListener(){

		public void update(long pBytesRead, long pContentLength, int pItems) {
			int percent = (int)(((float)pBytesRead/(float)pContentLength)*100);
			session.setAttribute("percent", percent+"%");
		}
		
	});
	upload.setHeaderEncoding("UTF-8");
	try {
		List<FileItem> items = upload.parseRequest(request);
		for(FileItem fileItem : items){
			if(!fileItem.isFormField()){
				String fileName = fileItem.getName();
				FileOutputStream fos = new FileOutputStream("c:"+File.separator+fileName);
				InputStream is = fileItem.getInputStream();
				byte[] buffer = new byte[256];
				int readBytes = 0;
				while(-1!=(readBytes=is.read(buffer))){
					fos.write(buffer,0,readBytes);
				}
				is.close();
				fos.close();
				
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
}
}
