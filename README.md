JEHC开源开发文档

简介
平台版本开发版本分为Eclipse和MyEclipse两个版本，采用技术为：spring，springMVC，Mybatis，Activiti5，（Activiti可视化设计器基于IE，火狐，谷歌，360等浏览器），Solr4.10，Mysql，Netty，Redis，Ehcache，服务器监控模块，tk压缩，Extjs6.2，RPC服务 ，Junit单元测试，Logback与Log4j共用，同时融入了Hessian，session共享，数据库读写分离，MQ消息中间件等技术
交流及其他说明：
由于空余时间不多，只能写一部分开发文档模块 主要还是交流为目的
本平台采用开源方式，交流QQ群：330370132
项目地址：http://git.oschina.net/jehc/jehc

开发建议
建议开发人员开发环境以如下结构：






开发条件
1.Redis必须启动（若做session共享或其他你要扩展的redis缓存操作）
2．Solr如果被使用，则服务必须开启，本平台开发环境包括了SOlR4.10服务即独立应用
3．Mysql5.6++版本
4．平台采用统一上传文件管理（即必须使用建立文件工程如jehcFile工程专门用来存放文件或图片等，当然也可在JEHC工程中建立文件存储目录或你自定义文件工程。建立结束之后必须在JEHC平台中菜单-开发助手->配置中心->配置->平台路径模块进行配置如图：）
5．JDK版本在1.7++如果不采用SOLR则1.6可使用，Tomcat版本为7++
6. 注意（如果代码生成器过来的 表一定是存在主键并且一个 并且为UUID类型32位）
7．初始化用户名及密码admin/123456


工程目录
以Eclipse目录为主如图：

目录包括了xt模块，solr模块，流程模块，junit模块


系统模块中分为core，dao，model，service，web等模块，其中core中为核心工具类，配置文件，代码生成器，动态调度器 可打开进去详阅
（其他模块风格同上）


说明：webroot下deng为静态资源，view为相关模块JS
Web-inf下view为相关模块jsp 打开看即可 一目了然
核心JS模块如下：

如果你真正上线的时候可以将该js进行压缩或混淆 


数据库脚本存储目录



配置文件模块


JDBC连接配置


Logback配置文件


Solr及页面配置文件
功能模块
主要模块有
1.登录

2.平台主页面

3.开发助手模块（不再列举）

4.平台管理

5.流程事项

6.代码生成器模块

单表生成

一对多


生成之后例子如：






生成之后代码如下：

将这些代码放到工程里重启效果如下：

当然你也可以看看代码风格怎么样是不是很清晰
所生成控件可以是附件 可以是下拉框 可以是数字框，可以是文本域，可以是日期框 同时也可以生成查询信息
一对多同理
注意（如果代码生成器过来的 表一定是存在主键并且一个 并且为UUID类型32位）


功能开发说明
1.js模块
(1)gTopValue(id)获取TOP层元素
(2)sValue(id,val)赋值元素对象
(3)sTopValue(id,val)赋值TOP层元素
(4)hiddenTopBtn(id)隐藏TOP层按钮
(5)hiddenBtn(id)隐藏层按钮
(6)showTopBtn(id)显示TOP层按钮
(7)showBtn(id)显示层按钮
(8)gfiValue(form,itemId)获取form元素对象(条件根据form和itemId)
(9)sfiValue(form,itemId,val)赋值form元素对象(条件根据form和itemId)
(10)sfValue(form)通过form中id或name进行赋值
(11)sfValue(form,id_name,val)通过form中id或name进行取值
(12)sfReadOnly(form,id_name,isReadOnly)通过form中id或name进行设置只读或可读
(13)addTabContent(url,id,text,icon)将内容加到中间面板中
(14)showWaitMsg(msg)显示请求等待进度条窗口
(15)hideWaitMsg()隐藏请求等待进度条窗口
(16)hideTopWaitMsg()隐藏请求等待进度条窗口


(17)loadFormData(forms,url)载入form表单数据
实例:
loadFormData(lcApplyFormDetail,'../lcApplyController/getLcApplyById?lc_apply_id='+ record.items[0].data.lc_apply_id);

