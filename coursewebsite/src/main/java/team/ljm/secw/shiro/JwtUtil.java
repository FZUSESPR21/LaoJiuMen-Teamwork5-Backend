package team.ljm.secw.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JwtUtil {
    //private static String secret="abcde";
    // 过期时间5分钟
    private static final long EXPIRE_TIME = 50*60*1000;

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String type, String account, String secret) {
        System.out.println("verify");
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("type", type)
                    .withClaim("account", account)
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            System.out.println("verify true:" + token);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("verify false:" + token);
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户账号
     */
    public static String getAccount(String token) {
        System.out.println("getAccount");
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("account").asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getType(String token) {
        System.out.println("getType");
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("type").asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     * @param account 用户账号
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String type, String account, String secret) {
        System.out.println("sign");
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("type", type)
                .withClaim("account", account)
                .withExpiresAt(date)
                .sign(algorithm);
    }

}
