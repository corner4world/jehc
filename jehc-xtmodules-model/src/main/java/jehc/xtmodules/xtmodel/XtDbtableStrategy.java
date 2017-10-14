package jehc.xtmodules.xtmodel;
import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
* xt_dbtable_strategy 分表策略表 
* 2017-02-14 16:23:48  邓纯杰
*/
public class XtDbtableStrategy extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_dbtable_strategy_id;/**策略编号**/
	private String tableName;/**数据库表名**/
	private String cTime;/**创建时间**/
	private String onlyModulesFlag;/**唯一模块标识**/
	public void setXt_dbtable_strategy_id(String xt_dbtable_strategy_id){
		this.xt_dbtable_strategy_id=xt_dbtable_strategy_id;
	}
	public String getXt_dbtable_strategy_id(){
		return xt_dbtable_strategy_id;
	}
	public void setTableName(String tableName){
		this.tableName=tableName;
	}
	public String getTableName(){
		return tableName;
	}
	public void setCTime(String cTime){
		this.cTime=cTime;
	}
	public String getCTime(){
		return cTime;
	}
	public void setOnlyModulesFlag(String onlyModulesFlag){
		this.onlyModulesFlag=onlyModulesFlag;
	}
	public String getOnlyModulesFlag(){
		return onlyModulesFlag;
	}
}