(18)loadFormDataCallBack(forms,url,fn)载入form表单数据并回调
实例:
loadFormDataCallBack(xtServiceCenterFormDetail,'../xtServiceCenterController/getXtServiceCenterById?xt_service_center_id=1',callFnDetail);
function callFnDetail(form, action){
	var xt_Service_Center_Parameter = action.result.data.xt_Service_Center_Parameter;
	for(var i = 0; i < xt_Service_Center_Parameter.length; i++){
		addDetailXtServiceCenterParameterFormDetail(xt_Service_Center_Parameter[i]);
	}
}

(19)提交form表单数据
param {} form:表单名称,url:地址,grids:列表,win:窗体,closeActionType:true时为hide false时为close,isReset:是否清空表单
submitForm(subForm,url,grids,win,isHide,isReset)

(20)提交form表单数据并回调函数
@param {} form:表单名称,url:地址,grids:列表,win:窗体,closeActionType:true时为hide false时为close,isReset:是否清空表单,fn:回调事件
submitFormCallFn(subForm,url,grids,win,isHide,isReset,fn)
实例：
submitFormCallFn(xtChangePwdFormAdd,'../xtChangePwdController/addXtChangePwd',null,null,false,true,backIndex)
function backIndex(form, action){
	Ext.Msg.confirm('提示',action.result.msg,function(btn){
		window.location.href=basePath;
	});
}


(21)提交form表单数据 并且返回form信息
@param {} form:表单名称,url:地址,grids:列表,win:窗体,closeActionType:true时为hide false时为close,isReset:是否清空表单,isCallForm:是否回调加载表单,callUrl:加载表单URL地址,callForm:回调表单名称
submitFormCallBack(subForm,url,grids,win,isHide,isReset,isCallForm,callUrl,callForm)
实例：
submitFormCallBack(xtCompanyFormEdit,'../xtCompanyController/delXtCompany',null,null,false,true,true,'../xtCompanyController/getXtCompany',xtCompanyFormEdit);

(22)提交form表单数据并追加参数
@param {} form:表单名称,url:地址,grids:列表,win:窗体,closeActionType:true时为hide false时为close,isReset:是否清空表单,params:追加参数提交
submitFormIncludeParams(subForm,url,grids,win,isHide,isReset,params)

实例：
var params = {solrIndexSqlJSON:encodeURI(getSolrIndexSqlJSON()),solrIndexSqlFiledJSON:encodeURI(getSolrIndexSqlFiledJSON())}
submitFormIncludeParams(solrEntityFormAdd,'../solrEntityController/addSolrEntity',grid,solrEntityWinAdd,false,true,params);

(23)提交form表单数据 刷新TreeGrid
@param {} form,url,treeGrid,win,closeActionType,isExpandAll:展开TreeGrid
submitForm(subForm,url,grids,win,isHide,isReset,isExpandAll)

(24)ajaxRequest(url,grids,params,msg)提交AjaxRequest表单数据
实例：
var params = {solr_core_id:solr_core_id};
ajaxRequest('../solrCoreController/delSolrCore',grid,params,'正在执行删除操作中！请稍后...');

(25)ajaxRequestCallFn(url,grids,params,msg,fn)提交AjaxRequest表单数据并回调方法
实例:
var params = {xt_loadinfo_id:xt_loadinfo_id};
ajaxRequestCallFn('../xtLoadinfoController/delXtLoadinfo',grid,params,'正在执行删除操作中！请稍后...',fnRefresh);
function fnRefresh(response, opts){
var obj=Ext.decode(response.responseText);
	xt_column_store.reload();
}

(26)ajaxReq(url,gridArray,params,msg)提交AjaxRequest表单数据 多grid提交
实例：
var params = {xt_role_id:xt_role_id};
var gridArray = [];
gridArray.push(grid);
gridArray.push(grid_deleted);
ajaxReq('../xtRoleinfoController/recoverXtRoleinfo',gridArray,params,'正在执行恢复操作中！请稍后...');

(27)reGetWidthAndHeight()获取高度和宽度
实例:
先调用reGetWidthAndHeight() 然后clientWidth及clientHeight都有值了

