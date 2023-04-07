package com.papa.yogiyogi.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FireBaseService {
    @Value("${app.firebase-bucket}")
    private String firebaseBucket;
    public String uploadFiles (MultipartFile file, String nameFile) throws FirebaseAuthException, IOException {
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(file.getBytes());
        Blob blob = bucket.create(nameFile.toString(), content, file.getContentType());
        String url =
        "https://firebasestorage.googleapis.com/v0/b/yogi-350dc.appspot.com/o/" + blob.getName() + "?alt=media";


        return url;
    }
}
