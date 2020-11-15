package ru.itis.servlets;

import ru.itis.dto.UserDto;
import ru.itis.services.userService.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        usersService = (UsersService) config.getServletContext().getAttribute("usersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");
        UserDto dto = usersService.getUserByEmail(email);
        request.setAttribute("userDtoForJsp", dto);
        request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
    }


}
