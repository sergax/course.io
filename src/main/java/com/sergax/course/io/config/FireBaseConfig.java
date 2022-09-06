package com.sergax.course.io.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;

@Configuration
public class FireBaseConfig {
    @SneakyThrows
    @Bean
    public void initialization() {
        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("/home/user/Documents/fire_base/course-daa40-firebase-adminsdk-p3f57-82c6368b79.json");
            FirebaseOptions options = null;
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
