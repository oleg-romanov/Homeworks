package ru.itis.servlets.images;

import ru.itis.repositories.usersRepository.UsersRepository;
import ru.itis.services.fileUpdoadService.FileUploadService;
import ru.itis.services.userService.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/image-upload")
@MultipartConfig
public class ImageUploadServlet extends HttpServlet {

    private FileUploadService filesService;
    private UsersRepository usersRepository;

    @Override
    public void init(ServletConfig config) {
        this.filesService = (FileUploadService) config.getServletContext().getAttribute("filesUploadService");
        this.usersRepository = (UsersRepository) config.getServletContext().getAttribute("usersRepository");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("image");
            String fileName = filesService.saveFileToStorage(part.getInputStream(),
                    part.getSubmittedFileName(),
                    part.getContentType(),
                    part.getSize());

            String profileIdString = request.getParameter("profile_id");
            if (profileIdString != null && profileIdString.isEmpty() == false) {
                System.out.println("Changing avatar for user: " + profileIdString + " to " + fileName);
                Long profileId = Long.parseLong(profileIdString);
                Long imageId = filesService.getImage(fileName).getId();
                usersRepository.updateImageId(profileId, imageId);
            }
            response.sendRedirect("/profile");
    }
}

