package jehc.paymodules.base.util.builder;
import jehc.paymodules.base.model.MsgType;
import jehc.paymodules.base.model.PayOutMessage;
public class PayJsonOutMessage extends PayOutMessage{
	private static final long serialVersionUID = 2148295094626032139L;
	public PayJsonOutMessage() {
        this.msgType = MsgType.json.name();
    }
    public String toMessage() {
        return getContent();
    }
}
