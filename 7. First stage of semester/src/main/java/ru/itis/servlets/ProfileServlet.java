package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.dto.UserDto;
import ru.itis.services.userService.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UsersService usersService;
    private ObjectMapper objectMapper = new ObjectMapper();

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean changeIsSuccess = false;
        String message = "";
        String oldEmail = (String) request.getSession().getAttribute("email");
        UserDto oldData = usersService.getUserByEmail(oldEmail);

        UserDto newData = objectMapper.readValue(request.getReader(), UserDto.class);

        changeIsSuccess = usersService.updateDataOfUser(newData, oldData);

        if (changeIsSuccess) {
            if (!newData.getEmail().equals("")) {
                response.setStatus(HttpServletResponse.SC_OK);
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("email", newData.getEmail());
                UserDto dto = usersService.getUserByEmail(newData.getEmail());
                request.setAttribute("userDtoForJsp", dto);
                response.sendRedirect("/profile");
            }
            message = "Ваши данные успешно обновлены";
        } else {
            message = "Ваши данные не были обновлены";
        }
        response.getWriter().write(message);
    }
}
