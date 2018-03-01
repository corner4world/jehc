package jehc.xtmodules.xtcore.allutils.file;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileEntity {
	private String targetFileName;//目标文件名称（如下载文件名称）
	private String basePath;//附件基础路径
	private List<String> filePathList;//附件集合路径
	private String zipFilePath;//附件压缩文件路径（创建临时文件）
	private String zipFileName;//附件压缩文件名称
	private HttpServletResponse res;
	private HttpServletRequest req;
	
	/**
	 * 默认构造函数
	 */
	public FileEntity(){}
	
	/**
	 * 有参构造函数
	 * @param targetFileName
	 * @param basePath
	 * @param filePathList
	 * @param zipFilePath
	 * @param res
	 * @param req
	 */
	public FileEntity(String targetFileName,String basePath,List<String> filePathList,String zipFilePath,String zipFileName,HttpServletResponse res,HttpServletRequest req){
		this.targetFileName = targetFileName;
		this.basePath = basePath;
		this.filePathList = filePathList;
		this.zipFilePath = zipFilePath;
		this.zipFileName =zipFileName;
		this.req = req;
		this.res = res;
	}
	public String getTargetFileName() {
		return targetFileName;
	}
	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}
	public List<String> getFilePathList() {
		return filePathList;
	}
	public void setFilePathList(List<String> filePathList) {
		this.filePathList = filePathList;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public String getZipFilePath() {
		return zipFilePath;
	}
	public void setZipFilePath(String zipFilePath) {
		this.zipFilePath = zipFilePath;
	}
	public HttpServletResponse getRes() {
		return res;
	}
	public void setRes(HttpServletResponse res) {
		this.res = res;
	}
	public HttpServletRequest getReq() {
		return req;
	}
	public void setReq(HttpServletRequest req) {
		this.req = req;
	}

	public String getZipFileName() {
		return zipFileName;
	}

	public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}
}
