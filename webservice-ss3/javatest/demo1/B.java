package demo1;

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


/**
 * 
 * project_name webservice-ss3
 *
 * package demo1
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-2-1上午11:31:12
 * springMvc文件上传进度条
 *
 */
public class B extends CommonsMultipartResolver {
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
