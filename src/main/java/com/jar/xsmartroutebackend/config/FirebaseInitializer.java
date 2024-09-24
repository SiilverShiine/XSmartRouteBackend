package com.jar.xsmartroutebackend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitializer {
    @PostConstruct
    public void initialize() throws IOException {
        try {
            FileInputStream refreshToken = new FileInputStream(
                    "src/main/resources/xsmartroute-adminskd.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build();
            FirebaseApp.initializeApp(options);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
