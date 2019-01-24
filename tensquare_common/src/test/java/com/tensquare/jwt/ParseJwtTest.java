package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ParseJwtTest {

    public static void main(String[] args) {
        Claims claims = Jwts.parser()
                .setSigningKey("itcast")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_mmI4iLCJpYXQiOjE1NDgzMTk4NzUsImV4cCI6MTU0ODMxOTkzNX0.ircTnuCQHTKe3mbRSekvSXF2SLXztUBcbLKYRDyeKU4")
                .getBody();

        System.out.println("用户id:" + claims.getId());
        System.out.println("用户名:" + claims.getSubject());
        System.out.println("登陆时间:" + claims.getIssuedAt());
        System.out.println("过期时间:" + claims.getExpiration());

    }
}
