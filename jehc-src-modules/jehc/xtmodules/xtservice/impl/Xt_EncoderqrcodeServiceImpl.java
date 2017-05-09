package jehc.xtmodules.xtservice.impl;
import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jehc.xtmodules.xtcore.allutils.AllUtils;
import jehc.xtmodules.xtcore.base.BaseService;
import jehc.xtmodules.xtcore.util.CommonUtils;
import jehc.xtmodules.xtcore.util.ExceptionUtil;
import jehc.xtmodules.xtcore.util.UUID;
import jehc.xtmodules.xtdao.Xt_EncoderqrcodeDao;
import jehc.xtmodules.xtmodel.Xt_Attachment;
import jehc.xtmodules.xtmodel.Xt_Encoderqrcode;
import jehc.xtmodules.xtservice.Xt_AttachmentService;
import jehc.xtmodules.xtservice.Xt_EncoderqrcodeService;

/**
* 平台二维码 
* 2016-04-05 20:58:53  邓纯杰
*/
@Service("xt_EncoderqrcodeService")
public class Xt_EncoderqrcodeServiceImpl extends BaseService implements Xt_EncoderqrcodeService{
	@Autowired
	private Xt_EncoderqrcodeDao xt_EncoderqrcodeDao;
	@Autowired
	private Xt_AttachmentService xt_AttachmentService;
	/**
	* 分页
	* @param condition 
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Xt_Encoderqrcode> getXtEncoderqrcodeListByCondition(Map<String,Object> condition){
		try{
			return xt_EncoderqrcodeDao.getXtEncoderqrcodeListByCondition(condition);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 查询对象
	* @param xt_encoderQRCode_id 
	* @return
	*/
	public Xt_Encoderqrcode getXtEncoderqrcodeById(String xt_encoderQRCode_id){
		try{
			return xt_EncoderqrcodeDao.getXtEncoderqrcodeById(xt_encoderQRCode_id);
		} catch (Exception e) {
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
	}
	/**
	* 添加
	* @param xt_encoderqrcode 
	* @return
	*/
	@SuppressWarnings("static-access")
	public int addXtEncoderqrcode(Xt_Encoderqrcode xt_Encoderqrcode){
		int i = 0;
		try {
//			TwoDimensionCode twoDimensionCode = new TwoDimensionCode();
			String newName = AllUtils.getRandom()+".png";
			String path = CommonUtils.getXtPathCache("xt_encoderqrcode_default_path").get(0).getXt_path();
			String relative_path = CommonUtils.getXtPathCache("xt_encoderqrcode_relative_path").get(0).getXt_path();
			File f = new File(path);
			if(!f.exists()){
				f.mkdirs();
			}
//			twoDimensionCode.encoderQRCode(xt_Encoderqrcode.getXt_encoderqrcode_url(), path+newName, "png");
			Xt_Attachment xt_Attachment = new Xt_Attachment();
			xt_Attachment.setXt_attachment_id(UUID.toUUID());
			xt_Attachment.setXt_attachmentCtime(CommonUtils.getSimpleDateFormat());
			xt_Attachment.setXt_attachmentName(newName);
			xt_Attachment.setXt_attachmentPath(relative_path+newName);
			xt_Attachment.setXt_userinfo_id(CommonUtils.getXtUid());
			xt_Attachment.setXt_attachmentType("image/png");
			xt_Attachment.setXt_attachmentTitle(xt_Encoderqrcode.getXt_encoderqrcode_title());
			xt_Attachment.setXt_userinfo_id(CommonUtils.getXtUid());
			xt_AttachmentService.addXtAttachment(xt_Attachment);
			xt_Encoderqrcode.setXt_attachment_id(xt_Attachment.getXt_attachment_id());
			i = xt_EncoderqrcodeDao.addXtEncoderqrcode(xt_Encoderqrcode);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 修改
	* @param xt_encoderqrcode 
	* @return
	*/
	@SuppressWarnings("static-access")
	public int updateXtEncoderqrcode(Xt_Encoderqrcode xt_Encoderqrcode){
		int i = 0;
		try {
//			TwoDimensionCode twoDimensionCode = new TwoDimensionCode();
			String newName = AllUtils.getRandom()+".png";
			String path = CommonUtils.getXtPathCache("xt_encoderqrcode_default_path").get(0).getXt_path();
			String relative_path = CommonUtils.getXtPathCache("xt_encoderqrcode_relative_path").get(0).getXt_path();
			File f = new File(path);
			if(!f.exists()){
				f.mkdirs();
			}
//			twoDimensionCode.encoderQRCode(xt_Encoderqrcode.getXt_encoderqrcode_url(), path+newName, "png");
			Xt_Attachment xt_Attachment = new Xt_Attachment();
			xt_Attachment.setXt_attachment_id(UUID.toUUID());
			xt_Attachment.setXt_attachmentCtime(CommonUtils.getSimpleDateFormat());
			xt_Attachment.setXt_attachmentName(newName);
			xt_Attachment.setXt_attachmentPath(relative_path+newName);
			xt_Attachment.setXt_userinfo_id(CommonUtils.getXtUid());
			xt_Attachment.setXt_attachmentType("image/png");
			xt_Attachment.setXt_attachmentTitle(xt_Encoderqrcode.getXt_encoderqrcode_title());
			xt_Attachment.setXt_userinfo_id(CommonUtils.getXtUid());
			xt_AttachmentService.addXtAttachment(xt_Attachment);
			xt_Encoderqrcode.setXt_attachment_id(xt_Attachment.getXt_attachment_id());
			i = xt_EncoderqrcodeDao.updateXtEncoderqrcode(xt_Encoderqrcode);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
	/**
	* 删除
	* @param condition 
	* @return
	*/
	public int delXtEncoderqrcode(Map<String,Object> condition){
		int i = 0;
		try {
			i = xt_EncoderqrcodeDao.delXtEncoderqrcode(condition);
		} catch (Exception e) {
			i = 0;
			/**方案一加上这句话这样程序异常时才能被aop捕获进而回滚**/
			throw new ExceptionUtil(e.getMessage(),e.getCause());
		}
		return i;
	}
}
