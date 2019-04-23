package com.qilinxx.rms.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        //排除配置
        addInterceptor.excludePathPatterns("/image");
        addInterceptor.excludePathPatterns("/error");
        addInterceptor.excludePathPatterns("/login**/**");
        addInterceptor.excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg");
        // addInterceptor.excludePathPatterns("/captcha");//排除验证码
        //拦截配置
        addInterceptor.addPathPatterns("/**/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            HttpSession session = request.getSession();
            ServletContext servletContext=session.getServletContext();
            @SuppressWarnings("unchecked")
            Map<String, Object> loginMap = (Map<String, Object>) servletContext.getAttribute("loginMap");
            if (loginMap == null) {
                loginMap = new HashMap<>();
                servletContext.setAttribute("loginMap", loginMap);
            }
            String uid= (String) session.getAttribute("uid");
            String sessionId= (String) session.getAttribute("sessionId");
            String saveId= (String) loginMap.get(uid);
            if (uid!=null&&saveId!=null && saveId.equals(sessionId)) return true;
            String url ;
            if (sessionId==null){
                if (uid==null){
                    url="/loginLose?msg=1";
                }else{
                url = "/login";
                }
            }else{
                url="/loginLose?msg=2";
            }
            //跳转到登录页
            response.sendRedirect(url);
            return false;
        }
    }

}