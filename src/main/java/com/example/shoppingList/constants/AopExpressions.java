package com.example.shoppingList.constants;

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

    @Pointcut("execution(* com.example.shoppingList.controller.HomeController.showFormForUpdate (..))")
    public void forShowFormForUpdate(){}

    @Pointcut("execution(* com.example.shoppingList.controller.HomeController.save (..))")
    public void forSave(){}

    @Pointcut("(forSave() || forShowFormForUpdate() || forControllerPackage() ) &&" +
            " !(forHomeController() || forLoginController() || forRegistrationController())")
    public void forControllerPackageWithoutHomeAndLoginAndRegistrationAndUserControllers() {
    }
}
