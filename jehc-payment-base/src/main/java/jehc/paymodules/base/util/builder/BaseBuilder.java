package jehc.paymodules.base.util.builder;

import jehc.paymodules.base.model.PayOutMessage;

public abstract class BaseBuilder<BuilderType, ValueType> {
    public abstract ValueType build();
    
    public void setCommon(PayOutMessage m) {}
}