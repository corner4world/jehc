package jehc.xtmodules.xtcore.util;

import java.util.ArrayList;
import java.util.List;

import jehc.xtmodules.xtmodel.XtMenuinfo;

public class IndexTree {
	private List<XtMenuinfo> nodes;

	public IndexTree(List<XtMenuinfo> nodes) {
		this.nodes = nodes;
	}
/*
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
//					html.append("\r\n<span class=\"arrow \" style=\"padding-top:18px\"></span>");
					html.append("\r\n<span class=\" \" style=\"padding-top:18px\"></span>");
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
					html.append("\r\n<span class=\"arrow\" style=\"padding-top:8px\"></span>");
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
	}*/
	
	public String buildTree(boolean adminText) {
		StringBuffer html = new StringBuffer();
		for(XtMenuinfo node:nodes){
			if ("0".equals(node.getXt_menuinfo_parentId()) && !node.getKeyid().equals("jEhcDevModule")) {
				html.append("<li class=\"m-menu__item  m-menu__item--submenu\" id=\"menu"+node.getXt_menuinfo_id()+"\" aria-haspopup=\"true\" m-menu-submenu-toggle=\"hover\" m-menu-link-redirect=\"1\" >");
					//根目录开始
					if(node.getXt_menuinfo_leaf().equals("0")){
						html.append("<a href=\"javascript:;\" class=\"m-menu__link m-menu__toggle\">");
					}else{
						//一级菜单如果没有子级则可以连接
						html.append("<a href='../"+node.getXt_menuinfo_url()+"' class=\"m-menu__link m-menu__toggle J_menuItem\" data-index='"+node.getXt_menuinfo_id()+"' rootId='"+node.getXt_menuinfo_id()+"'>");
					}
					html.append("<i class=\"m-menu__link-icon "+node.getXt_menuinfo_iconCls()+"\"></i>");
					html.append("<span class=\"m-menu__link-title\">");
						html.append("<span class=\"m-menu__link-wrap\"> ");
							html.append("<span class=\"m-menu__link-text\">"+node.getXt_menuinfo_title()+"</span>");
						html.append("</span>");
					html.append("</span>");
					html.append("<i class=\"m-menu__ver-arrow la la-angle-right\"></i>");
					html.append("</a>");
					//根目录结束
					//递归子目录
					html.append(build(node,node.getXt_menuinfo_id()));
				html.append("</li>");
			}
			
		}
		return html.toString();
	}
	
	private String build(XtMenuinfo node,String rootId) {
		StringBuffer html = new StringBuffer();
		List<XtMenuinfo> children = getChildren(node);
		if (!children.isEmpty()) {
			//遍历子级菜单----1将父级信息作为子级标题带入开始
			html.append("<div class=\"m-menu__submenu \">");
				//遍历子级菜单----1将父级信息作为子级标题带入开始
				html.append("<span class=\"m-menu__arrow\"></span>");
				//遍历子级菜单----1将父级信息作为子级标题带入开始
				html.append("<ul class=\"m-menu__subnav\">");
//					//遍历子级菜单----1将父级信息作为子级标题带入开始
//					html.append("<li class=\"m-menu__item  m-menu__item--submenu\" aria-haspopup=\"true\" m-menu-submenu-toggle=\"hover\" m-menu-link-redirect=\"1\" >");
//						html.append("<span class=\"m-menu__link\">");
//							//此处为父级标题名称
//							html.append("<span class=\"m-menu__link-text\">"+node.getXt_menuinfo_title()+"</span>");
//						html.append("</span>");
//					//遍历子级菜单----1将父级信息作为子级标题带入结束
//					html.append("</li>");
				//遍历子目录时（父级除外的下级菜单）
				for (XtMenuinfo child:children) {
					boolean existChild=false;
					String existChildText = "";
					if(child.getXt_menuinfo_leaf().equals("0")){
						existChild = true;
						existChildText = " class=\"m-menu__item  m-menu__item--submenu\" aria-haspopup=\"true\" m-menu-submenu-toggle=\"hover\" m-menu-link-redirect=\"1\" ";
					}else{
						existChildText = " class=\"m-menu__item \" aria-haspopup=\"true\"  m-menu-link-redirect=\"1\"";
					}
					html.append("<li "+existChildText+" id=\"menu"+child.getXt_menuinfo_id()+"\">");
					if(!child.getXt_menuinfo_leaf().equals("0")){
						//不存在下级菜单
						 html.append("<a  href='../"+child.getXt_menuinfo_url()+"' class=\"m-menu__link m-menu__toggle J_menuItem\" data-index='"+child.getXt_menuinfo_id()+"' rootId='"+rootId+"' idBu='"+idBu(child.getXt_menuinfo_parentId())+"'>");
					}else{
						//存在下级菜单
						html.append("<a href=\"javascript:;\" class=\"m-menu__link m-menu__toggle\">");
					}
						//a标签中内容i-span-text
						html.append("<i class=\"m-menu__link-icon "+child.getXt_menuinfo_iconCls()+"\"></i>");
						html.append("<span class=\"m-menu__link-text\">"+""+child.getXt_menuinfo_title()+"</span>");
						if(existChild){
							html.append("<i class=\"m-menu__ver-arrow la la-angle-right\"></i>");
						}
					html.append("</a>");
					//递归子目录
					String buildSbf = build(child,rootId);
					html.append(buildSbf);
					html.append("</li>");
				}
				html.append("</ul>");
			html.append("</div>");
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
