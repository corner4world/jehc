package jehc.xtmodules.xtcore.util;

import java.util.ArrayList;
import java.util.List;

import jehc.xtmodules.xtmodel.XtMenuinfo;

public class IndexTree {
	private List<XtMenuinfo> nodes;

	public IndexTree(List<XtMenuinfo> nodes) {
		this.nodes = nodes;
	}

	public String buildTree() {
		StringBuffer html = new StringBuffer();
		for(XtMenuinfo node:nodes){
			if ("0".equals(node.getXt_menuinfo_parentId())) {
				html.append("<li class=\"nav-item\" id=\"menu"+node.getXt_menuinfo_id()+"\">");
				//根目录开始
				if(node.getXt_menuinfo_leaf().equals("0")){
					html.append("\r\n<a href=\"javascript:;\" class=\"nav-link nav-toggle\">");
				}else{
//					html.append("\r\n<a href=\"javascript:;\" class=\"nav-link\">");
					//一级菜单如果没有子级则可以连接
					html.append("\r\n<a href='../"+node.getXt_menuinfo_url()+"' class=\"nav-link J_menuItem\" data-index='"+node.getXt_menuinfo_id()+"' rootId='"+node.getXt_menuinfo_id()+"'>");
				}
				html.append("\r\n<i class=\""+node.getXt_menuinfo_iconCls()+"\"></i>");
				html.append("\r\n<span class=\"title\">"+node.getXt_menuinfo_title()+"</span>");
				if(node.getXt_menuinfo_leaf().equals("0")){
					html.append("\r\n<span class=\"arrow \"></span>");
				}
				html.append("\r\n <span class=\"selected\"></span>");
				html.append("\r\n</a>");
				//根目录结束
				//递归子目录开始
				html.append(build(node,node.getXt_menuinfo_id()));
				//递归子目录结束
				html.append("\r\n</li>");
			}
		}
		return html.toString();
	}
	
	private String build(XtMenuinfo node,String rootId) {
		StringBuffer html = new StringBuffer();
		List<XtMenuinfo> children = getChildren(node);
		if (!children.isEmpty()) {
			html.append("\r\n<ul class=\"sub-menu\">");
			for (XtMenuinfo child:children) {
				html.append("\r\n<li class=\"nav-item\" id=\"menu"+child.getXt_menuinfo_id()+"\">");
				if(!child.getXt_menuinfo_leaf().equals("0")){
					//不存在下级菜单
//					html.append("\r\n<a href=\"javascript:clickAddTab('"+child.getXt_menuinfo_url()+"','"+child.getXt_menuinfo_title()+"','"+child.getXt_menuinfo_id()+"','"+rootId+"','"+idBu(child.getXt_menuinfo_parentId())+"');\" class=\"nav-link\">");
					 html.append("\r\n<a  href='../"+child.getXt_menuinfo_url()+"' class=\"nav-link J_menuItem\" data-index='"+child.getXt_menuinfo_id()+"' rootId='"+rootId+"' idBu='"+idBu(child.getXt_menuinfo_parentId())+"'>");
				}else{
					//存在下级菜单
					html.append("\r\n<a href=\"javascript:;\" class=\"nav-link nav-toggle\">");
				}
				html.append("\r\n"+child.getXt_menuinfo_title()+"");
//				html.append("\r\n<i class=\""+child.getXt_menuinfo_iconCls()+"\"></i> "+child.getXt_menuinfo_title()+"");//二级图标不再显示
				if(child.getXt_menuinfo_leaf().equals("0")){
					//存在下级菜单
					html.append("\r\n<span class=\"arrow\"></span>");
					html.append("\r\n <span class=\"selected\"></span>");
				}
				html.append("\r\n</a>");
				//继续递归
				String buildSbf = build(child,rootId);
				html.append(buildSbf);
				html.append("\r\n</li>");
			}
			html.append("\r\n</ul>");
		}
		return html.toString();
	}

	private List<XtMenuinfo> getChildren(XtMenuinfo node) {
		List<XtMenuinfo> children = new ArrayList<XtMenuinfo>();
		for (XtMenuinfo child:nodes) {
			if (child.getXt_menuinfo_parentId().equals(node.getXt_menuinfo_id())) {
				children.add(child);
			}
		}
		return children;
	}
	
	private String idBu(String id){
		StringBuffer ids = new StringBuffer();
		return ids.append(idList(id)).toString();
	}
	private String idList(String id){
		StringBuffer ids = new StringBuffer();
		for(XtMenuinfo node: nodes){
			if(id.equals(node.getXt_menuinfo_id())){
				if(null != node.getXt_menuinfo_parentId() && !"0".equals(node.getXt_menuinfo_parentId())){
					ids.append(node.getXt_menuinfo_id()+",");
					ids.append(idList(node.getXt_menuinfo_parentId()));
				}else{
					ids.append(node.getXt_menuinfo_id());
				}
			}
		}
		return ids.toString();
	}
}
