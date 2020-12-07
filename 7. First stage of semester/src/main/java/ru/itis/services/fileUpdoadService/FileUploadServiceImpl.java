package ru.itis.services.fileUpdoadService;

import ru.itis.model.Image;
import ru.itis.repositories.filesRepository.ImagesRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUploadServiceImpl implements FileUploadService {
    private final String path = "/Users/olegromanov/IdeaProjects/Homeworks/7. First stage of semester/files/";

    private ImagesRepository imagesRepository;

    public FileUploadServiceImpl(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Override
    public String saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size) {
        String name =  UUID.randomUUID().toString();

        Image image = Image.builder()
                .name(name)
                .size(size)
                .type(contentType)
                .build();

        try {
            Files.copy(file, Paths.get(path + image.getName() + "." + image.getType().split("/")[1]));
            imagesRepository.save(image);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return name;
    }

    @Override
    public void writeFileFromStorage(Long fileId, OutputStream outputStream) {
        // нашли файл в базе
        Image image = imagesRepository.findById(fileId);
        // нашли файл на диске
        File file = new File(path + image.getName() + "." + image.getType().split("/")[1]);
        try {
            // записали его в ответ
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Image getImage(Long fileId) {
        return imagesRepository.findById(fileId);
    }

    @Override
    public Image getImage(String name) {
        return imagesRepository.findByName(name);
    }
}
