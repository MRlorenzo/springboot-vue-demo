package org.spmul.web.log.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class IPUtil {

    /**
     * 获取IP地址
     *
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.error("IPUtils ERROR ", e);
        }

        return ip;
    }
    /**
     * 获取计算机名称
     * @param ip ip地址
     * @param request 请求
     * @return String
     */
    public static String getComputerName(String ip, HttpServletRequest request){
        try{
            String computerName = getComputerName(ip);
            if(computerName.equals(ip)){
                String name = IPUtil.getComputerName(request);
                if(!StringUtils.isEmpty(name)){
                    computerName = name;
                }
            }
            return computerName;
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 获取计算机名称
     * @param ip ip地址
     * @return String
     */
    public static String getComputerName(String ip){
        if(StringUtils.isEmpty(ip)){
            return "";
        }
        try {
            InetAddress ia;
            if(ip.equals("127.0.0.1")){
                ia = InetAddress.getLocalHost();
            }else{
                ia = InetAddress.getByName(ip);
            }
            return ia.getHostName();
        } catch (UnknownHostException e) {
            log.error("IPUtils getComputerName ERROR ", e);
            return "";
        }
    }

    public static String getComputerName(HttpServletRequest request) {
        return request.getHeader("Computer-Name");
    }

    public static String getComputerMac(HttpServletRequest request) {
        return request.getHeader("Computer-Mac");
    }
}
