package ru.itis.filters;

import com.sun.org.apache.xpath.internal.operations.Bool;
import ru.itis.dto.SignInForm;
import ru.itis.services.signInServices.SignInService;
import ru.itis.services.userService.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private SignInService signInService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        signInService = (SignInService) filterConfig.getServletContext().getAttribute("signInService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        Boolean isAuthenticated = false;
        boolean sessionExists = session != null;
        boolean isLoginPage = request.getRequestURI().equals("/signIn");
        boolean isSignUpPage = request.getRequestURI().equals("/signUp");
        boolean isAdminPage = request.getRequestURI().contains("/admin");
        boolean isStaticResource = request.getRequestURI().startsWith("/css/");

        if (sessionExists) {
            isAuthenticated = (Boolean) session.getAttribute("authenticated");

            if (isAuthenticated == null) {
                isAuthenticated = false;
            }
        }

        if (isAuthenticated == false || isAdminPage) {
            isAuthenticated = checkCookie(request, isAdminPage);
        }

        // если авторизован и запрашивает не логин или если не авторизован и запрашивает логин
        if (isAuthenticated && !isLoginPage || !isAuthenticated && isLoginPage || !isAuthenticated && isSignUpPage || isStaticResource) {
            // отдаем ему то, что он хочет
            filterChain.doFilter(request, response);
        } else if (isAuthenticated) {
            // пользователь аутенцифицирован и запрашивает страницу входа
            // - отдаем ему корень
            response.sendRedirect("/profile");
        } else {
            // если пользователь не аутенцицицирован и запрашивает другие страницы
            response.sendRedirect("/signIn");
        }

    }

    private boolean checkCookie(ServletRequest servletRequest, Boolean asAdmin) throws UnsupportedEncodingException {
        String email = "";
        String password = "";
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if ("email".equals(c.getName())) {
                    email = URLDecoder.decode(c.getValue(), "UTF-8");
                } else if ("password".equals(c.getName())) {
                    password = URLDecoder.decode(c.getValue(), "UTF-8");
                }
            }
        }

        if (email.equals("") || password.equals("")) { return false; }

        SignInForm form = new SignInForm();
        form.setEmail(email);
        form.setPassword(password);
        boolean isAuthenticated = signInService.signIn(form, asAdmin);

        if (isAuthenticated) {
            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute("authenticated", true);
            httpSession.setAttribute("email", email);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void destroy() {}
}
