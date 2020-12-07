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
import java.util.Optional;


@WebServlet("/admin/categories/edit/*")
@MultipartConfig
public class AdminEditCategoryServlet extends HttpServlet {

    private FileUploadService filesService;
    private CategoryRepository categoryRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.filesService = (FileUploadService) config.getServletContext().getAttribute("filesUploadService");
        this.categoryRepository = (CategoryRepository) config.getServletContext().getAttribute("categoryRepository");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryIdString = request.getRequestURI().substring(23);
        if(categoryIdString.isEmpty()) {
            request.setAttribute("error", "Ошибка! Требуется id");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }
        Long categoryId = Long.parseLong(categoryIdString);
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent() == false) {
            request.setAttribute("error", "Ошибка! Not found");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }
        Category category = optionalCategory.get();
        request.setAttribute("category", category);
        request.getRequestDispatcher("/jsp/admin/editCategory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryIdString = request.getRequestURI().substring(23);
        if(categoryIdString.isEmpty()) {
            request.setAttribute("error", "Ошибка! Требуется id");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }
        Long categoryId = Long.parseLong(categoryIdString);

        Part part = request.getPart("image");
        Long imageId;
        if(part != null && part.getSize() != 0) {
            String fileName = filesService.saveFileToStorage(part.getInputStream(),
                    part.getSubmittedFileName(),
                    part.getContentType(),
                    part.getSize());
            imageId = filesService.getImage(fileName).getId();
        } else {
            imageId = Long.parseLong(request.getParameter("imageId"));
        }
        Category category = Category.builder().
                imageId(imageId).
                name(request.getParameter("name"))
                .build();
        categoryRepository.updateCategory(categoryId, category);
        response.sendRedirect("/admin");
    }
}
