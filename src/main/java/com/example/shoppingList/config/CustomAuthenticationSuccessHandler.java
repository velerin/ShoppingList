package com.example.shoppingList.config;

import com.example.shoppingList.constants.Roles;
import com.example.shoppingList.dao.AuthorityRepository;
import com.example.shoppingList.entity.Authority;
import com.example.shoppingList.entity.User;
import com.example.shoppingList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String userName = authentication.getName();

		User user = userService.findByUserName(userName);
		List<Authority> authorities = authorityRepository.findAllAuthoritiesByUser(user);

		HttpSession session = request.getSession();
		session.setAttribute("user", user);


		if(authorities.contains(new Authority(Roles.ADMIN.value, userName,user))){
			response.sendRedirect(request.getContextPath() + "/admins");
			return;
		}
		if(authorities.contains(new Authority(Roles.MANAGER.value, userName,user))){
			response.sendRedirect(request.getContextPath() + "/leaders");
			return;
		}
		if(authorities.contains(new Authority(Roles.USER.value, userName,user))){
			response.sendRedirect(request.getContextPath() + "/user");
			return;
		}

		response.sendRedirect(request.getContextPath() + "/error");
	}

}
