package jehc.paymodules.payservice;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import jehc.paymentmodules.paymentdao.PaymentAccountDao;
import jehc.paymentmodules.paymentmodel.PaymentAccount;

/**
 * 
 * @author dengcj
 *
 */
@Service
public class ApyAccountService {
	@Autowired
	private PaymentAccountDao paymentAccountDao;
    @Resource
    private AutowireCapableBeanFactory spring;
    private final static Map<String, PayResponse> payResponses = new HashMap<String, PayResponse>();

    /**
     *  获取支付响应
     * @param id 账户id
     * @return
     */
    public PayResponse getPayResponse(String id) {
        PayResponse payResponse = payResponses.get(id);
        if (payResponse  == null) {
            PaymentAccount apyAccount = paymentAccountDao.getPaymentAccountById(id);
            if (apyAccount == null) {
                throw new IllegalArgumentException ("无法查询");
            }
            payResponse = new PayResponse();
            spring.autowireBean(payResponse);
            payResponse.init(apyAccount);
            payResponses.put(id, payResponse);
        }
        return payResponse;
    }


}
