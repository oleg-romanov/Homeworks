package ru.itis.servlets.images;

import ru.itis.model.Image;
import ru.itis.services.fileUpdoadService.FileUploadService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/images/*")
public class ImageDownloadServlet extends HttpServlet {

    private FileUploadService filesService;

    @Override
    public void init(ServletConfig config) {
        this.filesService = (FileUploadService) config.getServletContext().getAttribute("filesUploadService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String fileIdString = req.getRequestURI().substring(8);
        Long fileId = Long.parseLong(fileIdString);
        // получили информацию о загруженном файле
        Image image = filesService.getImage(fileId);
        // в ответ указали какого-типа данные уйдут клиенту
        response.setContentType(image.getType());
        // в ответ указали какой размер данных
        response.setContentLength(image.getSize().intValue());
        // в ответ указали оригинальнгое название файла
        response.setHeader("Content-Disposition", "filename=\"" + image.getName() + "\"");
        // записываем данные самого файла в ответ
        System.out.println();
        filesService.writeFileFromStorage(fileId, response.getOutputStream());
        response.flushBuffer();
    }
}

