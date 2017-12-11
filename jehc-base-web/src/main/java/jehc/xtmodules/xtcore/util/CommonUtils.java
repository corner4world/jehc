package jehc.xtmodules.xtcore.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import jehc.solrmodules.solrmodel.SolrCore;
import jehc.xtmodules.xtcore.allutils.AllUtils;
import jehc.xtmodules.xtcore.allutils.StringUtil;
import jehc.xtmodules.xtcore.allutils.file.FileUtil;
import jehc.xtmodules.xtcore.base.BaseXtModifyRecordRun;
import jehc.xtmodules.xtcore.base.BaseXtOperateBusinessLogsRun;
import jehc.xtmodules.xtcore.util.constant.CacheConstant;
import jehc.xtmodules.xtcore.util.constant.SessionConstant;
import jehc.xtmodules.xtmodel.XtAreaRegion;
import jehc.xtmodules.xtmodel.XtAttachment;
import jehc.xtmodules.xtmodel.XtConstant;
import jehc.xtmodules.xtmodel.XtDataDictionary;
import jehc.xtmodules.xtmodel.XtDepartinfo;
import jehc.xtmodules.xtmodel.XtIpFrozen;
import jehc.xtmodules.xtmodel.XtModifyRecord;
import jehc.xtmodules.xtmodel.XtOperateBusinessLogs;
import jehc.xtmodules.xtmodel.XtPath;
import jehc.xtmodules.xtmodel.XtPost;
import jehc.xtmodules.xtmodel.XtUserinfo;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

/**
 * 公用类获取平台信息方法如获取登录用户信息,当前用户部门,当前用户岗位等一系列信息
 * 
 * @author邓纯杰
 * 
 */
