Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.chooser', '.');
Ext.Loader.setPath('Ext.ux', '../../ux');
Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.view.View',
    'Ext.ux.DataView.DragSelector',
    'Ext.ux.DataView.LabelEditor'
]);
var store;
var grid;
Ext.onReady(function() {
	store = getGridJsonStore('../bProductImgDefaultController/getBProductImgDefaultListByCondition?b_product_id='+$('#b_product_id_').val(),[]);
    grid = Ext.create('Ext.view.View', {
        store:store,
        title:'商品默认图片列表',
        id:'images-view',
        tpl:[
            '<tpl for=".">',
                '<div class="thumb-wrap" id="{name:stripTags}">',
                    '<div class="thumb"><img src="{jehcimg_base_path_url}" title="{b_product_img_name}"></div>',
                    '<span class="x-editable"><font color="#ddd">名称:</font><font color="#ddd">{b_product_img_nameShort:htmlEncode}</span>',
                    '<span class="x-editable"><font color="#ddd">大小:</font><font color="#ddd">{xt_attachmentSizeShort:htmlEncode}KB</span>',
                    '<span class="x-editable"><font color="#ddd">时间:</font><font color="#ddd">{xt_attachmentCtimeShort:htmlEncode}</span>',
                    '<span class="x-editable"><font color="#ddd">格式:</font><font color="#ddd">{xt_attachmentTypeShort:htmlEncode}</span>',
                '</div>',
            '</tpl>',
            '<div class="x-clear"></div>'
        ],
        multiSelect:true,
        trackOver:true,
        overItemCls:'x-item-over',
        itemSelector:'div.thumb-wrap',
        emptyText:'暂无信息',
        plugins:[
            //Ext.create('Ext.ux.DataView.DragSelector',{}),
            //Ext.create('Ext.ux.DataView.LabelEditor',{dataIndex:'b_product_img_name'})
        ],
        prepareData:function(data){
            Ext.apply(data,{
            	/**
                shortName:Ext.util.Format.ellipsis(data.b_product_img_name,14),
                sizeString:Ext.util.Format.fileSize(data.xt_attachmentSize),
                dateString:Ext.util.Format.date(data.xt_attachmentCtime, "m/d/Y g:i a")
                **/
                b_product_img_nameShort:Ext.util.Format.ellipsis(data.b_product_img_name,14),
                xt_attachmentTypeShort:Ext.util.Format.ellipsis(data.xt_attachmentType,10),
                xt_attachmentSizeShort:Ext.util.Format.ellipsis(data.xt_attachmentSize,10),
                xt_attachmentCtimeShort:Ext.util.Format.ellipsis(data.xt_attachmentCtime, 20)
            });
            return data;
        }
    });
    Ext.create('Ext.Viewport',{
		layout:'fit',
		items:[{
	        tbar:[{
				text:'返回商品列表',
				tooltip:'返回商品列表',
				scope:this,
				cls:'backBtn',
				icon:backIcon,
				handler:function(){
					document.location.href="../bProductController/loadBProduct";
				}
			}],
			bbar:getGridBBar(store),
	        items:grid
		}]
	});
});