package ru.itis.servlets.admin;

import ru.itis.model.Category;
import ru.itis.repositories.categoryRepository.CategoryRepository;
import ru.itis.services.fileUpdoadService.FileUploadService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/admin/categories/add")
@MultipartConfig
public class AdminAddCategoryServlet extends HttpServlet {

    private FileUploadService filesService;
    private CategoryRepository categoryRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.filesService = (FileUploadService) config.getServletContext().getAttribute("filesUploadService");
        this.categoryRepository = (CategoryRepository) config.getServletContext().getAttribute("categoryRepository");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/admin/addCategory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("image");
        String fileName = filesService.saveFileToStorage(part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());
        Long imageId = filesService.getImage(fileName).getId();
        Category category = Category.builder().
                imageId(imageId).
                name(request.getParameter("name"))
                .build();
        categoryRepository.save(category);
        response.sendRedirect("/admin");
    }
}