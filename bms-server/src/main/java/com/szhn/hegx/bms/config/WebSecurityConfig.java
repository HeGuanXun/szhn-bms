package com.szhn.hegx.bms.config;

import com.szhn.hegx.bms.security.JwtAuthenticationFilter;
import com.szhn.hegx.bms.security.JwtAuthenticationProvider;
import com.szhn.hegx.bms.security.JwtLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
		http.cors().and()
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/swagger**/**").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers("/doc.html").permitAll()
				.antMatchers("/book/deleteById").access("hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll()
				.and().logout()
				.permitAll();
		http.csrf().disable();
		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().sameOrigin();
		// 退出登录处理器
		http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
		// 开启登录认证流程过滤器
		http.addFilterBefore(new JwtLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		// 访问控制时登录状态检查过滤器
		http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
	}


	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 使用自定义登录身份认证组件
		auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}