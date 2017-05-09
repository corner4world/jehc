{
	/**
	 * 构造一个新的应用程序（注意，这会返回一个mxEditor（mx编辑器）实例）。
	 */
	function mxApplication(config) {
		// 淡出的启动画面
		var hideSplash = function() {
			var splash = document.getElementById('splash');
			if (splash != null) {
				try {
					mxEvent.release(splash);
					mxEffects.fadeOut(splash, 100, true);
				} catch (e) {
					splash.parentNode.removeChild(splash);
				}
			}
		};
		try {
			//判断浏览器支持
			if (!mxClient.isBrowserSupported()) {
				mxUtils.error('浏览器不支持', 200, false);
			}else {
				//载入xml信息
				var node = mxUtils.load(config).getDocumentElement();
				//初始化编辑器
				var editor = new mxEditor(node);
				// 更新后打开新文件的窗口标题
				var title = document.title;
				var funct = function(sender) {
					document.title = title + ' - ' + sender.getTitle();
				};
				//添加监听
				editor.addListener(mxEvent.OPEN, funct);
				//如果当前根图形（目录）改变，在当前图形中打印窗口中的新标题
				editor.addListener(mxEvent.ROOT, funct);
				funct(editor);
				// 在状态栏显示版本
				editor.setStatus('mxGraph '+mxClient.VERSION);
				//淡出的启动画面，显示应用程序
				hideSplash();
			}
		}catch (e) {
			hideSplash();
			//如果编辑器不能启动，显示一个错误消息
			mxUtils.alert('Cannot start application: '+e.message);
			throw e; // 为调试给出异常
		}
		return editor;
	}
}
