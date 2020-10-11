package com.hdl.hdltool.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author jesse
 * @since 2020/10/11
 */
@Component
@ConfigurationProperties(prefix="system")
public class MyProps {
    public String url;
    public String getUrl() {
        return url;
    }
    public MyProps setUrl(String url) {
        this.url = url;
        return this;
    }
}
