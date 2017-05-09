package jehc.xtmodules.xtmodel;

import java.io.Serializable;

import jehc.xtmodules.xtcore.base.BaseEntity;

/**
 * 系统菜单表
 * 
 * @author邓纯杰
 * 
 */
public class Xt_Menuinfo extends BaseEntity  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String xt_menuinfo_id;//ID编号
	private String xt_menuinfo_title;//标题
	private String xt_menuinfo_url;//URL地址
	private String xt_menuinfo_iconCls;//图标
	private String xt_menuinfo_leaf;//是否存在子节点
	private String xt_menuinfo_parentId;//父ID
	private String xt_menuinfo_images;//图片路径
	private int xt_menuinfo_sort;//排序
	private int xt_menuinfo_sys;//是否是平台菜单0否1是
	private String xt_menuinfo_status;//状态0可用1禁用

	public String getXt_menuinfo_status() {
		return xt_menuinfo_status;
	}

	public void setXt_menuinfo_status(String xt_menuinfo_status) {
		this.xt_menuinfo_status = xt_menuinfo_status;
	}

	public String getXt_menuinfo_id() {
		return xt_menuinfo_id;
	}

	public void setXt_menuinfo_id(String xt_menuinfo_id) {
		this.xt_menuinfo_id = xt_menuinfo_id;
	}

	public String getXt_menuinfo_title() {
		return xt_menuinfo_title;
	}

	public void setXt_menuinfo_title(String xt_menuinfo_title) {
		this.xt_menuinfo_title = xt_menuinfo_title;
	}

	public String getXt_menuinfo_url() {
		return xt_menuinfo_url;
	}

	public void setXt_menuinfo_url(String xt_menuinfo_url) {
		this.xt_menuinfo_url = xt_menuinfo_url;
	}

	public String getXt_menuinfo_iconCls() {
		return xt_menuinfo_iconCls;
	}

	public void setXt_menuinfo_iconCls(String xt_menuinfo_iconCls) {
		this.xt_menuinfo_iconCls = xt_menuinfo_iconCls;
	}

	public String getXt_menuinfo_leaf() {
		return xt_menuinfo_leaf;
	}

	public void setXt_menuinfo_leaf(String xt_menuinfo_leaf) {
		this.xt_menuinfo_leaf = xt_menuinfo_leaf;
	}

	public String getXt_menuinfo_parentId() {
		return xt_menuinfo_parentId;
	}

	public void setXt_menuinfo_parentId(String xt_menuinfo_parentId) {
		this.xt_menuinfo_parentId = xt_menuinfo_parentId;
	}

	public String getXt_menuinfo_images() {
		return xt_menuinfo_images;
	}

	public void setXt_menuinfo_images(String xt_menuinfo_images) {
		this.xt_menuinfo_images = xt_menuinfo_images;
	}

	public int getXt_menuinfo_sort() {
		return xt_menuinfo_sort;
	}

	public void setXt_menuinfo_sort(int xt_menuinfo_sort) {
		this.xt_menuinfo_sort = xt_menuinfo_sort;
	}

	public int getXt_menuinfo_sys() {
		return xt_menuinfo_sys;
	}

	public void setXt_menuinfo_sys(int xt_menuinfo_sys) {
		this.xt_menuinfo_sys = xt_menuinfo_sys;
	}
}
