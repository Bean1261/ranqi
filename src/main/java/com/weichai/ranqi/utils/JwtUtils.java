package com.weichai.ranqi.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.cert.X509CertSelector;
import java.util.Date;

public class JwtUtils {
    // Token有效期（毫秒）
    private static long expire = 604800000; // 7天
    // 密钥
    private static String secret = "asscdjdjdjvneoslvkdjfqmfsmvldife";

    /**
     * 生成JWT Token
     *
     * @param username 用户名
     * @return JWT字符串
     */
    public static String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expire);

        return Jwts.builder()
                .setSubject(username) // 设置主题（一般是用户信息）
                .setIssuedAt(now) // 签发时间
                .setExpiration(expiration) // 过期时间
                .signWith(SignatureAlgorithm.HS256, secret) // 签名算法和密钥
                .compact(); // 生成最终的Token字符串
    }

    /**
     * 验证并解析JWT Token
     *
     * @param token JWT字符串
     * @return Token中的主体信息（例如用户名）
     */
    public static String validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret) // 设置密钥
                    .parseClaimsJws(token) // 解析Token
                    .getBody() // 获取载荷
                    .getSubject(); // 获取主题（用户名）
        } catch (Exception e) {
            return null; // 如果验证失败，返回null
        }
    }

    /**
     * 检查Token是否过期
     *
     * @param token JWT字符串
     * @return true表示过期，false表示未过期
     */
    public static boolean isTokenExpired(String token) {
        try {
            Date expiration = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true; // 如果解析出错，视为过期
        }
    }

    public static Claims getClaimsByToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
