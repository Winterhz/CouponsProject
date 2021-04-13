package com.kostya.coupons.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kostya.coupons.loginData.UserLoginData;
import com.kostya.coupons.logic.CacheController;


@Component
public class LoginFilter implements Filter {

	@Autowired
	private CacheController cacheController;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String pageRequested = req.getRequestURL().toString();

		if(pageRequested.endsWith("/login") || pageRequested.endsWith("/home")) {
			chain.doFilter(request, response);
			return;
		}

		if(pageRequested.endsWith("/users") && req.getMethod().toString().equals("POST")) {
			chain.doFilter(request, response);
			return;
		}

		String token = req.getHeader("Authorization");
		UserLoginData userLoginData = (UserLoginData) cacheController.get(token);

		if(userLoginData!=null) {
			request.setAttribute("userLoginData", userLoginData);
			//logged in, move to next stage
			chain.doFilter(request, response);
			return;
		}

		HttpServletResponse res = (HttpServletResponse) response;
		// not logged in
		res.setStatus(401);
	}


	
	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