public class CommonUtils extends UUID{
	/**
	 * 获取当前用户姓名
	 * 
	 * @return
	 */
	public static String getXtUname() {
		try {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
			HttpSession session = request.getSession(false);
			if(null == session){
				return null;
			}
			XtUserinfo xtUserinfo = (XtUserinfo) session.getAttribute(SessionConstant.XTUSERINFO);
			if (null != xtUserinfo) {
				return xtUserinfo.getXt_userinfo_realName();
			}
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return null;
	}

	/**
	 * 获取当前用户登录账户
	 * 
	 * @return
	 */
	public static String getXtUlname() {
		try {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
			HttpSession session = request.getSession(false);
			if(null == session){
				return null;
			}
			XtUserinfo xtUserinfo = (XtUserinfo) session.getAttribute(SessionConstant.XTUSERINFO);
			if (null != xtUserinfo) {
				return xtUserinfo.getXt_userinfo_name();
			}
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return null;
	}

	/**
	 * 获取当前用户编号
	 * 
	 * @return
	 */
	public static String getXtUid() {
		try {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
			HttpSession session = request.getSession(false);
			if(null == session){
				return null;
			}
			XtUserinfo xtUserinfo = (XtUserinfo) session.getAttribute(SessionConstant.XTUSERINFO);
			if (null != xtUserinfo) {
				return xtUserinfo.getXt_userinfo_id();
			}
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return null;
	}

	/**
	 * 获取当前用户所属部门名称
	 * 
	 * @return
	 */
	public static String getXtUdname() {
		return null;
	}

	/**
	 * 获取当前用户所属部门编号
	 * 
	 * @return
	 */
	public static String getXtUdid() {
		return null;
	}

	/**
	 * 获取当前用户所在岗位名称
	 * 
	 * @return
	 */
	public static String getXtUpname() {
		return null;
	}

	/**
	 * 获取当前用户所在岗位编号
	 * 
	 * @return
	 */
	public static String getXtUpid() {
		return null;
	}

	/**
	 * 获取当前用户对象信息
	 * 
	 * @return
	 */
	public static XtUserinfo getXtU() {
		try {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
			HttpSession session = request.getSession(false);
			if(null == session){
				return null;
			}
			XtUserinfo xtUserinfo = (XtUserinfo) session.getAttribute(SessionConstant.XTUSERINFO);
			return xtUserinfo;
		} catch (Exception e) {
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}

	/**
	 * 一个人可以拥有多个部门 获取当前用户所在部门对象集合信息
	 * 
	 * @return
	 */
	public static List<XtDepartinfo> getXtUd() {
		return null;
	}

	/**
	 * 一个人可以拥有多个岗位 获取当前用户所在岗位对象集合信息
	 * 
	 * @return
	 */
	public static List<XtPost> getXtUp() {
		return null;
	}

	/**
	 * 获取当前用户所在部门上级部门名称
	 * 
	 * @return
	 */
	public static String getXtUdupname() {
		return null;
	}

	/**
	 * 获取当前用户所在部门上级部门编号
	 * 
	 * @return
	 */
	public static String getXtUdupid() {
		return null;
	}

	/**
	 * 获取当前用户所在部门下级部门名称
	 * 
	 * @return
	 */
	public static String getXtUdnextname() {
		return null;
	}

	/**
	 * 获取当前用户所在部门下级部门编号
	 * 
	 * @return
	 */
	public static String getXtUdnextid() {
		return null;
	}

	/**
	 * 获取当前用户所在岗位上级岗位名称
	 * 
	 * @return
	 */
	public static String getXtUpupname() {
		return null;
	}

	/**
	 * 获取当前用户所在岗位上级岗位编号
	 * 
	 * @return
	 */
	public static String getXtUpupid() {
		return null;
	}

	/**
	 * 获取当前用户所在岗位下级岗位名称
	 * 
	 * @return
	 */
	public static String getXtUpnextpname() {
		return null;
	}

	/**
	 * 获取当前用户所在岗位下级岗位编号
	 * 
	 * @return
	 */
	public static String getXtUpnewxtpid() {
		return null;
	}
	
	/**
	 * 获取数据权限
	 * @return
	 */
	public static String systemUandM(){
		try {
			RequestAttributes ra = RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
			HttpSession session = request.getSession(false);
			if(null == session){
				return null;
			}
			return (String) session.getAttribute(SessionConstant.SYSTEMUANDM);
		} catch (Exception e) {
			throw new ExceptionUtil("获取systemUandM出现异常："+e.getMessage());
		}
	}

	/**
	 * 判断当前用户是否为超级管理员
	 * 
	 * @return
	 */
	public static boolean isAdmin() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
		HttpSession session = request.getSession(false);
		if(null == session){
			return false;
		}
		XtUserinfo xtUserinfo = (XtUserinfo) session.getAttribute(SessionConstant.XTUSERINFO);
		if (xtUserinfo.getXt_userinfo_isAdmin() == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取缓存值
	 * 
	 * @return
	 */
	public static Object getCache(String key) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
		ServletContext sc = request.getSession(false).getServletContext();
//		Map<String, Object> map = (Map<String, Object>) sc.getAttribute("sys_message");
//		return map.get(key);
		return (String)sc.getAttribute(key);
	}

	/**
	 * 获取缓存值
	 * 
	 * @return
	 */
	public static String getCacheStr(String key) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
		ServletContext sc = request.getSession().getServletContext();
//		Map<String, Object> map = (Map<String, Object>) sc.getAttribute("sys_message");
//		return (String) map.get(key);
		return (String)sc.getAttribute(key);
	}

	/**
	 * 根据KEY获取平台路径
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<XtPath> getXtPathCache(String key) {
		Cache ehCache = CacheManagerUtil.getCache(CacheConstant.XTPATHCACHE);
		Element xtPathCacheEle = ehCache.get(CacheConstant.XTPATHCACHE);
		if(null != xtPathCacheEle){
			List<XtPath> xtPathList = (List<XtPath>) xtPathCacheEle.getObjectValue();
			List<XtPath> list = new ArrayList<XtPath>();
			for (int i = 0; i < xtPathList.size(); i++) {
				XtPath xtPath = xtPathList.get(i);
				if (key.equals(xtPath.getXt_value())) {
					list.add(xtPath);
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 根据KEY获取平台常量
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static XtConstant getXtConstantCache(String key) {
		Cache ehCache = CacheManagerUtil.getCache(CacheConstant.XTCONSTANTCACHE);
		Element XtConstantEle = ehCache.get(CacheConstant.XTCONSTANTCACHE);
		if(null != XtConstantEle){
			List<XtConstant> xtConstantList = (List<XtConstant>) XtConstantEle.getObjectValue();
			for (int i = 0; i < xtConstantList.size(); i++) {
				XtConstant xtConstant = xtConstantList.get(i);
				if (key.equals(xtConstant.getXt_constantName())) {
					return xtConstant;
				}
			}
		}
		return null;
	}

	/**
	 * 根据key获取行政区域集合
	 * @param key
	 * @return
	 */
	public static List<XtAreaRegion> getXtAreaRegionCache(String key) {
		Cache ehCache = CacheManagerUtil.getCache(CacheConstant.XTAREAREGIONCACHE);
		Element XtAreaRegionCache = ehCache.get(CacheConstant.XTAREAREGIONCACHE);
		List<XtAreaRegion> arList = new ArrayList<XtAreaRegion>();
		if(null != XtAreaRegionCache){
			@SuppressWarnings("unchecked")
			List<XtAreaRegion> list = (List<XtAreaRegion>) XtAreaRegionCache.getObjectValue();
			if(StringUtil.isEmpty(key)){
				for (int i = 0; i < list.size(); i++) {
					XtAreaRegion xtAreaRegion = list.get(i);
					if (xtAreaRegion.getPARENT_ID() ==  1) {
						arList.add(xtAreaRegion);
					}
				}
			}else{
				for (int i = 0; i < list.size(); i++) {
					XtAreaRegion xtAreaRegion = list.get(i);
					if (key.equals(""+xtAreaRegion.getPARENT_ID())) {
						arList.add(xtAreaRegion);
					}
				}
			}
		}
		return arList;
	}
	
	public static List<XtAreaRegion> getXtAreaRegionAllCache() {
		Cache ehCache = CacheManagerUtil.getCache(CacheConstant.XTAREAREGIONCACHE);
		Element XtAreaRegionCache = ehCache.get(CacheConstant.XTAREAREGIONCACHE);
		return  (List<XtAreaRegion>) XtAreaRegionCache.getObjectValue();
	}
	
	/**
	 * 根据KEY获取平台字典
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<XtDataDictionary> getXtDataDictionaryCache(String key) {
		Cache ehCache = CacheManagerUtil.getCache(CacheConstant.XTDATADICTIONARYCACHE);
		Element xtDataDictionaryCacheEle = ehCache.get(CacheConstant.XTDATADICTIONARYCACHE);
		if(null != xtDataDictionaryCacheEle){
			List<XtDataDictionary> xtDataDictionaryList = (List<XtDataDictionary>) xtDataDictionaryCacheEle.getObjectValue();
			List<XtDataDictionary> list = new ArrayList<XtDataDictionary>();
			String id ="";
			for (int i = 0; i < xtDataDictionaryList.size(); i++) {
				XtDataDictionary xtDataDictionary = xtDataDictionaryList.get(i);
				if(key.equals(xtDataDictionary.getXt_data_dictionary_value())){
					id = xtDataDictionary.getXt_data_dictionary_id();
					break;
				}
			}
			for (int i = 0; i < xtDataDictionaryList.size(); i++) {
				XtDataDictionary xtDataDictionary = xtDataDictionaryList.get(i);
				if (id.equals(xtDataDictionary.getXt_data_dictionary_pid()) && !"".equals(id)) {
					list.add(xtDataDictionary);
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 判断IP是否为黑户
	 * @param ip
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean getXtIpFrozenCache(String ip){
		Cache ehCache = CacheManagerUtil.getCache(CacheConstant.XTIPFROZENCACHE);
		Element XtIpFrozenCacheEle = ehCache.get(CacheConstant.XTIPFROZENCACHE);
		if(null != XtIpFrozenCacheEle){
			List<XtIpFrozen> xtIpFrozenList = (List<XtIpFrozen>) XtIpFrozenCacheEle.getObjectValue();
			for (int i = 0; i < xtIpFrozenList.size(); i++) {
				XtIpFrozen xtIpFrozen = xtIpFrozenList.get(i);
				if(ip.equals(xtIpFrozen.getXt_ip_frozen_address())){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 获取公共功能缓存
	 * @return
	 */
	public static String getXtFunctioninfoCommonCache(){
		Cache ehCache = CacheManagerUtil.getCache(CacheConstant.XTFUNCTIONINFOCOMMONCACHE);
		Element XtFunctioninfoCommonCacheEle = ehCache.get(CacheConstant.XTFUNCTIONINFOCOMMONCACHE);
		return ""+XtFunctioninfoCommonCacheEle.getObjectValue();
	}
	
	/**
	 * 根据使用唯一编号编码查找对象
	 * @param useonlynumbercode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static SolrCore getSolrCoreByUseonlynumbercode(String useonlynumbercode){
		Cache ehCache = CacheManagerUtil.getCache(CacheConstant.SOLRCORECACHE);
		Element SolrCoreEle = ehCache.get(CacheConstant.SOLRCORECACHE);
		if(null != SolrCoreEle){
			List<SolrCore> solrCoreList = (List<SolrCore>) SolrCoreEle.getObjectValue();
			for (int i = 0; i < solrCoreList.size(); i++) {
				SolrCore solrCore = solrCoreList.get(i);
				if (useonlynumbercode.equals(solrCore.getUseonlynumbercode())) {
					return solrCore;
				}
			}
		}
		return null;
	}
	/**
	 * 通用文件上传
	 * @param request
	 * @param path上传的路径
	 * @param relative_path相对路径即数据库存放路径
	 * @param validateparameter校验类型
	 * @param validateSize校验大小
	 * @param xt_path_absolutek绝对路径键（自定义）
	 * @param xt_path_urlk基础路径URL（自定义）
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static List<XtAttachment> upLoad(HttpServletRequest request,String path,String relative_path,String validateparameter,String validateSize,String xt_path_absolutek,String xt_path_relativek,String xt_path_urlk) throws IllegalStateException, IOException{
		List<XtAttachment> list = new ArrayList<XtAttachment>();
		File filePath = new File(path);
		if(!filePath.exists()){
			filePath.mkdirs();
		}
		//解析器解析request的上下文
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//先判断request中是否包涵multipart类型的数据，
		if (multipartResolver.isMultipart(request)) {
			//再将request中的数据转化成multipart类型的数据
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			Iterator iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				MultipartFile file = multiRequest.getFile((String) iter.next());
				if(file != null){
					//验证图片类型 验证图片大小
					if(validate(file,validateparameter,validateSize)){
						String fileName = file.getOriginalFilename();
						int lastD = fileName.lastIndexOf('.');  
						String newName = AllUtils.getRandom()+fileName.substring(lastD,fileName.length());
						//写文件到本地
						//File localFile = new File(path);
						//file.transferTo(localFile);
						FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path,newName));//采用文件拷贝模式上传
//						FtpUtil.uploadFile(path, newName, file.getInputStream());//此处可以修改成Ftp操作如下:
						XtAttachment xt_Attachment = new XtAttachment();
						xt_Attachment.setXt_attachment_id(UUID.toUUID());
						xt_Attachment.setXt_attachmentCtime(getSimpleDateFormat());
						xt_Attachment.setXt_attachmentName(newName);
						xt_Attachment.setXt_attachmentPath(relative_path+newName);
						xt_Attachment.setXt_userinfo_id(CommonUtils.getXtUid());
						xt_Attachment.setXt_attachmentSize(""+file.getSize());
						xt_Attachment.setXt_attachmentType(file.getContentType());
						xt_Attachment.setXt_attachmentTitle(fileName);
						xt_Attachment.setXt_userinfo_id(getXtUid());
						xt_Attachment.setXt_path_absolutek(xt_path_absolutek);
						xt_Attachment.setXt_path_relativek(xt_path_relativek);
						xt_Attachment.setXt_path_urlk(xt_path_urlk);
						list.add(xt_Attachment);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 验证文件合法
	 * @param file
	 * @param validateparameter 格式如:png,jpg,bmp 以逗号分隔
	 * @param validateSize 格式如:1024-10240以-分隔
	 * @return
	 */
	public static boolean validate(MultipartFile file,String validateparameter,String validateSize){
		if(null != validateparameter && !"".equals(validateparameter) && !StringUtils.isEmpty(validateparameter)){
			String[] v = validateparameter.split(",");
			String ftype = FileUtil.getFilePreFix(file.getOriginalFilename()).toLowerCase();
			int flag=0;
			for(int i = 0; i < v.length; i++){
				if(ftype.equals(v[i].toLowerCase())){
					flag++;
				}
			}
			if(flag==0){
				throw new ExceptionUtil("文件类型不合法：文件只能上传["+validateparameter+"]格式");
			}
		}
		if(null != validateSize && !"".equals(validateSize) && !StringUtils.isEmpty(validateSize)){
			String[] siz = validateSize.split("-");
			long fsize = file.getSize();
			//说明只有一个参数 此时平台默认上传不超过该大小
			if(siz.length==1){
				if(fsize>new Long(siz[0])){
					throw new ExceptionUtil("文件大小不合法：文件大小不能超过["+siz[0]+"]Kb");
				}
			}else if(siz.length>2 || siz.length < 1){
				throw new ExceptionUtil("文件大小验证规则不符合：请参考格式如:1024-10240以-分隔");
			}else{
				if(fsize < new Long(siz[0])){
					throw new ExceptionUtil("文件大小不合法：文件大小最小必须超过["+siz[0]+"]Kb");
				}
				if(fsize > new Long(siz[1])){
					throw new ExceptionUtil("文件大小不合法：文件大小不能超过["+siz[1]+"]Kb");
				}
			}
		}
		return true;
	}
	
	/**
	 * 获取当前时间字符串
	 * @return
	 */
	public static String getSimpleDateFormat(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	/**
	 * 获取 当前日期
	 * @return
	 */
	public static Date getDate(){
		return new Date();
	}
	/**
	 * 添加平台业务操作日志通用 采用put方法目的不走事务控制
	 * @param classname
	 * @param method
	 * @param message
	 */
	public void aBLogs(String classname,String method,String message){
		XtOperateBusinessLogs xt_Operate_Business_Logs = new XtOperateBusinessLogs();
		xt_Operate_Business_Logs.setXt_operate_b_logsTime(CommonUtils.getSimpleDateFormat());
		xt_Operate_Business_Logs.setXt_operate_b_logs_id(UUID.toUUID());
		xt_Operate_Business_Logs.setXt_operate_b_logsModules(classname);
		xt_Operate_Business_Logs.setXt_operate_b_logsMethod(method);
		xt_Operate_Business_Logs.setXt_userinfo_id(CommonUtils.getXtUid());
		xt_Operate_Business_Logs.setXt_operate_b_logsResult(message);
		//////////为了性能采用异步
		/**
        xt_Operate_Business_LogsService.putXtOperateBusinessLogs(xt_Operate_Business_Logs);
        **/
		new BaseXtOperateBusinessLogsRun(xt_Operate_Business_Logs).start();
	}
	/**
	 * 添加平台业务操作日志通用 采用put方法目的不走事务控制
	 * @param classname
	 * @param method
	 * @param message
	 * @param parm
	 */
	public void aBLogs(String classname,String method,String message,String parm){
		XtOperateBusinessLogs xt_Operate_Business_Logs = new XtOperateBusinessLogs();
		xt_Operate_Business_Logs.setXt_operate_b_logsTime(CommonUtils.getSimpleDateFormat());
		xt_Operate_Business_Logs.setXt_operate_b_logs_id(UUID.toUUID());
		xt_Operate_Business_Logs.setXt_operate_b_logsModules(classname);
		xt_Operate_Business_Logs.setXt_operate_b_logsMethod(method);
		xt_Operate_Business_Logs.setXt_userinfo_id(CommonUtils.getXtUid());
		xt_Operate_Business_Logs.setXt_operate_b_logsResult(message);
		xt_Operate_Business_Logs.setXt_operate_b_logsMethodPar(parm);
		//////////为了性能采用异步
		/**
        xt_Operate_Business_LogsService.putXtOperateBusinessLogs(xt_Operate_Business_Logs);
        **/
        new BaseXtOperateBusinessLogsRun(xt_Operate_Business_Logs).start();
	}
	
	/**
	 * 执行变更记录
	 * @param <T>
	 * @param oldT
	 * @param newT
	 * @param className
	 */
	@SuppressWarnings("unchecked")
	public static <T> void aRecord(T oldT, T newT, String modules,String business_id){
		try {
			JSONObject oldJson = JsonUtil.toJsonObj(oldT);
			JSONObject newJson = JsonUtil.toJsonObj(newT);
			List<XtModifyRecord> list = new ArrayList<XtModifyRecord>();
			Iterator iterator = oldJson.keys();
			while(iterator.hasNext()){
	            String key = (String) iterator.next();
	            String oldV = oldJson.getString(key);
	            String newV = newJson.getString(key);
	            if(!oldV.equals(newV)){
	            	XtModifyRecord record = new XtModifyRecord();
	            	record.setXt_modify_record_aftervalue(""+newV);
	            	record.setXt_modify_record_beforevalue(""+oldV);
	            	record.setXt_modify_record_ctime(CommonUtils.getSimpleDateFormat());
	            	record.setXt_modify_record_field(key);
	            	record.setXt_modify_record_modules(modules);
	            	list.add(record);
	            }
			}
			for(int i = 0; i < list.size(); i++){
				list.get(i).setXt_modify_record_id(UUID.toUUID());
				list.get(i).setBusiness_id(business_id);
				list.get(i).setXt_userinfo_id(CommonUtils.getXtUid());
			}
			new BaseXtModifyRecordRun(list).start();
		} catch (Exception e) {
		}
	}
	
	/**
	 * 执行变更记录并过滤字段
	 * @param <T>
	 * @param oldT
	 * @param newT
	 * @param className
	 */
	@SuppressWarnings("unchecked")
	public static <T> void aRecord(T oldT, T newT, String modules,String business_id,List<String> fieldList){
		try {
			JSONObject oldJson = JsonUtil.toJsonObj(oldT);
			JSONObject newJson = JsonUtil.toJsonObj(newT);
			List<XtModifyRecord> list = new ArrayList<XtModifyRecord>();
			Iterator iterator = oldJson.keys();
			while(iterator.hasNext()){
	            String key = (String) iterator.next();
	            if(!fieldList.isEmpty() && fieldList.size() > 0){
	            	for(String field:fieldList){
	            		if(field.equals(key)){
	            			String oldV = oldJson.getString(key);
	        	            String newV = newJson.getString(key);
	        	            if(!oldV.equals(newV)){
	        	            	XtModifyRecord record = new XtModifyRecord();
	        	            	record.setXt_modify_record_aftervalue(""+newV);
	        	            	record.setXt_modify_record_beforevalue(""+oldV);
	        	            	record.setXt_modify_record_ctime(CommonUtils.getSimpleDateFormat());
	        	            	record.setXt_modify_record_field(key);
	        	            	record.setXt_modify_record_modules(modules);
	        	            	list.add(record);
	        	            }
	            		}
	            	}
	            }
			}
			for(int i = 0; i < list.size(); i++){
				list.get(i).setXt_modify_record_id(UUID.toUUID());
				list.get(i).setBusiness_id(business_id);
				list.get(i).setXt_userinfo_id(CommonUtils.getXtUid());
			}
			new BaseXtModifyRecordRun(list).start();
		} catch (Exception e) {
		}
	}
	
	/**
	 * 统一验证错误 通过注解捕捉字段验证错误信息
	 * @param bindingResult
	 * @return
	 */
	public String backFem(BindingResult bindingResult){
		List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < fieldErrorList.size(); i++) {
	    	FieldError fieldError =fieldErrorList.get(i);
	        sb.append("错误字段消息："+fieldError.getField() +" : "+fieldError.getDefaultMessage()+"<br>");
	    }
	    return sb.toString();
	}
//	/**
//	 * 统一验证错误 通过注解捕捉字段验证错误信息
//	 * @param bindingResult
//	 * @return
//	 */
//	public String backFem(BindingResult bindingResult){
//		List<ObjectError> fieldErrorList = bindingResult.getAllErrors();
//	    StringBuilder sb = new StringBuilder();
//	    for (int i = 0; i < fieldErrorList.size(); i++) {
//	    	ObjectError fieldError =fieldErrorList.get(i);
//	        sb.append("错误字段消息："+fieldError.getObjectName() +" : "+fieldError.getDefaultMessage()+"<br>");
//	    }
//	    return sb.toString();
//	}
}
