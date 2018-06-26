<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--页脚开始-->
<div id="footer">
	<!-- 微信分享导引 开始-->
	<script>
		function openGuide() {
			document.getElementById('guide').style.display = 'block';
		}
		function closeGuide() {
			document.getElementById('guide').style.display = '';
		}
	</script>
	<div id="guide" onClick="closeGuide()">
		<img src="${sysPath }/deng/phone/images/guide.png" />
	</div>
	<!-- 微信分享导引 结束-->
	<!--快捷按钮 开始-->
	<div class="plug-div">
		<div class="plug-phone">
			<div class="plug-menu themeStyle">
				<span class="close"></span>
			</div>
			<div class="themeStyle plug-btn plug-btn1 close">
				<a href="${sysPath }/index.html"><span style="background-image: url(${sysPath }/deng/phone/ky_img/home.png);"></span></a>
			</div>
			<div class="themeStyle plug-btn plug-btn2 close">
				<a href="${sysPath }/cmsContactController/contact.html"><span style="background-image: url(${sysPath }/deng/phone/ky_img/tel.png);"></span></a>
			</div>
			<div class="themeStyle plug-btn plug-btn3 close">
				<a href="${sysPath }/cmsAboutController/about.html"><span style="background-image: url(${sysPath }/deng/phone/ky_img/aboutus.png);"></span></a>
			</div>
			<div class="themeStyle plug-btn plug-btn4 close">
				<a href="${sysPath }/cmsMessageController/message.html"><span style="background-image: url(${sysPath }/deng/phone/ky_img/guestbook.png);"></span></a>
			</div>
			<div class="plug-btn plug-btn5 close"></div>
		</div>
	</div>
	<!--快捷按钮 结束-->
	<div>
		<ul class="wxlist">
			<a href="#top" class="">
				<li>
					<div class="GoTop">
						<img src="${sysPath }/deng/phone/images/top.png" />回顶部
					</div>
				</li>
			</a>
		</ul>
	</div>
	<div class="copyright">
		这里是底部信息 <br />免责声明：所有资源仅供学习与参考，版权归原作者，请勿用于商业用途，否则产生的一切后果将由您自行承担，与jehc无关。
	</div>
</div>
<!--页脚结束-->
<footer id="footer_mess">
    <div id="foot-div">
        <ul id="foot_ul">
            <li class="foot_acive foot_liwidth">
                <span>
                    <a href="${sysPath }/index.html" class="foot_acive">
                       <i><img src="${sysPath }/deng/phone/images/sy_i.png" /></i><br />首页
                    </a>
                </span>
            </li>
            <li class="foot_liwidth">
                <span>
                    <a href="${sysPath }/cmsNewsController/news.html" class="foota"><i>
                    <img src="${sysPath }/deng/phone/images/jd_i1.png" /></i><br />资讯中心
                    </a>
                </span>
            </li>
            <li class="foot_liwidth">
                <span>
                    <a href="${sysPath }/cmsCaseController/case.html" class="foota">
                        <i><img src="${sysPath }/deng/phone/images/hd_i1.png" /></i><br />案例展示
                    </a>
                </span>
            </li>
            <li class="foot_liwidth">
                <span>
                    <a href="${sysPath }/cmsContactController/contact.html" class="foota">
                        <i><img src="${sysPath }/deng/phone/images/xm_i1.png" /></i><br />联系我们
                    </a>
                </span>
            </li>
        </ul>
    </div>
</footer>