package com.study.shop.business.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Tiger
 * @date 2019-12-06
 * @see com.study.shop.business.configuration
 **/
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class GoodsResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/cart/**").hasAuthority("USER")
                .antMatchers("/order/**").hasAuthority("USER")
                .antMatchers("/admin-classification/**").hasAuthority("ADMIN")
                .antMatchers("/admin-goods/**").hasAuthority("ADMIN")
                .antMatchers("/admin-order/**").hasAuthority("ADMIN")
                .antMatchers("/shipping/**").hasAuthority("USER")
                .antMatchers("/**").permitAll();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 配置资源 ID
        resources.resourceId("backend-resources");
    }
}
