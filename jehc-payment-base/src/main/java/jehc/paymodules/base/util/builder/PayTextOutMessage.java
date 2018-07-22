package jehc.paymodules.base.util.builder;
import jehc.paymodules.base.model.MsgType;
import jehc.paymodules.base.model.PayOutMessage;
public class PayTextOutMessage extends PayOutMessage{
	private static final long serialVersionUID = -7912100210519988988L;
	public PayTextOutMessage() {
        this.msgType = MsgType.text.name();
    }
    public String toMessage() {
        return getContent();
    }
}
