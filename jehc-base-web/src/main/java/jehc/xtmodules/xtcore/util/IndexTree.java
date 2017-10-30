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
				html.append("<li class=\"nav-item\">");
				//根目录开始
//				html.append("\r\n<a href=\"javascript:clickAddTab('"+node.getXt_menuinfo_url()+"','"+node.getXt_menuinfo_title()+"','"+node.getXt_menuinfo_id()+"');\" class=\"nav-link nav-toggle\">");
				html.append("\r\n<a href=\"javascript:;\" class=\"nav-link nav-toggle\">");
				html.append("\r\n<i class=\""+node.getXt_menuinfo_iconCls()+"\"></i>");
				html.append("\r\n<span class=\"title\">"+node.getXt_menuinfo_title()+"</span>");
				if(node.getXt_menuinfo_leaf().equals("0")){
					html.append("\r\n<span class=\"arrow \"></span>");
				}
				html.append("\r\n</a>");
				//根目录结束
				//递归子目录开始
				html.append(build(node));
				//递归子目录结束
				html.append("\r\n</li>");
			}
		}
		return html.toString();
	}
	
	private String build(XtMenuinfo node) {
		StringBuffer html = new StringBuffer();
		List<XtMenuinfo> children = getChildren(node);
		if (!children.isEmpty()) {
			html.append("\r\n<ul class=\"sub-menu\">");
			for (XtMenuinfo child:children) {
				html.append("\r\n<li class=\"nav-item\">");
				if(!child.getXt_menuinfo_leaf().equals("0")){
					html.append("\r\n<a href=\"javascript:clickAddTab('"+child.getXt_menuinfo_url()+"','"+child.getXt_menuinfo_title()+"','"+child.getXt_menuinfo_id()+"');\" class=\"nav-link\">");
				}else{
					html.append("\r\n<a href=\"javascript:;\" class=\"nav-link nav-toggle\">");
				}
				html.append("\r\n<i class=\""+child.getXt_menuinfo_iconCls()+"\"></i> "+child.getXt_menuinfo_title()+"");
				if(child.getXt_menuinfo_leaf().equals("0")){
					html.append("\r\n<span class=\"arrow\"></span>");
				}
				html.append("\r\n</a>");
				//继续递归
				html.append(build(child));
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
}
