package ru.itis.servlets.admin;

import ru.itis.model.Category;
import ru.itis.model.Product;
import ru.itis.repositories.categoryRepository.CategoryRepository;
import ru.itis.repositories.productRepository.ProductRepository;
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
import java.util.List;

@WebServlet("/admin/products/add")
@MultipartConfig
public class AdminAddProductServlet extends HttpServlet {
        private FileUploadService filesService;
        private ProductRepository productRepository;
        private CategoryRepository categoryRepository;

        @Override
        public void init(ServletConfig config) throws ServletException {
            this.filesService = (FileUploadService) config.getServletContext().getAttribute("filesUploadService");
            this.productRepository = (ProductRepository) config.getServletContext().getAttribute("productRepository");
            this.categoryRepository = (CategoryRepository) config.getServletContext().getAttribute("categoryRepository");

        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Category> categories = categoryRepository.findAll();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/jsp/admin/addProduct.jsp").forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Part part = request.getPart("image");
            String fileName = filesService.saveFileToStorage(part.getInputStream(),
                    part.getSubmittedFileName(),
                    part.getContentType(),
                    part.getSize());
            Long imageId = filesService.getImage(fileName).getId();
            System.out.println("Категория, которую выбрали: " + request.getParameter("categoryId"));
            Product product = Product.builder()
                    .imageId(imageId)
                    .name(request.getParameter("name"))
                    .categoryId(Long.parseLong(request.getParameter("categoryId")))
                    .description(request.getParameter("description"))
                    .build();

            productRepository.save(product);
            response.sendRedirect("/admin?id=" + product.getCategoryId());
        }
}
