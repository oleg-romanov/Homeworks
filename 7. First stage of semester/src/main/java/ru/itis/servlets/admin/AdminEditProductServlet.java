package ru.itis.servlets.admin;

import ru.itis.model.Category;
import ru.itis.model.Product;
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
import java.util.Optional;

@WebServlet("/admin/products/edit/*")
@MultipartConfig
public class AdminEditProductServlet extends HttpServlet {

    private FileUploadService filesService;
    private ProductRepository productRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.filesService = (FileUploadService) config.getServletContext().getAttribute("filesUploadService");
        this.productRepository = (ProductRepository) config.getServletContext().getAttribute("productRepository");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdString = request.getRequestURI().substring(21);
        System.out.println(productIdString);
        if(productIdString.isEmpty()) {
            request.setAttribute("error", "Ошибка! Требуется id");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }
        Long productId = Long.parseLong(productIdString);
        Optional<Product> optionalProduct = productRepository.findProductById(productId);
        if(optionalProduct.isPresent() == false) {
            request.setAttribute("error", "Ошибка! Not found");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }
        Product product = optionalProduct.get();
        request.setAttribute("product", product);
        request.getRequestDispatcher("/jsp/admin/editProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdString = request.getRequestURI().substring(21);
        if(productIdString.isEmpty()) {
            request.setAttribute("error", "Ошибка! Требуется id");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }
        Long productId = Long.parseLong(productIdString);

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
        System.out.println("описание с формы: " + request.getParameter("description"));
        Product product = Product.builder()
                .imageId(imageId)
                .name(request.getParameter("name"))
                .categoryId(Long.parseLong(request.getParameter("categoryId")))
                .description(request.getParameter("description"))
                .build();
        productRepository.updateProduct(productId, product);
        response.sendRedirect("/admin?id=" + product.getCategoryId());
    }
}
