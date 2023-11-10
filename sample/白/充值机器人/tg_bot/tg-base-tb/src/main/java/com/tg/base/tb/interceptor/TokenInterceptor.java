package com.tg.base.tb.interceptor;

import com.tg.base.tb.context.TokenUser;
import com.tg.base.tb.context.UserContext;
import com.tg.base.tb.exception.BaseException;
import com.tg.base.tb.exception.enum1.BaseErrorEnum;
import com.tg.base.tb.utils.JwtHelper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)) {
            throw new BaseException(BaseErrorEnum.TOKEN_ERROR_NOT_NULL.getCode(),
                    BaseErrorEnum.TOKEN_ERROR_NOT_NULL.getMessage());
        }
        this.setContext(token);
        return super.preHandle(request, response, handler);
    }

    private TokenUser setContext(String token) {
        Map<String, String> map = JwtHelper.verifyToken(token);

        if(map == null && !map.containsKey(JwtHelper.USER_ID_KEY) && !map.containsKey(JwtHelper.BOT_INS_ID)) {
            throw new BaseException(String.valueOf(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value()),
                    HttpStatus.NON_AUTHORITATIVE_INFORMATION.getReasonPhrase());
        }

        String tgUserId = map.get(JwtHelper.USER_ID_KEY);
        String botInstanceId = map.get(JwtHelper.BOT_INS_ID);

        TokenUser tokenUser = new TokenUser();
        tokenUser.setTgUserId(Long.valueOf(tgUserId));
        tokenUser.setBotInstanceId(Integer.valueOf(botInstanceId));

        UserContext.setUser(tokenUser);
        return tokenUser;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        UserContext.remove();
    }
}
