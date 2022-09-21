package com.example.shoppingList.controller.aspect;

import com.example.shoppingList.constants.Authorities;
import com.example.shoppingList.dao.AuthorityRepository;
import com.example.shoppingList.entity.Authority;
import com.example.shoppingList.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@Aspect
@Order(Integer.MIN_VALUE + 1)
@Component
public class CheckUserAspect {

    private int userId;

    private String userName;
    private List<Authority> authorities;

    @Autowired
    private AuthorityRepository authorityRepository;

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Around("com.example.shoppingList.controller.aspect.AopExpressions.forControllerPackageWithoutHomeAndLoginAndRegistrationAndUserControllers()")
    public Object aroundControllerFunction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        if(authorities==null){
            logger.info("Login error. Authorities not initialized.");
            return "redirect:/showMyLoginPage";
        }

        if (!(authorities.contains(new Authority(userName, Authorities.ADMIN.value))||authorities.contains(new Authority(userName,Authorities.MANAGER.value)))) {

            Integer userId;

            if (joinPoint.getArgs()[0] instanceof Integer) {
                userId = ((Integer) joinPoint.getArgs()[0]);
                if (this.userId != userId) {
                    result = "redirect:/access-denied";
                }
            } else {
                result = "redirect:/access-denied";
            }
        }
        return result;
    }

    @Around("com.example.shoppingList.controller.aspect.AopExpressions.forHomePage()")
    public Object aroundHomePage(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = joinPoint.proceed();
        Object[] args = joinPoint.getArgs();
        HttpSession session;

        if (args.length > 0 && args[0] instanceof HttpSession) {
            session = (HttpSession) args[0];
            User user = (User) session.getAttribute("user");
            this.authorities = authorityRepository.findAllAuthoritiesByUser(user);

            if (user != null) {
                this.userId = user.getId();
                this.userName = user.getUserName();
            } else {
                result = "redirect:/showMyLoginPage";
            }
        }

        return result;
    }
}
