package jehc.xtmodules.xtmodel;

import java.io.Serializable;

/**
* xt_unit 商品(产品)单位; InnoDB free: 6144 kB 
* 2015-08-29 11:02:35  邓纯杰
*/
public class Xt_Unit implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_unit_id;/**单位编号**/
	private String xt_unitName;/**单位名称**/
	public void setXt_unit_id(String xt_unit_id){
		this.xt_unit_id=xt_unit_id;
	}
	public String getXt_unit_id(){
		return xt_unit_id;
	}
	public void setXt_unitName(String xt_unitName){
		this.xt_unitName=xt_unitName;
	}
	public String getXt_unitName(){
		return xt_unitName;
	}
}
