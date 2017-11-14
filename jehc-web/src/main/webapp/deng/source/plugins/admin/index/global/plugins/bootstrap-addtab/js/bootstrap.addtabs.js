$.fn.addtabs = function (options) {
    obj = $(this);
    Addtabs.options = $.extend({
        content: '', //直接指定所有页面TABS内容
        close: true, //是否可以关闭
        monitor: 'body', //监视的区域
        iframeUse: true, //使用iframe还是ajax
        iframeHeight: $(document).height() - 95, //固定TAB中IFRAME高度,根据需要自己修改
        method: 'init',
        callback: function () { //关闭后回调函数
        }
    }, options || {});


    $(Addtabs.options.monitor).on('click', '[data-addtab]', function () {
        Addtabs.add({
            id: $(this).attr('data-addtab'),
            title: $(this).attr('title') ? $(this).attr('title') : $(this).html(),
            content: Addtabs.options.content ? Addtabs.options.content : $(this).attr('content'),
            url: $(this).attr('url'),
            close: $(this).attr('close'),
            ajax: $(this).attr('ajax') ? true : false
        });
    });

    obj.on('click', '.close-tab', function () {
        var id = $(this).prev("a").attr("aria-controls");
        Addtabs.close(id);
    });
    //obj上禁用右键菜单
    obj.on('contextmenu', 'li[role=presentation]', function () {
        var id = $(this).children('a').attr('aria-controls');
        var url = $(this).attr('aria-url');//dengcj修改
        Addtabs.pop(id, $(this),url);
        return false;
    });
    
    obj.on('click', 'li[role=presentation]', function () {
        var id = $(this).children('a').attr('aria-controls');
        var idList = $(this).children('a').attr('idList');
        doActive(id.replace("tab_",""),idList);
    });

    //刷新页面
    obj.on('click', 'ul.rightMenu a[data-right=refresh]', function () {
        var id = $(this).parent('ul').attr("aria-controls").substring(4);
        var url=$(this).parent('ul').attr('aria-url');
        Addtabs.add({'id':id,'url':url});
        $('#popMenu').fadeOut();
    });

    //关闭自身
    obj.on('click', 'ul.rightMenu a[data-right=remove]', function () {
        var id = $(this).parent("ul").attr("aria-controls");
        Addtabs.close(id);
        Addtabs.drop();
        $('#popMenu').fadeOut();
    });

    //关闭其他
    obj.on('click', 'ul.rightMenu a[data-right=remove-circle]', function () {
        var tab_id = $(this).parent('ul').attr("aria-controls");
        obj.children('ul.nav').find('li').each(function () {
            var id = $(this).attr('id');
            if (id && id != 'tab_' + tab_id) {
                Addtabs.close($(this).children('a').attr('aria-controls'));
            }
        });
        Addtabs.drop();
        $('#popMenu').fadeOut();
    });

    //关闭左侧
    obj.on('click', 'ul.rightMenu a[data-right=remove-left]', function () {
        var tab_id = $(this).parent('ul').attr("aria-controls");
        $('#tab_' + tab_id).prevUntil().each(function () {
            var id = $(this).attr('id');
            if (id && id != 'tab_' + tab_id) {
                Addtabs.close($(this).children('a').attr('aria-controls'));
            }
        });
        Addtabs.drop();
        $('#popMenu').fadeOut();
    });

    //关闭右侧
    obj.on('click', 'ul.rightMenu a[data-right=remove-right]', function () {
        var tab_id = $(this).parent('ul').attr("aria-controls");
        $('#tab_' + tab_id).nextUntil().each(function () {
            var id = $(this).attr('id');
            if (id && id != 'tab_' + tab_id) {
                Addtabs.close($(this).children('a').attr('aria-controls'));
            }
        });
        Addtabs.drop();
        $('#popMenu').fadeOut();
    });

    obj.on('mouseover', 'li[role = "presentation"]', function () {
        $(this).find('.close-tab').show();
    });

    obj.on('mouseleave', 'li[role = "presentation"]', function () {
//        $(this).find('.close-tab').hide();
    });

    $(window).resize(function () {
        obj.find('iframe').attr('height', Addtabs.options.iframeHeight);
        Addtabs.drop();
    });

};

