package com.machine.manager.entity;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.machine.manager.jwt.CustomAuthorityDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * user_info
 * @author 
 */
@Proxy(lazy = false)
@Entity
@Table(name = "user_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable, UserDetails {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id)
                && Objects.equals(roleId, userInfo.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,   roleId);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "user_type")
    private String userType;


    @Column(name = "company_name")
    private String companyName;

    @Column(name = "stores_count")
    private int storesCount;
    private static final long serialVersionUID = 1L;


    //实体类中使想要添加表中不存在字段，就要使用@Transient这个注解了。
    @Transient
    private List<GrantedAuthority> authorities;


    @JsonDeserialize(using = CustomAuthorityDeserializer.class)
    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.authorities;
        if(null != userType && !"".equals(userType)){
            String[] sp = userType.split(",");

            List<GrantedAuthority> authorities = new ArrayList<>();
            for(String role : sp){
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return authorities;

        }

        return null;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserInfo getUserInfo() {
        UserInfo userInfo = null;
        // 获取用户认证信息对象。
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 认证信息可能为空，因此需要进行判断。
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserInfo) {
                userInfo = (UserInfo) principal;
            }
        }
        return userInfo;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用  ,禁用的用户不能身份验证
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}