(28)reGetTopWidthAndHeight同上
(29)clearCookie(name)从缓存中清除Cookie
(30)getnavigator()获取浏览器版本
(31)selectAll(grid)全选grid
(32)unSelectAll(grid)反选grid
(33)refreshGrid(grid)刷新grid
(34)printerGrid(grid)打印grid
(35)exportExcel(grid,url)导出grid数据至excel
(36)exportExcelByCondition(url,searchForm)根据条件筛选导出
(37)resetTitle(panel,isSetBackGround)设置panel标题并判断是否设置背景样式

(38)panelPosition上部(north) ,中部(center), 左边(west) ,右部(east) ,底部(south)
	items 表单中元素
	isTop 表单所属位置
	labelPosition标签位置'top'上面 ，'left'左边 ，'right'右边
initSearchForm(panelPosition,items,isTop,labelPosition)

实例:
/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
		maxHeight:150,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'标题',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_encoderqrcode_title',
			name:'xt_encoderqrcode_title',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
initSearchForm('north',items,false,'left');
Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
});
(39)initSearchFormByUserdefined(panelPosition,items,isTop,labelPosition)自定义form查询扩展
(40)initSearch(store,url,searchForm)调用查询方法
实例：
/**查询操作**/
function search(){
	initSearch(store,'../xtEncoderqrcodeController/getXtEncoderqrcodeListByCondition',searchForm); 
}
(41)overLoad()页面加载完成
(42)initDataGrid(grid)与initData(dataStore,value) 重新设置Grid中列值与initData配合使用
实例：
{
	header:'性别',
	width:50,
	dataIndex:'xt_userinfo_sex',
	renderer:function(value){
			return initData(xtUserinfoSexList,value);
	}
}
(43)通用上传开始
/**
  *初始化附件右键
  *flag:标识符号1正常（即窗体在frame里面）2最外层显示（即窗体显示在最外层）
  *isUpAndDelete:表示是否拥有上传和删除功能1是2否（即明细页面使用） 如明细不需要上传 和删除功能
  *validateparameter:校验非法参数组装字符串
  *validateSize:校验大小
  *xt_path_absolutek:平台路径配置中心键（自定义上传绝对路径使用）
  *xt_path_relativek:平台路径配置中心键（自定义上传相对路径使用）
  *xt_path_urlk:平台路径配置中心键（自定义上传路径 自定义URL地址）
**/
initFileRight(fieldid,picid,flag,isUpAndDelete,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk)
实例：
（1）普通（在新增页面）
/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/
		initFileRight('xt_attachment','xt_attachment_pic',1,2);
/**初始化附件右键菜单结束**/


（2）高级自定义（在新增页面）
		/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
		initFileRight('xt_attachment','xt_attachment_pic',1,1,'zip,rar','1000000','ActivitiLc','ActivitiLcRelative');
		/**初始化附件右键菜单结束**/
  	   

(44)ajaxFilePathBackRequest(url,params,flag)表单中单个或多个附件路径回显    @param {} url,params,flag（标识符号1正常（即窗体在frame里面）2最外层显示（即窗体显示在最外层））
实例：
/**配置附件回显方法开始**/
	var params = {xt_attachment_id:record.items[0].data.xt_attachment,field_name:'xt_attachment'};
	ajaxFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,1);
	/**配置附件回显方法结束**/

(45)初始化右键 在TOP层
/**
  *初始化附件右键
  *isUpAndDelete表示是否拥有上传和删除功能1是2否（即明细页面使用） 如明细不需要上传 和删除功能
  *validateparameter:校验非法参数组装字符串
  *validateSize:校验大小 格式：1024-10240
  *xt_path_absolutek:平台路径配置中心键（自定义上传绝对路径使用）
  *xt_path_relativek:平台路径配置中心键（自定义上传相对路径使用）
  *xt_path_urlk:平台路径配置中心键（自定义上传路径 自定义URL地址）
**/
initTopFileRight(fieldid,picid,flag,isUpAndDelete,validateparameter,validateSize,xt_path_absolutek,xt_path_relativek,xt_path_urlk);
实例：
同上
(46)downOrExport(url)通过iFrame实现类ajax文件下载
(47)initrightgridcontextmenu(gd,contextmenu,items)初始化右键
实例：
/**调用右键**/
	initRight();
