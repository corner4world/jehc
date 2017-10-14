package jehc.xtmodules.xtcore.addressip;

import javax.servlet.http.HttpServletRequest;

public class GetClientIp {
	public String getIpAddr(HttpServletRequest request) {
//		String ip = request.getHeader("x-forwarded-for"); //apache
		String ip = request.getHeader("X-Real-IP");//nginx
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.split(",")[0];
	}
	
	
	public static String getIpAddrW(HttpServletRequest request) {   
        String ip = request.getHeader("X-Forwarded-For");   
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("Proxy-Client-IP");   
        }   
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("WL-Proxy-Client-IP");   
        }   
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("HTTP_CLIENT_IP");   
        }   
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");   
        }   
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getRemoteAddr();   
        }   
        return ip;   
    } 
}
