package jehc.lcmodules.mxgraph.mxUtils.communal;

import java.util.List;

import org.dom4j.Element;


/**
 * 连线坐标
 * @author邓纯杰
 *
 */
public class CoordinateUtils {

	/**
	 * 连线坐标获取算法
	 * @param mxCell_agin节点
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getCoordinate(Element mxCell_agin){
		String g = "";
		List<Element> mxGeometryLabelElements = mxCell_agin.elements();  
		String mxPoint_x = "";
		String mxPoint_y = "";
		String label_x = "";
		String label_y="";
		String mxPoint_x_y="";
        for (Element mxGeometryLabel:mxGeometryLabelElements) {  
        	//距离连线位置的XY位置坐标
        	label_x = mxGeometryLabel.attributeValue("x");
        	label_y = mxGeometryLabel.attributeValue("y");
        	////////查找中心点位置坐标/////////
        	//1.获取<Array as="points">节点
        	List<Element> arrayElements = mxGeometryLabel.elements();
        	for(Element arrayElement:arrayElements){
        		List<Element> mxPointElements = arrayElement.elements();
        		//2.获取<mxPoint x="" y=""/>
        		for(Element mxPointElement:mxPointElements){
        			mxPoint_x = mxPointElement.attributeValue("x");
        			mxPoint_y = mxPointElement.attributeValue("y");
        			if(null != mxPoint_x && !"".equals(mxPoint_x) && null != mxPoint_y && !"".equals(mxPoint_y)){
        				String [] mxPoint_x_list = mxPoint_x.split("\\.");
        				String [] mxPoint_y_list = mxPoint_y.split("\\.");
        				mxPoint_x = mxPoint_x_list[0];
        				mxPoint_y = mxPoint_y_list[0];
        				if(null != mxPoint_x_y && !"".equals(mxPoint_x_y)){
        					mxPoint_x_y = mxPoint_x_y+";"+mxPoint_x+","+mxPoint_y;
        				}else{
        					mxPoint_x_y = mxPoint_x+","+mxPoint_y;
        				}
        			}
        		}
        	}
        }  
        if(null != mxPoint_x_y && !"".equals(mxPoint_x_y)){
        	g=mxPoint_x_y;
        }else{
        	g="";
        }
        if(null != g && !"".equals(g)){
        	if(!"".equals(label_x) && null != label_x && null != label_y && !"".equals(label_y)){
        		String[] label_x_list = label_x.split("\\.");
        		String[] label_y_list = label_y.split("\\.");
        		label_x = label_x_list[0];
        		label_y = label_y_list[0];
        		g=g+":"+label_x+","+label_y;
            }else{
            	g=g+":"+"0,0";
            }
        }else{
        	if(!"".equals(label_x) && null != label_x && null != label_y && !"".equals(label_y)){
        		String[] label_x_list = label_x.split("\\.");
        		String[] label_y_list = label_y.split("\\.");
        		label_x = label_x_list[0];
        		label_y = label_y_list[0];
        		g=label_x+","+label_y;
            }else{
            	g="0,0";
            }
        }
		return g;
	}
}
