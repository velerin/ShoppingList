package com.example.shoppingList.controller.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class AopExpressions {

    @Pointcut("execution(* com.example.shoppingList.controller.*Controller.* (..))")
    public void forControllerPackage() {
    }

    @Pointcut("execution(* com.example.shoppingList.controller.HomeController.* (..))")
    public void forHomeController() {
    }

    @Pointcut("execution(* com.example.shoppingList.controller.LoginController.* (..))")
    public void forLoginController() {
    }

    @Pointcut("execution(* com.example.shoppingList.controller.UserController.* (..))")
    public void forUserController() {
    }

    @Pointcut("execution(* com.example.shoppingList.controller.RegistrationController.* (..))")
    public void forRegistrationController() {
    }
    @Pointcut("execution(* com.example.shoppingList.controller.HomeController.home (..))")
    public void forHomePage(){}

    @Pointcut("forControllerPackage() && !(forHomeController() || forLoginController() || forRegistrationController()|| forUserController())")
    public void forControllerPackageWithoutHomeAndLoginAndRegistrationAndUserControllers() {
    }
}
