package jehc.xtmodules.xtweb;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;
/**
 * 监控磁盘
 * @author 邓纯杰
 *
 */
@Controller
@RequestMapping("/xtDisksController")
@Scope("prototype")
public class XtDisksController extends BaseAction {
	/**
	 * 载入初始化页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadXtDisks",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtDisks(HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-disks/xt-disks-list");
	} 
	
	/**
	 * 获取当前服务器硬盘信息
	 */
	@ResponseBody
	@RequestMapping(value="/getDisksInfo",method={RequestMethod.POST,RequestMethod.GET})
	public String getDisksInfo(){
		JSONArray jsonArray = new JSONArray();  
		Map<String, Object> model = new HashMap<String, Object>();
		File[] arFileRoot = File.listRoots();
		for(int i=0;i<arFileRoot.length;i++){
			File rootFile = arFileRoot[i];
			//1盘名称
			String rootName = rootFile.getAbsolutePath().replaceAll(":\\\\","盘");
			//2是否可以读取
			if(rootFile.canRead()){
				model.put("isRead", "是");
			}else{
				model.put("isRead", "否");
			}
			//3可用空间大小
			long freeSpace = rootFile.getFreeSpace();
			//4总空间大小
			long totalSpace = rootFile.getTotalSpace();
			//5已用空间大小
			long usableSpace = rootFile.getUsableSpace();
			//6是否可写
			if(rootFile.canWrite()){
				model.put("isWrite", "是");
			}else{
				model.put("isWrite", "否");
			}
			if(totalSpace != 0){
				model.put("rootName", rootName);
//				model.put("freeSpace", (freeSpace)/(1024*1024*1024));
//				model.put("totalSpace", totalSpace/(1024*1024*1024));
//				model.put("usableSpace", (totalSpace/(1024*1024*1024))-(usableSpace)/(1024*1024*1024));
				model.put("freeSpace", (freeSpace)/(1024*1024));
				model.put("totalSpace", totalSpace/(1024*1024));
				model.put("usableSpace", (totalSpace/(1024*1024))-(usableSpace)/(1024*1024));
			}
			jsonArray.add(model);
		}
		return outItemsStr(jsonArray);
	}
}
