package jehc.xtmodules.xtcore.util;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jehc.xtmodules.xtmodel.XtMenuinfo;

public class AdminTree {
	private List<XtMenuinfo> nodes;

	public AdminTree(List<XtMenuinfo> nodes) {
		this.nodes = nodes;
	}

	public String buildTree() {
		StringBuffer html = new StringBuffer();
		for(XtMenuinfo node:nodes){
			if ("0".equals(node.getXt_menuinfo_parentId()) && node.getKeyid().equals("jEhcDevModule")) {
				html.append("<li class=\"m-menu__item  m-menu__item--active  m-menu__item--submenu m-menu__item--rel\" id=\"menu"+node.getXt_menuinfo_id()+"\" m-menu-submenu-toggle=\"click\" aria-haspopup=\"true\">");
					//根目录开始
					if(node.getXt_menuinfo_leaf().equals("0")){
						html.append("<a href=\"javascript:;\" class=\"m-menu__link m-menu__toggle\">");
					}else{
						//一级菜单如果没有子级则可以连接
						html.append("<a href='../"+node.getXt_menuinfo_url()+"' class=\"m-menu__link m-menu__toggle J_menuItem\" data-index='"+node.getXt_menuinfo_id()+"' rootId='"+node.getXt_menuinfo_id()+"'>");
					}
//					html.append("<i class=\"m-menu__hor-arrow "+node.getXt_menuinfo_iconCls()+"\"></i>");
					html.append("<span class=\"m-menu__link-text\">"+node.getXt_menuinfo_title()+"</span>");
					html.append("<i class=\"m-menu__hor-arrow la la-angle-down\"></i>");
					html.append("<i class=\"m-menu__ver-arrow la la-angle-right\"></i>");
					html.append("</a>");
					//根目录结束
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
			html.append("<div class=\"m-menu__submenu m-menu__submenu--classic m-menu__submenu--right \">");
				//遍历子级菜单----1将父级信息作为子级标题带入开始
				html.append("<span class=\"m-menu__arrow\"></span>");
				//遍历子级菜单----1将父级信息作为子级标题带入开始
				html.append("<ul class=\"m-menu__subnav\">");
//					//遍历子级菜单----1将父级信息作为子级标题带入开始
//					html.append("<li class=\"m-menu__item  m-menu__item--submenu\" m-menu-submenu-toggle=\"hover\" m-menu-link-redirect=\"1\" aria-haspopup=\"true\" >");
//						html.append("<span class=\"m-menu__link\">");
//							//此处为父级标题名称
//							html.append("<span class=\"m-menu__link-text\">"+node.getXt_menuinfo_title()+"</span>");
//						html.append("</span>");
//					//遍历子级菜单----1将父级信息作为子级标题带入结束
					html.append("</li>");
				//遍历子目录时（父级除外的下级菜单）
				for (XtMenuinfo child:children) {
					boolean existChild=false;
					String existChildText = "";
					if(child.getXt_menuinfo_leaf().equals("0")){
						existChild = true;
						existChildText = " class=\"m-menu__item  m-menu__item--submenu\" aria-haspopup=\"true\"  m-menu-submenu-toggle=\"hover\" m-menu-link-redirect=\"1\"";
					}else{
						existChildText = " class=\"m-menu__item \" aria-haspopup=\"true\"  m-menu-link-redirect=\"1\"";
					}
					html.append("<li "+existChildText+" id=\"menu"+child.getXt_menuinfo_id()+"\">");
					if(!child.getXt_menuinfo_leaf().equals("0")){
						//不存在下级菜单
						 html.append("<a  href='../"+child.getXt_menuinfo_url()+"' class=\"m-menu__link m-menu__toggle  J_menuItem\" data-index='"+child.getXt_menuinfo_id()+"' rootId='"+rootId+"' idBu='"+idBu(child.getXt_menuinfo_parentId())+"'>");
					}else{
						//存在下级菜单
						html.append("<a href=\"javascript:;\" class=\"m-menu__link m-menu__toggle\">");
					}
							//a标签中内容i-span-text
							html.append("<i class=\"m-menu__link-icon "+child.getXt_menuinfo_iconCls()+"\">");
								html.append("<span></span>");
							html.append("</i>");
							html.append("<span class=\"m-menu__link-text\">");
								html.append(""+child.getXt_menuinfo_title()+"");//菜单标题
							html.append("</span>");
							if(existChild){
								html.append("<i class=\"m-menu__hor-arrow la la-angle-right\"></i>");
								html.append("<i class=\"m-menu__ver-arrow la la-angle-right\"></i>");
							}
						html.append("</a>");
						//递归
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
