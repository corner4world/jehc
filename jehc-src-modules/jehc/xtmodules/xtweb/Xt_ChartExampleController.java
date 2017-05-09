package jehc.xtmodules.xtweb;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jehc.xtmodules.xtcore.base.BaseAction;
/**
* 报表案例
* 2015-05-24 08:36:53  邓纯杰
*/
@Controller
@RequestMapping("/xtChartExampleController")
public class Xt_ChartExampleController extends BaseAction {
	/**
	 * 载入到折线图表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadXtLineSpline",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtChart(HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-chartexample/xt-line-spline");
	} 
	
	/**
	 * 载入到bar_basic_3d图表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadXtBarBasic3d",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtBarBasic3d(HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-chartexample/xt-bar-basic-3d");
	}
	
	/**
	 * 载入到xt-clustered图表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadXtClustered",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtClustered(HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-chartexample/xt-clustered");
	}
	
	/**
	 * 载入到xt_scatter图表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadXtScatter",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtScatter(HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-chartexample/xt-scatter");
	}
	/**
	 * 载入到xt_pie图表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadXtPie",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtPie(HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-chartexample/xt-pie");
	}
	/**
	 * 载入到xt_animated图表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loadXtAnimated",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtAnimated(HttpServletRequest request) {
		return new ModelAndView("pc/xt-view/xt-chartexample/xt-animated");
	}
	
	/**
	 * 载入到xt-area-chart图表页面
	 * @return
	 */
	@RequestMapping(value="/loadXtAreaChart",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView loadXtAreaChart(){
		return new ModelAndView("pc/xt-view/xt-chartexample/xt-area-chart");
	}
}
