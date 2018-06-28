package jehc.crmmodules.crmmodel;
import jehc.xtmodules.xtcore.base.BaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
* crm_invoice 客户发票 
* 2018-06-27 15:13:10  邓纯杰
*/
public class CrmInvoice extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String invoiceId;/**客户发票id 主键**/
	private int invoiceType;/**发票类型：0普通发票2增值税发票**/
	private String invoiceName;/**发票名称**/
	private String customerId;/**客户编号**/
	private String invoiceTaxNumber;/**发票税号**/
	public void setInvoiceId(String invoiceId){
		this.invoiceId=invoiceId;
	}
	public String getInvoiceId(){
		return invoiceId;
	}
	public void setInvoiceType(int invoiceType){
		this.invoiceType=invoiceType;
	}
	public int getInvoiceType(){
		return invoiceType;
	}
	public void setInvoiceName(String invoiceName){
		this.invoiceName=invoiceName;
	}
	public String getInvoiceName(){
		return invoiceName;
	}
	public void setCustomerId(String customerId){
		this.customerId=customerId;
	}
	public String getCustomerId(){
		return customerId;
	}
	public void setInvoiceTaxNumber(String invoiceTaxNumber){
		this.invoiceTaxNumber=invoiceTaxNumber;
	}
	public String getInvoiceTaxNumber(){
		return invoiceTaxNumber;
	}
}
