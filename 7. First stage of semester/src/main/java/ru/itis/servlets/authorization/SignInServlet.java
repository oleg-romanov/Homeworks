package ru.itis.servlets.authorization;

import ru.itis.dto.SignInForm;
import ru.itis.services.signInServices.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.validation.Validator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 16.10.2020
 * 4. Simple Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private SignInService signInService;
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
        validator = (Validator) config.getServletContext().getAttribute("validator");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signIn.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignInForm form = new SignInForm();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        form.setEmail(email);
        form.setPassword(password);

        boolean isCorrect = signInService.signIn(form, false);

        if (isCorrect) {
            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute("authenticated", true);
            httpSession.setAttribute("email", form.getEmail());
            //httSession.setAttribute("user", currentUser);
            addAuthenticationCookies(response, email, password);
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/signIn");
        }
    }

    private void addAuthenticationCookies(HttpServletResponse response, String email, String password) throws UnsupportedEncodingException {
        // Create cookies for first and last names.
        Cookie emailCookie = new Cookie("email",
                URLEncoder.encode(email, "UTF-8"));
        Cookie passwordCookie = new Cookie("password",
                URLEncoder.encode(password, "UTF-8"));

        // Set expiry date after 24 Hrs for both the cookies.
        emailCookie.setMaxAge(60 * 60 * 24 * 25);
        passwordCookie.setMaxAge(60 * 60 * 24 * 25);

        // Add both the cookies in the response header.
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
    }
}
