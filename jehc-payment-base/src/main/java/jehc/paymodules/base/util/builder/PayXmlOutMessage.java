package jehc.paymodules.base.util.builder;
import jehc.paymodules.base.model.MsgType;
import jehc.paymodules.base.model.PayOutMessage;
public class PayXmlOutMessage extends PayOutMessage{
	private static final long serialVersionUID = 9172608744040819688L;
	private String code;
    public PayXmlOutMessage() {
        this.msgType = MsgType.xml.name();
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String toMessage() {
       return "<xml><return_code><![CDATA[" + code + "]]></return_code><return_msg><![CDATA[" + content + "]]></return_msg></xml>";
    }
}
