package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.XtEncoderqrcodeDao;
import jehc.xtmodules.xtmodel.XtEncoderqrcode;

/**
* 平台二维码 
* 2016-04-05 20:58:53  邓纯杰
*/
@Repository("xtEncoderqrcodeDao")
public class XtEncoderqrcodeDaoImpl  extends BaseDaoImpl implements XtEncoderqrcodeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<XtEncoderqrcode> getXtEncoderqrcodeListByCondition(Map<String,Object> condition){
		return (List<XtEncoderqrcode>)this.getList("getXtEncoderqrcodeListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_encoderQRCode_id 
	* @return
	*/
	public XtEncoderqrcode getXtEncoderqrcodeById(String xt_encoderQRCode_id){
		return (XtEncoderqrcode)this.get("getXtEncoderqrcodeById", xt_encoderQRCode_id);
	}
	/**
	* 添加
	* @param xt_encoderqrcode 
	* @return
	*/
	public int addXtEncoderqrcode(XtEncoderqrcode xt_Encoderqrcode){
		return this.add("addXtEncoderqrcode", xt_Encoderqrcode);
	}
	/**
	* 修改
	* @param xt_encoderqrcode 
	* @return
	*/
	public int updateXtEncoderqrcode(XtEncoderqrcode xt_Encoderqrcode){
		return this.update("updateXtEncoderqrcode", xt_Encoderqrcode);
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtEncoderqrcode(Map<String,Object> condition){
		return this.del("delXtEncoderqrcode", condition);
	}
}
