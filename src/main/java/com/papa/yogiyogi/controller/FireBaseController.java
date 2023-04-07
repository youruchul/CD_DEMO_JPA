package com.papa.yogiyogi.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.papa.yogiyogi.service.FireBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/yogi")
@RequiredArgsConstructor
public class FireBaseController {
    private final FireBaseService fireBaseService;

    @PostMapping("/files")
    public String uploadFile(@RequestParam("file") MultipartFile file, String nameFile)
            throws FirebaseAuthException, IOException {
        if (file.isEmpty()) {
            return "is empty";
        }
        return fireBaseService.uploadFiles(file, nameFile);
    }
}
