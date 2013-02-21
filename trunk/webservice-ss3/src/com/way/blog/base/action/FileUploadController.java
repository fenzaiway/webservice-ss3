package com.way.blog.base.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * project_name webservice-ss3
 *
 * package com.way.blog.util.web
 *
 * @author fenzaiway
 *
 * @email fenzaiway@qq.com
 *
 * create_time 2013-1-31下午10:13:21
 *
 * 文件上传控制类
 *
 */
@Controller
@RequestMapping(value="/fileUpload")
public class FileUploadController {
	private static final String REALNAME = "realName";
	private static final String STORENAME = "storeName";
	private static final String SIZE = "size";
	private static final String SUFFIX = "suffix";
	private static final String CONTENTTYPE = "contentType"; 
	private static final String CREATETIME = "createTime";
	private static final String UPLOADDIR = "uploadDir/";
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile("file");

		String name = multipartRequest.getParameter("name");
		System.out.println("name: " + name);
		// 获得文件名：
		String realFileName = file.getOriginalFilename();
		System.out.println("获得文件名：" + realFileName);
		// 获取路径
		String ctxPath = request.getSession().getServletContext().getRealPath(
				"/")
				+ "images/";
		// 创建文件
		File dirPath = new File(ctxPath);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
		File uploadFile = new File(ctxPath + realFileName);
		FileCopyUtils.copy(file.getBytes(), uploadFile);
		request.setAttribute("files", loadFiles(request));
		return new ModelAndView("success");
	}

	@RequestMapping(value = "/upload2", method = RequestMethod.POST)
	public ModelAndView onSubmit2(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {

		// 转型为MultipartHttpRequest
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 根据前台的name名称得到上传的文件
		MultipartFile file = multipartRequest.getFile("file");
		// 获得文件名：
		String realFileName = file.getOriginalFilename();
		// 获取路径
		String ctxPath = request.getSession().getServletContext().getRealPath(
				"/")
				+ "\\" + "images\\";
		// 创建文件
		File dirPath = new File(ctxPath);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
		File uploadFile = new File(ctxPath + realFileName);
		FileCopyUtils.copy(file.getBytes(), uploadFile);
		request.setAttribute("files", loadFiles(request));
		return new ModelAndView("success");
	}

	@RequestMapping(value = "/upload3", method = RequestMethod.POST)
	public String upload(@RequestParam("file")
	MultipartFile image, HttpServletRequest request) throws IOException {

		String ctxPath = request.getSession().getServletContext().getRealPath(
				"/")
				+ "\\" + "images\\";
		System.out.println("路径：" + ctxPath);
		File file = new File(ctxPath + "/" + image.getOriginalFilename());
		// FileCopyUtils.copy(image.getBytes(),new
		// File(ctxPath+"/"+image.getOriginalFilename()));
		try {
			image.transferTo(file); // 保存上传的文件
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("files", loadFiles(request));
		return "success";
	}

	// 多文件上传
	@RequestMapping(value = "/upload4", method = RequestMethod.POST)
	public ModelAndView fileUpload(HttpServletRequest request,
			HttpServletResponse response, BindException errors)
			throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		System.out.println(fileMap.size());
		String ctxPath = request.getSession().getServletContext().getRealPath(
				"/")
				+ "\\" + "images\\";

		File file = new File(ctxPath);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件名
			 System.out.println("key: " + entity.getKey());
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			File uploadFile = new File(ctxPath + fileName);
			FileCopyUtils.copy(mf.getBytes(), uploadFile);
		}
		request.setAttribute("files", loadFiles(request));
		return new ModelAndView("success");
	}

	// @ModelAttribute("files")//此属性用于初始类时调用,但上传文件后不能时时反应上传文件个数,不适合动态数据
	public List<String> loadFiles(HttpServletRequest request) {
		List<String> files = new ArrayList<String>();
		String ctxPath = request.getSession().getServletContext().getRealPath(
				"/")
				+ "\\" + "images\\";
		File file = new File(ctxPath);
		if (file.exists()) {
			File[] fs = file.listFiles();
			String fname = null;
			for (File f : fs) {
				fname = f.getName();
				if (f.isFile()) {
					files.add(fname);
				}
			}
		}
		return files;
	}

	@RequestMapping("/download/{fileName}")
	public ModelAndView download(@PathVariable("fileName")
	String fileName, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext().getRealPath(
				"/")
				+ "\\" + "images\\";
		String downLoadPath = ctxPath + fileName;
		System.out.println(downLoadPath);
		try {
			long fileLength = new File(downLoadPath).length();
			response.reset();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}
	
	
	
	/**
	 * 将上传文件进行重命名
	 * @param name
	 * @return
	 */
	public String rename(String name){
		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		Long random = (long) (Math.random()*now);
		String fileName = now + "" + random;
		if(fileName.indexOf(".")!=-1){
			fileName += name.substring(name.lastIndexOf("."));
		}
		return fileName;
	}
	
	public List<Map<String,Object>> upload(HttpServletRequest request, String[] params, Map<String,Object[]> values)
		throws Exception{
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		String uploadDir = request.getSession().getServletContext().getRealPath("/")+FileUploadController.UPLOADDIR;
		File file = new File(uploadDir);
		if(!file.exists()){
			file.mkdir();
		}
		String fileName = null;
		int i = 0;
		for(Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();it.hasNext(); i++){
			Map.Entry<String,MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			fileName = mFile.getOriginalFilename();
			String storeName = rename(fileName);
			String noZipName = uploadDir+storeName;
			String zipName = zipName(noZipName);
			////上传成为压缩文件
			ZipOutputStream outputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipName)));
			outputStream.putNextEntry(new ZipEntry(fileName));
			FileCopyUtils.copy(mFile.getInputStream(), outputStream);
			Map<String,Object> map = new HashMap<String, Object>();
			///固定参数值
			map.put(FileUploadController.REALNAME, zipName(fileName));
			map.put(FileUploadController.STORENAME, zipName(storeName));
			map.put(FileUploadController.SIZE, new File(zipName).length());
			map.put(FileUploadController.SUFFIX, "zip");
			map.put(FileUploadController.CONTENTTYPE, "application/octet-stream");
			map.put(FileUploadController.CREATETIME, new Date());
			////自定义参数对
			for(String param : params){
				map.put(param, values.get(param)[i]);
			}
			result.add(map);
		}
		return result;
		
		
	}

	private String zipName(String name) {
		String prefix = "";
		if(name.indexOf(".")!=-1){
			prefix = name.substring(0,name.lastIndexOf("."));
		}else{
			prefix = name;
		}
		return prefix+".zip";
	}
}
