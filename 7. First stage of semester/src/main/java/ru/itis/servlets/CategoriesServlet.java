package ru.itis.servlets;

import ru.itis.model.Category;
import ru.itis.model.Product;
import ru.itis.repositories.categoryRepository.CategoryRepository;
import ru.itis.repositories.productRepository.ProductRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/categories")
public class CategoriesServlet extends HttpServlet {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public void init(ServletConfig config) {
        this.categoryRepository = (CategoryRepository) config.getServletContext().getAttribute("categoryRepository");
        this.productRepository = (ProductRepository) config.getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            List<Category> categories = categoryRepository.findAll();
            request.setAttribute("category", categories);
        } else {
            Long id = Long.parseLong(request.getParameter("id"));
            Optional<Category> category = categoryRepository.findById(id);
            List<Product> products = productRepository.findProductsByCategoryId(id);
            category.ifPresent(value -> request.setAttribute("name", value.getName()));
            request.setAttribute("products", products);
            request.getRequestDispatcher("/jsp/products.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/jsp/categories.jsp").forward(request, response);
    }
}
