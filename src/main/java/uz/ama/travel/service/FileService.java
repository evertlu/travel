package uz.ama.travel.service;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.ama.travel.dto.AttachmentDto;
import uz.ama.travel.model.Attachment;
import uz.ama.travel.model.Content;
import uz.ama.travel.repository.ContentRepository;
import uz.ama.travel.repository.FileRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private FileRepository repository;
    private ContentRepository contentRepository;

    @Autowired
    public FileService(FileRepository repository, ContentRepository contentRepository) {
        this.repository = repository;
        this.contentRepository = contentRepository;
    }

    @SneakyThrows
    public Integer save(AttachmentDto dto) {
        Attachment attachment = new Attachment();
        MultipartFile file = dto.getFile();
        attachment.setContentType(file.getContentType());
        attachment.setName(file.getName());
        attachment.setGeneratedName(System.currentTimeMillis() + file.getName() + "." + file.getContentType());
        attachment.setSize(file.getSize());

        Content content = new Content();
        content.setContent(file.getBytes());
        contentRepository.save(content);
        attachment.setContentId(content.getId());
        repository.save(attachment);
        return attachment.getId();
    }

    public ByteArrayResource download(Integer id) {
        Optional<Attachment> byId =
                repository.findById(id);
        if (!byId.isPresent()) {
            throw new RuntimeException("File not found");
        }
        Optional<Content> content = contentRepository.findById(byId.get().getContentId());
        ByteArrayResource resource = new ByteArrayResource(content.get().getContent());
        return resource;
    }

    public Attachment findById(Integer id) {
        Optional<Attachment> byId =
                repository.findById(id);
        if (!byId.isPresent()) {
            throw new RuntimeException("File not found");
        }
        return byId.get();
    }
}
