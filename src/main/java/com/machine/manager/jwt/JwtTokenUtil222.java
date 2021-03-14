package com.machine.manager.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.machine.manager.entity.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Data
@Component
public class JwtTokenUtil222 {
    //公用密钥-保存在服务端,客户端是不会知道密钥的,以防被攻击
    public static String SECRET = "ThisIsASecret";

    //生成Troke
    public static String createToken(UserInfo loginUser) {
        //签发时间
        Date iatDate = new Date();
        //过地时间 1分钟后过期
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, 1);
        Date expiresDate = nowTime.getTime();
        Map<String, Object> map = new HashMap();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        //        Map<String, Object> claims = new HashMap<>(3);
//        claims.put("sub", userDetails.getUsername());
//        claims.put("created", new Date());
//
//        claims.put("authorities", userDetails.getAuthorities());

       List<GrantedAuthority>  au = (List<GrantedAuthority>) loginUser.getAuthorities();

        String token = JWT.create()
                .withHeader(map)
                //.withClaim( "name","Free码生") //设置 载荷 Payload
                //.withClaim("age","12")
                //.withClaim( "org","测试")
                .withClaim("userId",loginUser.getId())
                .withClaim("userName",loginUser.getName())
                .withClaim("userPhone",loginUser.getTelephone())
                .withClaim("userType",loginUser.getUserType())
                .withExpiresAt(expiresDate)//设置过期时间,过期时间要大于签发时间
                .withIssuedAt(iatDate)//设置签发时间
                .withAudience(loginUser.getName()) //设置 载荷 签名的观众
                .sign(Algorithm.HMAC256(SECRET));//加密
        System.out.println("后台生成token:" + token);
        return token;
    }

    //校验TOKEN
    public static boolean verifyToken(String token) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        try {
            verifier.verify(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

        public static Claims getClaimsFromToken(String token) {
        Claims claims = null;

        try {
            //getKeyInstance(), SignatureAlgorithm.HS256
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
//            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    //获取Token信息
    public static DecodedJWT getTokenInfo(String token) throws UnsupportedEncodingException{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        try{
            return verifier.verify(token);
        } catch(Exception e){
            return null;
        }
    }
}