window.Addtabs = {
    options: {},
    add: function (opts) {
        var id = 'tab_' + opts.id;
        $('li[role = "presentation"].active').removeClass('active');
        $('div[role = "tabpanel"].active').removeClass('active');
        //如果TAB不存在，创建一个新的TAB
        if (!$("#" + id)[0]) {
            //创建新TAB的title
            var title = $('<li>', {
                'role': 'presentation',
                'id': 'tab_' + id,
                'aria-url':opts.url,
                'jehchref':opts.url,
                'jehcid':opts.id,
                'idList':opts.idList
            }).append(
                $('<a>', {
                    'href': '#' + id,
                    'aria-controls': id,
                    'role': 'tab',
                    'data-toggle': 'tab',
                    'jehchref':opts.url,
                    'jehcid':opts.id,
                    'idList':opts.idList
                }).html(opts.title)
            );

            //是否允许关闭dengcj修改
            if (opts.close) {
//          if (Addtabs.options.close) {
                title.append(
                    $('<i>', {'class': 'close-tab glyphicon glyphicon-remove'})
                );
            }
            //创建新TAB的内容
            var content = $('<div>', {
                'class': 'tab-pane',
                'id': id,
                'role': 'tabpanel'
            });

            //加入TABS
            obj.children('.nav-tabs').append(title);
            obj.children(".tab-content").append(content);
        } else {
            var content = $('#' + id);
            content.html('');
        }

        //是否指定TAB内容
        if (opts.content) {
            content.append(opts.content);
        } else if (Addtabs.options.iframeUse && !opts.ajax) {//没有内容，使用IFRAME打开链接
            content.append(
                $('<iframe>', {
                    'class': 'iframeClass',
                    'height': Addtabs.options.iframeHeight-95,
                    'iframe_id_':id,
                    'frameborder': "no",
                    'border': "0",
                    'src': opts.url
                })
            );
        } else {
            $.get(opts.url, function (data) {
                content.append(data);
            });
        }
        //$('#tab_' + id).find('.close-tab').hide();
        //激活TAB
        $('#tab_' + id).addClass('active');
        $('#' + id).addClass('active');
        Addtabs.drop();
    },
    pop: function (id,e,url) {
        $('body').find('#popMenu').remove();
        /*if(id == 'tab_home_index'){
        	var popHtml = $('<ul>', {'aria-controls': id, 'class': 'rightMenu list-group', id: 'popMenu','aria-url':url})
	            .append(
	            '<a href="javascript:void(0);" class="list-group-item" data-right="refresh"><i class="glyphicon glyphicon-refresh"></i> 刷新此标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove-circle"><i class="glyphicon glyphicon-remove-circle"></i> 关闭其他标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove-left"><i class="glyphicon glyphicon-chevron-left"></i> 关闭左侧标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove-right"><i class="glyphicon glyphicon-chevron-right"></i> 关闭右侧标签</a>'
	        );
	        popHtml.css({
	            'top': e[0].offsetHeight + 20 + 'px',
	            'left': e[0].offsetLeft + 215 + 'px'
	        });
	        popHtml.appendTo(obj).fadeIn('slow');
	        popHtml.mouseleave(function () {
	            $(this).fadeOut('slow');
	        });
        }else{
        	var popHtml = $('<ul>', {'aria-controls': id, 'class': 'rightMenu list-group', id: 'popMenu','aria-url':url})
	            .append(
	            '<a href="javascript:void(0);" class="list-group-item" data-right="refresh"><i class="glyphicon glyphicon-refresh"></i> 刷新此标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove"><i class="glyphicon glyphicon-remove"></i> 关闭此标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove-circle"><i class="glyphicon glyphicon-remove-circle"></i> 关闭其他标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove-left"><i class="glyphicon glyphicon-chevron-left"></i> 关闭左侧标签</a>' +
	            '<a href="javascript:void(0);" class="list-group-item" data-right="remove-right"><i class="glyphicon glyphicon-chevron-right"></i> 关闭右侧标签</a>'
	        );
	        popHtml.css({
	            'top': e[0].offsetHeight + 20 + 'px',
	            'left': e[0].offsetLeft + 215 + 'px'
	        });
	        popHtml.appendTo(obj).fadeIn('slow');
	        popHtml.mouseleave(function () {
	            $(this).fadeOut('slow');
	        });
        }*/
    },
    activeTabId:function(){
    	var id = obj.find("#tabList li.active").attr('id');
    	return id;
    },
    close: function (id) {
    	var tabIdArray = Addtabs.tabIdList();
    	id = id.replace("tab_tab_","");
    	id = id.replace("tab_","")
        //如果关闭的是当前激活的TAB，激活他的前一个TAB
        if (obj.find("#tabList li.active").attr('id') === "tab_tab_" + id) {
            $("#tab_tab_" + id).prev().addClass('active');
            $("#tab_" + id).prev().addClass('active');
        }
        //关闭TAB
        $("#tab_tab_" + id).remove();
        $("#tab_" + id).remove();
        Addtabs.drop();
        Addtabs.options.callback();
        
        
        setTimeout(function () { 
        	try {
        		//dengcj获取当前激活标签
                var idList = obj.find("#tabList li.active").attr('idList');
                var ids = obj.find("#tabList li.active").attr('id');
                if(typeof(idList) != "undefined"){
                }
            	if(typeof(ids) != "undefined"){
            		ids = ids.replace("tab_tab_","");
            		ids = ids.replace("tab_","");
            	}else{
            		ids = null;
            	}
                doActive(ids,idList,tabIdArray);
			} catch (e) {
				
			}
        }, 100);
    },
    closeRight: function (tab_id) {
        $('#tab_' + tab_id).nextUntil().each(function () {
            var id = $(this).attr('id');
            if (id && id != 'tab_' + tab_id) {
                Addtabs.close($(this).children('a').attr('aria-controls'));
            }
        });
        Addtabs.drop();
        $('#popMenu').fadeOut();
    },
    tabList:  function(){
    	var i = 0;
    	$.each(obj.find('li[id]'), function () {
          i = i +1;
        });
    	return i;
    },
    tabJehcId:  function(){
    	return obj.find("#tabList li.active").attr('jehcid');
    },
    tabJehchref:  function(){
    	return obj.find("#tabList li.active").attr('jehchref');
    },
    tabIdList:  function(){
    	var idList_ = [];
    	$.each(obj.find('li[id]'), function () {
    		idList_.push($(this).children('a').attr('idList'));
    		idList_.push(($(this).attr('id')).replace("tab_tab_",""));
        });
    	return idList_;
    },
    closeAll: function () {
        $.each(obj.find('li[id]'), function () {
            var id = $(this).children('a').attr('aria-controls');
            $("#tab_" + id).remove();
            $("#" + id).remove();
        });
        obj.find('li[role = "presentation"]').first().addClass('active');
        var firstID=obj.find('li[role = "presentation"]').first().children('a').attr('aria-controls');
        $('#'+firstID).addClass('active');
        Addtabs.drop();
    },
    drop: function () {
        element = obj.find('.nav-tabs');
        //创建下拉标签
        var dropdown = $('<li>', {
            'class': 'dropdown pull-right hide tabdrop tab-drop'
        }).append(
            $('<a>', {
                'class': 'dropdown-toggle',
                'data-toggle': 'dropdown',
                'href': '#'
            }).append(
                $('<i>', {'class': "glyphicon glyphicon-align-justify"})
            ).append(
                $('<b>', {'class': 'caret'})
            )
        ).append(
            $('<ul>', {'class': "dropdown-menu"})
        )

        //检测是否已增加
        if (!$('.tabdrop').html()) {
            dropdown.prependTo(element);
        } else {
            dropdown = element.find('.tabdrop');
        }
        //检测是否有下拉样式
        if (element.parent().is('.tabs-below')) {
            dropdown.addClass('dropup');
        }
        var collection = 0;

        //检查超过一行的标签页
        element.append(dropdown.find('li'))
            .find('>li')
            .not('.tabdrop')
            .each(function () {
                if (this.offsetTop > 0 || element.width() - $(this).position().left - $(this).width() < 83) {
                    dropdown.find('ul').append($(this));
                    collection++;
                }
            });

        //如果有超出的，显示下拉标签
        if (collection > 0) {
            dropdown.removeClass('hide');
            if (dropdown.find('.active').length == 1) {
                dropdown.addClass('active');
            } else {
                dropdown.removeClass('active');
            }
        } else {
            dropdown.addClass('hide');
        }
    }
}