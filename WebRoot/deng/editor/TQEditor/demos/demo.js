function echo(str){document.write(str);}
if(!document.getElementById('navPannel')){
echo('<div id="navPannel" style="font-size:12px;background-color:#F7F7F7;padding:5px;border-bottom:1px solid #333; text-decoration:none;">');
echo('<a href="index.htm">demo首页</a>');
echo(' | <a href="demo_toolbar.htm">默认模式</a>');
echo(' | <a href="demo_custom_toolbar.htm">工具栏定义</a>');
echo(' | <a href="demo_splitpage.htm">自定分页代码</a>');
echo(' | <a href="demo_media_tpl.htm">自定插入媒体代码</a>');
echo(' | <a href="demo_upload.htm">上传</a>');
echo(' | <a href="demo_custom_upload_ui.htm">自定上传界面</a>');
echo(' | <a href="demo_clean_word.htm">自动清理Word代码</a>');
echo(' | <a href="demo_typeset.htm">自动排版</a>');
echo(' | <a href="demo_style.htm">自定可视化背景色与文字色</a>');
echo(' | <a href="demo_skin.htm">皮肤</a>');
echo(' | <a href="demo_api.htm">API交互</a>');
echo(' | <a href="demo_event.htm">事件</a>');
echo(' | <a href="demo_apiex.htm">扩展的API</a>');
echo(' | <a href="demo_ubb.htm">UBB插件</a>');
echo(' | <a href="demo_extend.htm">扩展示例</a>');
echo(' | <a href="index.htm">下载</a>');
//echo(' | <a href="wizard.htm">代码向导</a>');
echo('</div>');
}