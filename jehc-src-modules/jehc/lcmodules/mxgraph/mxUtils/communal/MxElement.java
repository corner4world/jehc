package jehc.lcmodules.mxgraph.mxUtils.communal;

import java.util.List;

import org.dom4j.Element;

/**
 * 五级节点集合
 * @author邓纯杰
 *
 */
public class MxElement {
	private List<Element> mxCellChildList;/**子节点**/
	private List<Element> mxCellEdgeList;/**连线**/
	private List<Element> mxCellOneList;/**一级节点**/
	private List<Element> mxCellTwoList;/**二级节点**/
	private List<Element> mxCellThreeList;/**三级节点**/
	private List<Element> mxCellFourList;/**四级节点**/
	private List<Element> mxCellFiveList;/**五级节点**/
	public List<Element> getMxCellOneList() {
		return mxCellOneList;
	}
	public void setMxCellOneList(List<Element> mxCellOneList) {
		this.mxCellOneList = mxCellOneList;
	}
	public List<Element> getMxCellTwoList() {
		return mxCellTwoList;
	}
	public void setMxCellTwoList(List<Element> mxCellTwoList) {
		this.mxCellTwoList = mxCellTwoList;
	}
	public List<Element> getMxCellThreeList() {
		return mxCellThreeList;
	}
	public void setMxCellThreeList(List<Element> mxCellThreeList) {
		this.mxCellThreeList = mxCellThreeList;
	}
	public List<Element> getMxCellFourList() {
		return mxCellFourList;
	}
	public void setMxCellFourList(List<Element> mxCellFourList) {
		this.mxCellFourList = mxCellFourList;
	}
	public List<Element> getMxCellFiveList() {
		return mxCellFiveList;
	}
	public void setMxCellFiveList(List<Element> mxCellFiveList) {
		this.mxCellFiveList = mxCellFiveList;
	}
	public List<Element> getMxCellChildList() {
		return mxCellChildList;
	}
	public void setMxCellChildList(List<Element> mxCellChildList) {
		this.mxCellChildList = mxCellChildList;
	}
	public List<Element> getMxCellEdgeList() {
		return mxCellEdgeList;
	}
	public void setMxCellEdgeList(List<Element> mxCellEdgeList) {
		this.mxCellEdgeList = mxCellEdgeList;
	}
}
