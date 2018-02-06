package jehc.xtmodules.xtcore.util.quartz.task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jehc.xtmodules.xtcore.allutils.file.FileUtil;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.constant.CacheConstant;
import jehc.xtmodules.xtcore.util.db.DBMSMetaUtil;
import jehc.xtmodules.xtcore.util.db.DbInfo;
import jehc.xtmodules.xtcore.util.springutil.SpringUtil;
import jehc.xtmodules.xtdao.XtDbDao;
import jehc.xtmodules.xtmodel.XtDb;
import jehc.xtmodules.xtmodel.XtPath;

/**
 * 数据库备份定时任务
 * 
 * @author邓纯杰
 * 
 */
public class Xt_DbinfoBack_Task extends BaseService{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 业务逻辑处理
	 */
	public void service() {
		XtDbDao xtDbDao= (XtDbDao)SpringUtil.getBean("xtDbDao");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfID = new SimpleDateFormat("yyyyMMddHHmmss");
		logger.info(sdf.format(new Date())+"--->开始备份数据库调度-----------"); 
		//1读取配置文件中数据库连接信息
		DbInfo dbInfo = DBMSMetaUtil.excuteDB(null);
		List<XtPath> pathList = getXtPathCache(CacheConstant.XTDBBACKPATH);
		String path = pathList.get(0).getXt_path();
		FileUtil.validOrCreateFile(path);
		String cmd = "";
		if("oracle".equals(dbInfo.getDatabasetype())){
			 cmd = "exp " + dbInfo.getUsername() + "/" + dbInfo.getPassword() + "@" + dbInfo.getIp()+":"+dbInfo.getPort()+"/"+dbInfo.getDbname() + " file=" + path + "/" + sdfID.format(new Date()) + ".dmp";
			 try {
				Process process = Runtime.getRuntime().exec(cmd);
//				try {
//					if(process.waitFor() == 0){
//						logger.info(sdf.format(new Date())+"--->备份oracle数据库成功"); 
//					 }
//				} catch (InterruptedException e) {
//					logger.error(sdf.format(new Date())+"--->备份oracle数据库失败，错误原因："+e.getLocalizedMessage()); 
//				}
			} catch (IOException e) {
				logger.error(sdf.format(new Date())+"--->备份oracle数据库失败，错误原因："+e.getLocalizedMessage()); 
			}
		}else if("mysql".equals(dbInfo.getDatabasetype())){
			path = path + "/" + sdfID.format(new Date()) + ".sql";
			cmd = "cmd /c " + "mysqldump -h "+ dbInfo.getIp() + " -P"+ dbInfo.getPort() + " -u"+ dbInfo.getUsername() + " -p"+ dbInfo.getPassword() + "  "+ dbInfo.getDbname() + "";
			//2执行备份操作
			Runtime rt = Runtime.getRuntime();
			PrintWriter p = null;
			OutputStream output;
			try {
				output = new FileOutputStream(path);
				BufferedReader reader = null;
				try {
					p = new PrintWriter(new OutputStreamWriter(output, "utf8"));
					Process child;
					try {
						child = rt.exec(cmd);
						InputStreamReader inputStreamReader = new InputStreamReader(child.getInputStream(), "utf8");
						reader = new BufferedReader(inputStreamReader);
						String line = null;
						while ((line = reader.readLine()) != null) {
							p.println(line);
						}
						p.flush();
						try {
							if (reader != null) {
								reader.close();
							}
							if (p != null) {
								p.close();
							}
						} catch (IOException e) {
							logger.error(sdf.format(new Date())+"--->备份mysql数据库失败1，错误原因："+e.getLocalizedMessage()); 
						}
					} catch (IOException e1) {
						logger.error(sdf.format(new Date())+"--->备份mysql数据库失败2，错误原因："+e1.getLocalizedMessage()); 
					}
				} catch (UnsupportedEncodingException e1) {
					logger.error(sdf.format(new Date())+"--->备份mysql数据库失败3，错误原因："+e1.getLocalizedMessage()); 
				}
			} catch (FileNotFoundException e1) {
				logger.error(sdf.format(new Date())+"--->备份mysql数据库失败4，错误原因："+e1.getLocalizedMessage()); 
			}
		}
		//3.备份成功入库记录
		XtDb xtDb = new XtDb();
		xtDb.setXt_db_name(dbInfo.getDbname());
		xtDb.setXt_db_path(path);
		xtDb.setXt_db_time(sdf.format(new Date()));
		xtDb.setXt_db_user("平台");
		xtDb.setXt_db_type(0);
		xtDb.setXt_db_id(toUUID());
		xtDbDao.addXtDb(xtDb);
		logger.info(sdf.format(new Date())+"--->结束备份数据库调度-----------"); 
	}
}