function initRight(){
		var contextmenu = new Ext.menu.Menu({
			id:'theContextMenu',
			items:[{
				text:'添 加',
				glyph:0xf016,
				id:'addLcProcessItem',
				handler:function(){addLcProcess();}
			},{
				text:'编 辑',
				glyph:0xf044,
				id:'updateLcProcessItem',
				handler:function(){updateLcProcess();}
			},{
				text:'删 除',
				glyph:0xf014,
				id:'delLcProcessItem',
				handler:function(){delLcProcess();}
			},'-',{
				text:'复制一行并生成记录',
				glyph:0xf0ea,
				id:'copyLcProcessItem',
				handler:function(){copyLcProcess();}
			},{
				text:'明 细',
				glyph:0xf03b,
				id:'detailLcProcessItem',
				handler:function(){detailLcProcess();}
			},{
				text:'导 出',
				glyph:0xf1c3,
				handler:function(){
					exportLcProcess(grid,'../lcProcessController/exportLcProcess');
				}
			},{
				text:'打 印',
				glyph:0xf02f,
				handler:function(){printerGrid(grid);}
			},'-',{
				text:'全 选',
				glyph:0xf046,
				handler:function(){selectAll(grid);}
			},{
				text:'反 选',
				glyph:0xf096,
				handler:function(){unSelectAll(grid);}
			},'-',{
				text:'刷 新',
				glyph:0xf021,
				handler:function(){refreshGrid(grid);}
			}]
		});
		initrightgridcontextmenu(grid,contextmenu,['updateLcProcessItem','delLcProcessItem','copyLcProcessItem','detailLcProcessItem']);
}
(48)filterBy(grid,text, by)根据字段筛选
实例:


2.数据权限
2.1mybatis层
	<select id="getXtNoticeListByCondition" resultType="jehc.xtmodules.xtmodel.Xt_Notice" parameterType="map">
		SELECT
			xt_notice.`xt_notice_id`,
			xt_notice.`xt_title`,
			xt_userinfo.`xt_userinfo_realName`
		FROM 
			xt_notice xt_notice LEFT JOIN xt_userinfo AS xt_userinfo ON xt_notice.xt_userinfo_id = 			 xt_userinfo.xt_userinfo_id
		WHERE 1=1
		<if test="null != xt_userinfo_id">
			AND xt_notice.`xt_userinfo_id` IN
			<foreach item="item" index="index" collection="xt_userinfo_id" open="(" separator="," close=")">
				#{item}
			</foreach>
		</if>
	</select>
描述：xt_userinfo_id 采用操作人作为数据权限例子 可自定义 如创建人 修改人等 controller会有传值描述
2.2controller层
	@ResponseBody
	@RequestMapping(value="/getXtNoticeListByCondition",method={RequestMethod.POST,RequestMethod.GET})
	public String getXtNoticeListByCondition(BaseSearch baseSearch,HttpServletRequest request){
		Map<String, Object> condition = baseSearch.convert();
		commonHPager(condition,request);
		dataAuthForXtUID(request,"xt_userinfo_id", condition);
		List<Xt_Notice> xt_NoticeList = xt_NoticeService.getXtNoticeListByCondition(condition);
		PageInfo<Xt_Notice> page = new PageInfo<Xt_Notice>(xt_NoticeList);
		return outPageStr(page,request);
	}
描述：xt_userinfo_id作为数据权限传入参数 可以自定义 如createId等
2.3view层
2.3.0配置页面功能

2.3.1list页面层


2.3.2编辑页面

强烈注意systemUID为数据权限唯一标识
3.工作流
介绍：
业务支撑lc_process

说明lc_process_uid;流程定义id编号，lc_process_uk;流程键 ，lc_process_bpmn_path activiti生成路径 该表保存了 流程定义 用于部署使用 包含生成了bpmn文件，bpmn的png图片 bpmn的zip压缩文件等 很重要 

业务支撑lc_deployment_his

