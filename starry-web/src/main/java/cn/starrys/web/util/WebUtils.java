package cn.starrys.web.util;

import cn.starrys.core.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * creationTime: 2023/3/18 13:02 .
 *
 * @author XingKong
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebUtils {

    public static final String UNKNOWN = "unknown";

    /**
     * 获取IP地址
     *
     * @param request HttpServletRequest
     * @return ip地址字符串
     */
    public static String getIp(HttpServletRequest request) {
        if (request == null) {
            throw (new NullPointerException("HttpServletRequest 对象为空"));
        }

        String ipStr = request.getHeader("x-forwarded-for");

        if (StringUtils.isEmpty(ipStr) || UNKNOWN.equalsIgnoreCase(ipStr)) {
            ipStr = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipStr) || UNKNOWN.equalsIgnoreCase(ipStr)) {
            ipStr = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipStr) || UNKNOWN.equalsIgnoreCase(ipStr)) {
            ipStr = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        String[] arr = ipStr.split(",");
        for (String str : arr) {
            if (StringUtils.notEmpty(str) && !UNKNOWN.equalsIgnoreCase(str)) {
                ipStr = str;
                break;
            }
        }

        return ipStr;
    }

}
