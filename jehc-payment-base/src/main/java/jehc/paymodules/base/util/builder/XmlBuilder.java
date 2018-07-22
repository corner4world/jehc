package jehc.paymodules.base.util.builder;

import jehc.paymodules.base.model.PayOutMessage;
/**
 *  <p> source chanjarster/weixin-java-tools</p>
 */
public class XmlBuilder extends BaseBuilder<XmlBuilder, PayOutMessage> {
    private String content;
    private String code;
    public XmlBuilder content(String content) {
        this.content = content;
        return this;
    }
    public XmlBuilder code(String code) {
        this.code = code;
        return this;
    }
    public PayOutMessage build() {
        PayXmlOutMessage message = new PayXmlOutMessage();
        setCommon(message);
        message.setContent(content);
        message.setCode(code);
        return message;
    }
}