说明：该历史发布版本即存放所有发布过的流程版本即 指定流程 指定发布历史记录 用于 每次业务中发起实例 取最新部署

相关方法
类型一平台自身提供相关主要方法
所在类Lc_ProcessController
1.createBPMN(Lc_Process lc_Process,MxGraphModel mxGraphModel,HttpServletRequest request,HttpServletResponse response)通过在线设计生成流程信息
2.generateBpmnAndImg(String url,String imgxml,String bpmn,String processName,String w,String h,HttpServletResponse response,Lc_Process lc_Process)生成bpmn,图片两个文件及压缩文件
3.createDeployment(String lc_process_id,HttpServletRequest request,HttpServletResponse response)部署流程
4.startProcessInstance(String lc_deployment_his_id)发起流程实例
5.suspendProcessInstanceById(String id)挂起流程实例（可作为撤销流程）
6.activateProcessInstanceById(String id)激活流程实例(开启流程实例)
7.completeTask(String taskId)完成任务
8.downFile(String lc_process_id,HttpServletRequest request,HttpServletResponse response)下载Bpmn文件
9.downFileImg(String lc_process_id,HttpServletRequest request,HttpServletResponse response)下载Img文件

activiti支撑的方法如下：
工具类：activitiUtil
1.createDeployment(File file)部署流程
2.deleteDeploymentById(String deploymentId)删除流程部署
3.getProcessDefinition(String deploymentId)获取流程定义对象
4.getProcessDefinitionByDid(String processDefinitionId)根据流程定义id查询流程定义
5.getProcessDefinitionList()获取所有流程定义集合
6.startProcessInstanceByKey(String key,String businessKey,Map<String, Object> variables)根据流程定义的key启动工作流实例
/**
	 * 设置跨节点提交
	 * 根据流程定义的key启动工作流实例
	 * 发起一个流程实例
	 * @param key流程部署
	 * @param businessKey业务键
	 * @param initialActivityId [设置跨节点提交]
	 * @param variables参数
	 * @return
*/
7.startProcessInstanceByKey(String key,String businessKey,String initialActivityId,Map<String, Object> variables)
8.getProcessInstanceById(String processInstanceId)根据流程实例获取流程对象
9.getProcessInstanceImageById(String processInstanceId,HttpServletResponse response)根据流程实例查找流程图
10.getProcessInstanceImageById(String processInstanceId,HttpServletResponse response)根据流程实例查找流程图
11.getActivityImageInfo(String executionId,String processInstanceId)获取流程图信息
12.getTask(String taskId) 根据任务ID获取activityId,businessKey[业务Key],variables[节点变量]
13.completeTask(String taskId,Variable variable)完成任务
14.claimTask(String taskId,String UID)根据用户ID+taskId签收任务
15.validatePEnd(String processInstanceID)判断流程实例是否结束
16.suspendProcessInstanceById(String processInstanceID)挂起流程实例（可作为撤销流程）
17.activateProcessInstanceById(String processInstanceID)激活流程实例(开启流程实例)
18.getActivityImpl(String taskID)根据任务ID获取ActivityImpl
19.nextTaskDefinition(String processInstanceID,String elString) 根据实例编号查找下一个任务节点
20.getNextNode(String procInstanceId)获取当前流程的下一个节点
21.getNextTaskDefinition(String taskID,String elString)根据任务ID查找下一步节点
22.nextTaskDefinition(ActivityImpl activityImpl,String activityId, String elString)下一个任务节点
23.getNextTaskDefinition(String processDefinitionId,String activityId,String elString,boolean flag)根据与流程定义processDefinitionId获取当前节点的下一个任务节点
24.getNextTaskDefinition(String taskID)获取下一步相关内容
25.getNextTaskDefinition(TaskDefinition taskDefinition)API通过下个节点 获取节点中各个属性
26.getProcessInstaceList()查询所有实例
27.getProcessInstaceList(String processDefinitionKey)根据流程定义Key查找所有实例
28.getProcessMap(ProcessInstance pi,ProcessDefinition processDefinition)获取活动任务
29.getProcessInstanceImg(String processInstanceId)根据流程实例获取流程图并高亮
30.getProcessImgEd(String deploymentId,String name)根据流程定义获取流程图
31.addApply(String id,String businessKey,Map<String, Object> variables,Lc_Apply lc_Apply)添加审批（发起申请）
32.addApplySetAssignee(String id,String businessKey,Map<String, Object> variables,Lc_Apply lc_Apply)添加审批（发起申请）并设置第一个节点发起人并完成第一个节点
33.claim(String taskId,String userId)将组任务指定个人任务（该方法：检查该任务是否已经被认领，如果被认领则会抛出ActivitiTaskAlreadyClaimedException）
34.setAssignee(String taskId,String userId)将个人任务再回退到组任务（前提：之前这个任务是组任务）
35.setOwner(String taskId,String userId)设置任务的归属者
36.addGroupUser(String taskId,String userId)向组任务中添加成员
37.deleteGroupUser(String taskId,String userId)向组任务中删除成员
38.getAssigneeTaskPageList(Map<String, Object> condition)查找个人任务
39.getAssigneeTaskList(Map<String, Object> condition)查找个人任务(不分页)
40.getCandidateTaskPageList(Map<String, Object> condition)查找候选人任务
41.getCandidateGroupTaskPageList(Map<String, Object> condition)查找处理组任务
42.completeTask(String taskId)完成任务
43.isEnded(String processInstanceId)判断流程实例是否已经结束
44.getTaskList(Map<String, Object> condition)获取所有任务
45.getTaskListByInstanceId(Map<String, Object> condition)获取实例下所有任务
46.jump(String executionId,String activityId)调用跳转节点
47.jointProcess(String taskId, List<String> userCodes)会签操作 
48.backProcess(String taskId, String activityId,Map<String, Object> variables)驳回流程 
49.callBackProcess(String taskId, String activityId)
50.clearTransition(ActivityImpl activityImpl)清空指定活动节点流向 
51.commitProcess(String taskId, Map<String, Object> variables,String activityId)提交流程
52.endProcess(String taskId)结束流程
53.filterNewestActivity(ProcessInstance processInstance,List<ActivityImpl> tempList)根据流入任务集合，查询最近一次的流入任务节点 
54.findActivitiImpl(String taskId, String activityId)根据任务ID和节点ID获取活动节点
55.findBackAvtivity(String taskId)查询指定任务节点的最新记录 
56.findHistoricUserTask(ProcessInstance processInstance, String activityId)
57.findParallelGatewayId(ActivityImpl activityImpl)根据当前节点，查询输出流向是否为并行终点，如果为并行终点，则拼装对应的并行起点ID 
58.findProcessDefinitionEntityByTaskId(String taskId)根据任务ID获取流程定义 
59.findProcessInstanceByTaskId(String taskId)根据任务ID获取对应的流程实例 
60.TaskEntity findTaskById(String taskId) 根据任务ID获得任务实例 
61.findTaskListByKey(String processInstanceId, String key) 根据流程实例ID和任务key值查询所有同级任务集合 
62.restoreTransition(ActivityImpl activityImpl,List<PvmTransition> oriPvmTransitionList)还原指定活动节点流向 
63.reverList(List<ActivityImpl> list)  反向排序list集合，便于驳回节点按顺序显示 
64.transferAssignee(String taskId, String userCode)转办流程 
65.turnTransition(String taskId, String activityId,Map<String, Object> variables)流程转向操作 
66.getImageStream(String taskId)根据当前任务获取图片
67.getUserInfo(String userid)获取用户详细信息
68.getUserOfGroup(String userid)根据用户id查询用户所在的组
69.getGroupInfo(String groupId)根据groupId查询组详细信息
70.memberOfGroup(String groupId)列出组内的所有用户
71.getTaskByProcessInstanceID(String processInstanceID)根据流程实例编号查找当前任务

说明紫色背景为不常用
流程设计器




4.全文检索
介绍：采用solr4.10版本
交流及其他说明：
由于空余时间不多，只能写一部分开发文档模块 主要还是交流为目的
本平台采用开源方式，交流QQ群：330370132
项目地址：http://git.oschina.net/jehc/jehc


