//package com.machine.manager;
//
//import com.machine.manager.jwt.JwtTokenUtils;
//import org.springframework.context.ApplicationContext;
//
//public class GlobaleApplication {
//
//
//    public static ApplicationContext applicationContext;
//   static JwtTokenUtils jwtTokenUtils;
//
//    public static ApplicationContext getApplicationContext() {
//        return GlobaleApplication.applicationContext;
//    }
//
//    public static void setApplicationContext(ApplicationContext applicationContext) {
//        GlobaleApplication.applicationContext = applicationContext;
//    }
//
//    public static JwtTokenUtils getJwtUtil(  ) {
//        return  jwtTokenUtils;
//    }
//
//    public static void setjwtTokenUtils(  JwtTokenUtils jwt) {
//        jwtTokenUtils = jwt;
//    }
//
//    public static <T> T getBean(String name, Class<T> clazz) {
//        return getApplicationContext().getBean(name, clazz);
//    }
//}
