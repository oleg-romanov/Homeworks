package ru.itis.services.fileUpdoadService;

import ru.itis.model.Image;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileUploadService  {
    String saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size);
    void writeFileFromStorage(Long fileId, OutputStream outputStream);
    Image getImage(Long fileId);
    Image getImage(String name);
}
