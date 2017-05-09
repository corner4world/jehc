package jehc.junitmodules.junit.officejunit;
import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class officejunit {
	public static void main(String[] args) {
		String contractFileName = "测试" + ".pdf";
		String contractPath = ("D:" + File.separator + contractFileName);
		String contractImgPath = ("D:\\test\\demo.jpg");
		makeContractFile(contractPath, contractImgPath);
	}

	 private static void makeContractFile(String contractPath, String contractImgPath)
		{
			Document document = null;
			try 
			{
				document = new Document(PageSize.A4, 20, 20, 20, 20); // A4纸大小 左、右、上、下
				BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/webdings.TTF", BaseFont.CP1250, BaseFont.EMBEDDED); // 中文处理
				Font titleChinese = new Font(bfChinese, 16, Font.BOLD); // 模板抬头的字体
				Font subFontChinese = new Font(bfChinese, 9, Font.ITALIC); // 币种和租金金额的小一号字体
				Font moneyFontChinese = new Font(bfChinese, 8, Font.NORMAL); // 币种和租金金额的小一号字体
				Font subBoldFontChinese = new Font(bfChinese, 9, Font.BOLD); // 币种和租金金额的小一号字体
				PdfWriter.getInstance( document, new FileOutputStream(contractPath));
				document.open(); // 打开文档
				// ------------开始写数据-------------------
				Paragraph title = new Paragraph("宿迁运策物流有限公司(货运委托合同)    NO：" + 123456, titleChinese);// 抬头
				title.setAlignment(Element.ALIGN_CENTER); // 居中设置
				title.setLeading(1f);// 设置行间距//设置上面空白宽度
				document.add(title);
				
				Image img = Image.getInstance(contractImgPath);
				img.scaleAbsolute(100, 100);// 直接设定显示尺寸
				img.setAbsolutePosition(70, 120);
				document.add(img);

				float[] widths = new float[] { 10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f};// 设置表格的列宽和列数 默认是4列

				PdfPTable table = new PdfPTable(widths);// 建立一个pdf表格
				table.setSpacingBefore(20f);// 设置表格上面空白宽度
				table.setTotalWidth(500);// 设置表格的宽度
				table.setWidthPercentage(100);// 设置表格宽度为%100
				// table.getDefaultCell().setBorder(0);//设置表格默认为无边框

				PdfPCell cell = null;
				// ---表头
				cell = new PdfPCell(new Paragraph("托 运 方（甲方）", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("大A实业", subFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(3);
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("地址", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("南京财经大学", subFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("承运方陈韵（乙方）", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("大B实业", subFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(3);
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("电话／微信", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("13333333333", moneyFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("银行账户", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("666666666666", moneyFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(3);
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("身份证号", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("152324199201046654", moneyFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(3);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("货物名称", subBoldFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("毛重（吨）", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("体积（m³）", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(2);
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("件   数", subBoldFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("包 装", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("备  注", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(2);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("黄金", subFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("测试看一三五", moneyFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("你在说什么", moneyFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(2);
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph(77777+"", subFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("看看测试四五六", subFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("测试看看七八九", subFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(2);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("装货地址", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("内蒙古 通辽市开鲁县爱仕达", subFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(7);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("卸货地址", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("南京市南京财经大学南财科技楼A座502", subFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(7);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("车牌号", subBoldFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("蒙A45646", moneyFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("车长", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("15m", moneyFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("车型", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("高栏", subFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("车宽", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("", moneyFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("装货时间", subBoldFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("2016:03:12", moneyFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("到货时间", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("2016:10:17", moneyFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("运费总额", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("2500", moneyFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("结算方式", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("油卡", subFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("备注", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("这个杀手不太冷", subFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(7);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("甲、乙双方本着诚实守信的原则，经过协商一致同意根据合同法有关规定，订立货物运输合同，条款如下：\n\n"
												+"1、乙方所提供的车辆必须是技术性能良好，证照齐全、合法、有效、保险期内的车辆。签订合同时，乙方须向甲方提供承运车辆及驾驶员的基本资料照片（行驶证、营运证、保险卡、驾驶证、身份证正面反面，车辆照片）。\n"
												+"2、乙方如不按时到达装货地点，甲方有权取消此次物流合同并不退还已经收取乙方的费用。\n"
												+"3、乙方在提货地点办理提货手续时如需签署提货单，应清点品种及数量无误后再签字，签字即视为乙方确认无误。如发现有差错，乙方需第一时间和甲方联系。\n"
												+"4、乙方在装货的过程中：发现货物尺寸与已签订合同不相符，必须第一时间提出并不得离厂，否则出厂即视为乙方默认没有争议，运输过程中再提出争议视为乙方违约。乙方需确认装货是否安全、是否影响在途行驶，若影响，离场前通知甲方，由甲方协调整理装货，出场即为乙方默认装货安全，不影响在途行驶，在途出现异常由乙方自行承担责任。\n"
												+"5、因为牵涉到保险生效确认，乙方需收到甲方的短信通知方可离场（由号码1069开头发出的短信，内容：运策物流已确认您可以离厂，一路顺风！），如擅自离场产生的货损及异常保险公司拒绝承保，均由乙方承担。\n"
												+"6、乙方对所托运的货物安全完好负责，保证货物无损坏、无短缺，货物运输过程中若发生意外事故，乙方应在第一时间通知甲方并随时通报事故处理情况。如产生货损，需配合保险公司现场勘查及取证工作，并在两周内按保险公司要求提供所需的有效单据。甲方通过自身的保险公司进行保险理赔，甲方保险赔偿不足部分由乙方承担，乙方另需承担保险公司代位追偿责任。\n"
												+"7、在运输途中未经甲方文字确认不得更换车辆，如乙方擅自更换车辆，乙方自愿向甲方支付双倍运费补偿，甲方将扣除该单运费并向乙方追讨造成的其他损失。 如乙方擅自变更本合同的付款方式及价格，甲方有权拒付该单运费。\n"
												+"8、乙方在任何情况下不得以任何理由留置或以其他任何方式扣留甲方的货物，否则，乙方应按照其扣留货物价值的双倍向甲方支付违约金。\n"
												+"9、如非乙方原因，发生收货方拒收货物或变更收货地址的情况，乙方应及时通知甲方，由甲方协调处理收货事宜。\n "
												+"10、如因乙方原因送错货物，由乙方无条件将正确货物送至正确地点，并承担由此产生的损失。\n"
												+"11、除不可抗力因素，乙方必须严格按照合同规定的期限，将货物安全送达安全收货地点，并及时向收货方发出货物到达的通知。如乙方到厂延迟，甲方有权追究乙方违约责任，按照赔付协议扣除此单相应运费。如客户装卸货延迟，导致乙方装卸货等待，双方依据延迟赔付补充协议，赔偿乙方费用，  乙方如擅自增加费用，甲方有权追究乙方违约责任。\n"
												+"12、 甲方提供乙方2份送货单，1份交客户，1份返甲方，送货回单在客户签字盖章后在7天内交返甲方，货物送达收货地点后，由收货方对货物进行验查并签收；收货内容不得涂改，如有涂改必须由收货方在涂改处注明并签字，否则一律无效；有特殊要求的以甲方书面通知为准，回单约定顺丰，快递费乙方承担，超过7天，甲方有权在该单运费中扣除60元。乙方凭回单甲方结算运费。\n"
												+"13、本合同一式三份，甲乙双方各持一份，（乙方在结算运费时交还甲方）。此合同在双方签字或者通过短信／彩信／微信／QQ等方式乙方回复“同意”后生效，具有法律效力。如双方发生争议，本合同在履行过程中发生的争议，由双方当事人协商解决，协商或调解不成的，双方均有权向南京市鼓楼区人民法院提起诉讼。产生的诉讼费由败诉方承担。\n"
													, subFontChinese));// 描述			
				cell.setFixedHeight(300);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(8);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("甲方：（盖章）" + "南京运策物流", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(4);
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("乙方：（盖章）" + "聪聪老司机", subBoldFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(4);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("代表： " + "王总", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(4);
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("代表： " + "聪聪老司机", subBoldFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(4);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("日期： ", subBoldFontChinese));// 描述
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(4);
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("日期： ", subBoldFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(4);
				table.addCell(cell);
				
				
				cell = new PdfPCell(new Paragraph("付款账户信息 ", subBoldFontChinese));// 描述
				cell.setFixedHeight(40);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(2);
				table.addCell(cell);
				
				PdfPTable table0 = new PdfPTable(3);// 建立一个pdf表格
				cell = new PdfPCell(new Paragraph("户名、开户行 ", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table0.addCell(cell);
				cell = new PdfPCell(new Paragraph("你好再见", subFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(2);
				table0.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("账   号 ", subBoldFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table0.addCell(cell);
				cell = new PdfPCell(new Paragraph("6666666666666", moneyFontChinese));// 描述			
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(2);
				table0.addCell(cell);
				
				cell = new PdfPCell(table0);// 描述			
				cell.setFixedHeight(40);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(6);
				table.addCell(cell);
				
				
				cell = new PdfPCell(new Paragraph("信息费支付方：", subBoldFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("现金", subFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("金额：", subBoldFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("15", moneyFontChinese));// 描述
				cell.setFixedHeight(20);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				table.addCell(cell);
				cell = new PdfPCell(new Paragraph("运输类型：" + "空运", subBoldFontChinese));// 描述			
				cell.setFixedHeight(30);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE); // 设置垂直居中
				cell.setColspan(4);
				table.addCell(cell);
				document.add(table);
			} catch (Exception e) {
				e.printStackTrace();
				return ;
			}finally{
				try{
					if(document != null){
						document.close();
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			return ;
		}
}
