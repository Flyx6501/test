package com.example.test.aspect;

import com.example.test.config.CookieConstant;
import com.example.test.exception.SellerAuthorizeException;
import com.example.test.util.CookieUtil;
import com.example.test.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class UserAuthorizeAspect {
   @Pointcut("execution(public * com.example.test.controller.User*.*(..))")
   /* @Pointcut("execution(public * com.example.test.controller.Seller*.*(..))" +
            "&& !execution(public * com.example.test.controller.SellerUserController.*(..))")*/
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        //查询cookie

        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);



       if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
