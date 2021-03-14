//package com.machine.manager.util;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.machine.manager.entity.UserInfo;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.util.Date;
//
//public class JwtTokenUtils2 {
//
//    public static final String TOKEN_HEADER = "Authorization";
//    public static final String TOKEN_PREFIX = "Bearer ";
//
//    private static final String SECRET = "jwtsecretdemo";
//    private static final String ISS = "echisan";
//
//    // 过期时间是3600秒，既是1个小时
//    private static final long EXPIRATION = 3600L;
//
//    // 选择了记住我之后的过期时间为7天
//    private static final long EXPIRATION_REMEMBER = 604800L;
//
//    // 创建token
//    public static String createToken(String username, boolean isRememberMe) {
//        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
//        return Jwts.builder()
//                .signWith(SignatureAlgorithm.HS512, SECRET)
//                .setIssuer(ISS)
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
//                .compact();
//    }
//
//    public String getToken(UserInfo user) {
//        Date start = new Date();
//        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
//        Date end = new Date(currentTime);
//        String token = "";
//
//        token = JWT.create().withAudience(user.getId() + "").withIssuedAt(start).withExpiresAt(end)
//                .sign(Algorithm.HMAC256(user.getPassword()));
//        return token;
//    }
//
//    // 从token中获取用户名
//    public static String getUsername(String token){
//        return getTokenBody(token).getSubject();
//    }
//
//    // 是否已过期
//    public static boolean isExpiration(String token){
//        return getTokenBody(token).getExpiration().before(new Date());
//    }
//
//    private static Claims getTokenBody(String token){
//        return Jwts.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}