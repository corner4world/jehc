package jehc.paymodules.base.util.builder;

import jehc.paymodules.base.model.PayOutMessage;

/**
 * <p> source chanjarster/weixin-java-tools</p>
 */
public class TextBuilder extends BaseBuilder<TextBuilder, PayOutMessage> {
    private String content;
    public TextBuilder content(String content) {
        this.content = content;
        return this;
    }
    public PayOutMessage build() {
        PayTextOutMessage message = new PayTextOutMessage();
        setCommon(message);
        message.setContent(content);
        return message;
    }
}
