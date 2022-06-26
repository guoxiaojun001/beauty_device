//package com.machine.manager.entity;
//
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.machine.manager.jwt.CustomAuthorityDeserializer;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Proxy;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//
///**
// * user_info
// * @author
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class UserData implements Serializable  {
//
//
//    private Integer id;
//
//    private String name;
//
//    private String email;
//
//    private String telephone;
//
//    private String address;
//
//    private String password;
//
//    private String roleId;
//
//    private String userType;
//
//    private String companyName;
//
//    private int storesCount;
//
//
//    private Integer newStoresCount;
//
//    //实体类中使想要添加表中不存在字段，就要使用@Transient这个注解了。
//
//
//}