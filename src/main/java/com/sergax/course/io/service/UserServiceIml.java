package com.sergax.course.io.service;

import com.google.firebase.cloud.FirestoreClient;
import com.sergax.course.io.entity.User;
import com.sergax.course.io.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserServiceIml {
    private static final String COLLECTION_NAME = "users";

    public String createUser(User user) throws ExecutionException, InterruptedException {
        var firestore = FirestoreClient.getFirestore();
        return firestore.collection(COLLECTION_NAME)
                .document(user.getName())
                .set(user)
                .get()
                .getUpdateTime()
                .toString();
    }

    public User getUser(String name) throws ExecutionException, InterruptedException {
        var firestore = FirestoreClient.getFirestore();
        var documentSnapshot = firestore.collection(COLLECTION_NAME).document(name).get().get();

        if (documentSnapshot.exists()) {
            return documentSnapshot.toObject(User.class);
        } else {
            throw new EntityNotFoundException("Document not found");
        }
    }

    public String updateUser(User user) throws ExecutionException, InterruptedException {
        var firestore = FirestoreClient.getFirestore();
        return firestore.collection(COLLECTION_NAME)
                .document(user.getName())
                .set(user)
                .get()
                .getUpdateTime()
                .toString();
    }

    public Set<User> getAllUsers() throws ExecutionException, InterruptedException {
        var firestore = FirestoreClient.getFirestore();
        var documentRef = firestore.collection(COLLECTION_NAME).listDocuments().iterator();
        var users = new HashSet<User>();

        while (documentRef.hasNext()) {
            var documentSnapshot = documentRef.next().get().get();
            var user = documentSnapshot.toObject(User.class);
            users.add(user);
        }
        return users;
    }

}
