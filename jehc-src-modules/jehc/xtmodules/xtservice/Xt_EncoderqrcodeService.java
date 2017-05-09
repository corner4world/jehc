package jehc.xtmodules.xtservice;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.Xt_Encoderqrcode;

/**
* 平台二维码 
* 2016-04-05 20:58:53  邓纯杰
*/
public interface Xt_EncoderqrcodeService{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<Xt_Encoderqrcode> getXtEncoderqrcodeListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_encoderQRCode_id 
	* @return
	*/
	public Xt_Encoderqrcode getXtEncoderqrcodeById(String xt_encoderQRCode_id);
	/**
	* 添加
	* @param xt_encoderqrcode 
	* @return
	*/
	public int addXtEncoderqrcode(Xt_Encoderqrcode xt_Encoderqrcode);
	/**
	* 修改
	* @param xt_encoderqrcode 
	* @return
	*/
	public int updateXtEncoderqrcode(Xt_Encoderqrcode xt_Encoderqrcode);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtEncoderqrcode(Map<String,Object> condition);
}
