package uz.ama.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.ama.travel.dto.AttachmentDto;
import uz.ama.travel.dto.LoginDto;
import uz.ama.travel.dto.ResponceDto;
import uz.ama.travel.dto.SignupDto;
import uz.ama.travel.model.Attachment;
import uz.ama.travel.service.FileService;
import uz.ama.travel.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class UserController {


    private UserService userService;
    private FileService fileService;

    @Autowired
    public UserController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    //signup
    @PostMapping("/signup")
    public ResponceDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }


    //login
    @PostMapping("/login")
    public ResponceDto login(@RequestBody LoginDto loginDto){ return userService.login(loginDto);}


    @PostMapping(value = "/upload")
    public Integer upload(@RequestParam(name = "file") MultipartFile dto) {
        return fileService.save(new AttachmentDto(dto));
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<?> download(@PathVariable(name = "id") Integer id) {
        Attachment attachment = fileService.findById(id);
        ByteArrayResource resource = fileService.download(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getName() +  "\"")
                .body(resource);
    }

}
