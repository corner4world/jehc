package jehc.xtmodules.xtdao.impl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import jehc.xtmodules.xtcore.base.impl.BaseDaoImpl;
import jehc.xtmodules.xtdao.Xt_EncoderqrcodeDao;
import jehc.xtmodules.xtmodel.Xt_Encoderqrcode;

/**
* 平台二维码 
* 2016-04-05 20:58:53  邓纯杰
*/
@Repository("xt_EncoderqrcodeDao")
public class Xt_EncoderqrcodeDaoImpl  extends BaseDaoImpl implements Xt_EncoderqrcodeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Encoderqrcode> getXtEncoderqrcodeListByCondition(Map<String,Object> condition){
		return (List<Xt_Encoderqrcode>)this.getList("getXtEncoderqrcodeListByCondition",condition);
	}
	/**
	* 查询对象
	* @param xt_encoderQRCode_id 
	* @return
	*/
	public Xt_Encoderqrcode getXtEncoderqrcodeById(String xt_encoderQRCode_id){
		return (Xt_Encoderqrcode)this.get("getXtEncoderqrcodeById", xt_encoderQRCode_id);
	}
	/**
	* 添加
	* @param xt_encoderqrcode 
	* @return
	*/
	public int addXtEncoderqrcode(Xt_Encoderqrcode xt_Encoderqrcode){
		return this.add("addXtEncoderqrcode", xt_Encoderqrcode);
	}
	/**
	* 修改
	* @param xt_encoderqrcode 
	* @return
	*/
	public int updateXtEncoderqrcode(Xt_Encoderqrcode xt_Encoderqrcode){
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
