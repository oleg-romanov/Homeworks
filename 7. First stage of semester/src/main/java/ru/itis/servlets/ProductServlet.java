package ru.itis.servlets;

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
import java.util.Optional;

@WebServlet("/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init(ServletConfig config) {
        this.productRepository = (ProductRepository) config.getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdString = request.getRequestURI().substring(9);
        if(productIdString.isEmpty()) {
            String error = "Ошибка! Требуется productId";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }
        Long productId = Long.parseLong(productIdString);
        Optional<Product> optionalProduct = productRepository.findProductById(productId);
        if (optionalProduct.isPresent() == false) {
            String error = "Ошибка! Продукт не найден...";
            request.setAttribute("error", error);
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }

        Product product = optionalProduct.get();
        System.out.println("Получение продукта " + product.getName());
        request.setAttribute("product", product);
        request.getRequestDispatcher("/jsp/detailProduct.jsp").forward(request, response);
    }
}
