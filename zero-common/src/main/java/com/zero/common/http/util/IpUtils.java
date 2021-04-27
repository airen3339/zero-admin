package com.zero.common.http.util;

import com.zero.common.http.domain.IpInfo;
import com.zero.common.http.properties.HttpThirdApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 *
 * @author herenpeng
 * @since 2021-02-16 8:08
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class IpUtils {

    private final RestTemplate restTemplate;

    private final HttpThirdApi httpThirdApi;

    /**
     * 通过 HttpServletRequest 对象获取真实地址
     *
     * @param request HttpServletRequest对象
     * @return 真实地址
     */
    public IpInfo getIpInfo(HttpServletRequest request) {
        return getIpInfo(getIpAddr(request));
    }

    /**
     * 通过 IP 地址获取真实地址
     *
     * @param ip IP 地址
     * @return 真实地址
     */
    public IpInfo getIpInfo(String ip) {
        try {
            String url = httpThirdApi.getIpInfo() + ip;
            ResponseEntity<IpInfo> responseEntity = restTemplate.getForEntity(url, IpInfo.class);
            HttpStatus statusCode = responseEntity.getStatusCode();
            if (ObjectUtils.nullSafeEquals(statusCode, HttpStatus.OK)) {
                IpInfo ipInfo = responseEntity.getBody();
                if (!ObjectUtils.isEmpty(ipInfo) && ipInfo.getCode().equals(0)) {
                    return ipInfo;
                }
            }
        } catch (Exception e) {
            log.warn("[获取IP信息]ip：{}信息获取失败", ip);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取访问用户的真实地址，因为如果使用了nginx反向代理，无法获取访问用户的真实地址
     *
     * @param request HttpServletRequest对象
     * @return 真实ip地址
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (checkIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (checkIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (checkIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 判断ip是否有效
     *
     * @param ip ip地址
     * @return ip是否有效，布尔值
     */
    private boolean checkIp(String ip) {
        return StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip);
    }


}
