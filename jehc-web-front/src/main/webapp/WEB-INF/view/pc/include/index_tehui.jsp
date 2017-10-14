<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/taglibs.jsp"%>
<div class="clear"></div>
<div class="tehui">
	<div class="width">
		<div class="tit"><span href="" class="name">今日特价</span></div>
		<div class="ctt">
			<div class="tejiaBox">
				<c:forEach var="bProduct" items="${bProductList }" varStatus="status">
					<c:if test="${status.index <=5 }">
					<div class="tejia">
						<div class="topImg"><img src="${jehcimg_base_url}${bProduct.xt_attachmentPath }" alt=""></div>
						<h2>上周特价展示</h2>
						<div class="more">
							<ul>
								<c:forEach var="bProductImgDefault" items="${bProduct.bproductImgDefaultList }" varStatus="status">
									<li><a href="${sysPath }/detail/${bProduct.b_product_id }"><img src="${jehcimg_base_url}${bProductImgDefault.xt_attachmentPath }" alt=""></a></li>
								</c:forEach>
								<li class="lli"><a href="${sysPath }/detail/${bProduct.b_product_id }"><b>118</b>${bProduct.b_product_name }</a></li>
							</ul>
						</div>
					</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</div>