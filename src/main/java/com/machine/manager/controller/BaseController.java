package com.machine.manager.controller;

import com.machine.manager.dao.JwtUserDao;
import com.machine.manager.jwt.JwtTokenUtil222;
import com.machine.manager.jwt.RestResult;
import com.machine.manager.reject.UserLoginToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 描述：
 * <p>
 *
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected JwtTokenUtil222 jwtTokenUtils;
    @Autowired
    protected JwtUserDao jwtUserDao;

    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 获取用户所拥有的权限列表
     * @return
     */
    public List<String> getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> list = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            logger.info("权限列表：{}", grantedAuthority.getAuthority());
            list.add(grantedAuthority.getAuthority());
        }
        return list;
    }

    public static RestResult preCheck(String methodName, Class  classT){
//        RestResult restResult = new RestResult();
//
//        boolean result = false;
//        try {
//            result = checkAuthByAnotation(methodName ,classT);
//            if(result){
//                restResult.setCode(200);
//                restResult.setSuccess(false);
//                restResult.setMsg("没有权限或者权限异常，请联系管理员");
//                return restResult;
//            }
//        } catch (Exception e) {
//            System.out.print("xxxxxxxxxxxxx ==  " + e.getLocalizedMessage() );
//            restResult.setCode(200);
//            restResult.setSuccess(false);
//            restResult.setMsg("没有权限或者权限异常，请联系管理员");
//            return restResult;
//        }

        return null;
    }

    public static boolean checkAuthByAnotation(String methodName, Class  classT) throws Exception {

        Method m = classT.getMethod(methodName);
        System.out.println("xxxxxxxxxxxxx ==  " + m );
        Annotation[] annotations = m.getAnnotations();

        if(null == annotations || annotations.length == 0){
            System.out.println("没有权限的方法 "  +methodName);
            return true;
        }

        for (Annotation an : annotations){
            if(an.annotationType().getSimpleName().equals(UserLoginToken.class.getSimpleName())){
                System.out.print("ss = " + an.annotationType().getSimpleName());
                return true;
            }
        }

        return false;
}



}
