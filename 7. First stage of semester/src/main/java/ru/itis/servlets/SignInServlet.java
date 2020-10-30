package ru.itis.servlets;

import ru.itis.dto.SignInForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.signInServices.SignInService;
import ru.itis.services.signUpServices.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

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

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignInForm form = new SignInForm();

        form.setEmail(request.getParameter("email"));
        form.setPassword(request.getParameter("password"));

        boolean isCorrect = signInService.signIn(form);

        if (isCorrect) {
            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute("authenticated", true);
            // httSession.setAttribute("user", currentUser);
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/signIn");
        }
    }
}
