package jehc.xtmodules.xtdao;
import java.util.List;
import java.util.Map;

import jehc.xtmodules.xtmodel.XtEncoderqrcode;

/**
* 平台二维码 
* 2016-04-05 20:58:53  邓纯杰
*/
public interface XtEncoderqrcodeDao{
	/**
	* 分页
	* @param condition 
	* @return
	*/
	public List<XtEncoderqrcode> getXtEncoderqrcodeListByCondition(Map<String,Object> condition);
	/**
	* 查询对象
	* @param xt_encoderQRCode_id 
	* @return
	*/
	public XtEncoderqrcode getXtEncoderqrcodeById(String xt_encoderQRCode_id);
	/**
	* 添加
	* @param xt_encoderqrcode 
	* @return
	*/
	public int addXtEncoderqrcode(XtEncoderqrcode xt_Encoderqrcode);
	/**
	* 修改
	* @param xt_encoderqrcode 
	* @return
	*/
	public int updateXtEncoderqrcode(XtEncoderqrcode xt_Encoderqrcode);
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtEncoderqrcode(Map<String,Object> condition);
}
