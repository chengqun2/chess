package com.wuziqi.configuration;

import com.wuziqi.controller.vm.constant.CommonConstant;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
@Slf4j
@Configuration
public class FeignConfig {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (null != attributes) {
                HttpServletRequest request = attributes.getRequest();
                String url = request.getRequestURI();
                log.info("Feign request: {}", request.getRequestURI());
                requestTemplate.header("Content-Type", "application/json");
                String token = request.getHeader(CommonConstant.X_ACCESS_TOKEN);
                if(token==null){
                    token = request.getParameter("token");
                    if(token==null){
                        if (null == redisTemplate.opsForValue().get("admin_token")){
                            return;
                        }else {
                            token = redisTemplate.opsForValue().get("admin_token").toString();
                            requestTemplate.header(CommonConstant.X_ACCESS_TOKEN, token);
                        }
                    }
                }
            }
        };
    }



    /**
     * Feign ??????????????????????????????????????????NONE
     * Logger.Level ????????????????????????
     * NONE????????????????????????
     * BASIC???????????????????????????URL????????????????????????????????????
     * HEADERS??????????????? BASIC????????????????????????????????????????????????????????????
     * FULL?????????????????????????????????????????????????????????????????????????????????
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * Feign??????????????????
     * @param messageConverters
     * @return
     */
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }
}
