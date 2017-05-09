var store;
var grid;
Ext.onReady(function(){	
	Ext.Loader.setPath('Ext.ux', basePath+'/deng/source/plugins/e6/ux');
    Ext.require("Ext.ux.PreviewPlugin", function() {
    	var titlekeywords = $('#keywords').val();
    	titlekeywords = Ext.util.Format.trim(titlekeywords);
    	titlekeywords = Ext.util.Format.ellipsis(titlekeywords, 50, true); 
    	titlekeywords = '全文检索、关键字、关键词:<font color="red">'+titlekeywords+'</font> 查询结果';
   		//msgTishi(!! Ext.ux.PreviewPlugin);
   		store = getGridJsonStore('../xtKnowledgeController/getXtKnowledgeListByCondition',[]);
		grid = Ext.create('Ext.grid.Panel',{
			region:'center',
			xtype:'panel',
			store:store,
			//title:'<font color="#fff">全文检索、关键字、查询结果</font>',
			title:'<font color="#fff">'+titlekeywords+'</font>',
			columnLines:false,
			hideHeaders:false,
			header:{
				//style:'background-color:#5fa2dd;background-image:none;',
				//height:110
			},
			viewConfig:{
				emptyText:'暂无数据',
				trackOver: false,
		       	stripeRows: false
			},
			requires:[
		        'Ext.toolbar.Paging',
		        'Ext.ux.PreviewPlugin'
		    ],
		    plugins: [{
		        ptype:'preview',
		        expanded:true,
		        bodyField:'xt_knowledge_content'
		    }],
		    frame:true,
		    disableSelection:true,
			loadMask:{
				msg:'正在加载...'
			},
			columns:[
				{
		            header:'<font color="#ADADAD">标题</font>',
		            dataIndex:'xt_knowledge_title',
		            flex:1,
		            renderer:renderTopic	        
		       	},
				{
					header:'',
					width:180,
					dataIndex:'xt_time',
					renderer:renderLast
				}
			],
			bbar:getGridBBar(store)
		});
		store.on('beforeload',function(thiz, options){
			Ext.apply(thiz.proxy.extraParams,{keywords:$('#keywords').val()});
	    });
		Ext.create('Ext.Viewport', {
			layout:'border',
			xtype:'viewport',
			items:[grid]
		});
    });
	function renderTopic(value, p, record) {
	    return Ext.String.format(
	        '<b><a href="../xtSearchController/loadXtSearchForm?searchid={2}" target="_blank">{0}</a></b>'/** + '{1}'**/,
	        value,
	        record.get('xt_userinfo_realName'),
	        record.get('xt_knowledge_id')
	    );
	}
	
	function renderLast(value, p, record) {
        return Ext.String.format('{0}<br/>by {1}', value, record.get('xt_userinfo_realName'));
    }
});