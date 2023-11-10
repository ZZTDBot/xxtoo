package com.tg.base.tb.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tg.base.tb.exception.BaseException;
import com.tg.base.tb.exception.enum1.BaseErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT的工具类
 */
public class JwtHelper {

    private static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);

      public static final String USER_ID_KEY = "tgUserKey";
      public static final String BOT_INS_ID = "botInstanceId";
      private static final String SECRET = "token_secret";

      private static final String ISSUER = "user";


      public static String getToken(Map<String, String> claims) {
        try {
          Algorithm algorithm = Algorithm.HMAC256(SECRET);
          JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER);
          claims.forEach((k, v) -> builder.withClaim(k, v));
          return builder.sign(algorithm).toString();
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
          throw new BaseException(BaseErrorEnum.PRODUCE_TOKEN_ERROR.getCode(),
                  BaseErrorEnum.PRODUCE_TOKEN_ERROR.getMessage());
        }
      }

      public static Map<String, String> verifyToken(String token) {
        Algorithm algorithm = null;
        Map<String, String> resultMap = new HashMap<>();
        try {
          algorithm = Algorithm.HMAC256(SECRET);
          JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
          DecodedJWT jwt = verifier.verify(token);
          Map<String, Claim> map = jwt.getClaims();
          map.forEach((k, v) -> resultMap.put(k, v.asString()));
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        } finally {
          return resultMap;
        }
      }

  public static void main(String[] args) {
    Map<String, String> claims = new HashMap<>();

    claims.put("userName", "iii");
    claims.put("passWord", "222");

    String token = getToken(claims);

    System.out.println(token);

    Map<String, String> stringStringMap = verifyToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ1c2VyIiwidGdVc2VyS2V5IjoiNTU0ODQ4NjQwNCJ9.w58kQJ_trlHP4mQEAppFoQCOQSWQEXv3yBe1b4ArsL0");

    System.out.println(stringStringMap);


  }

}
