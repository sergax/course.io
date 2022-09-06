package com.sergax.course.io.service.iml;

import com.google.firebase.cloud.FirestoreClient;
import com.sergax.course.io.entity.User;
import com.sergax.course.io.service.UserService;
import com.sergax.course.io.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceIml implements UserService {
    private static final String COLLECTION_NAME = "users";

    @SneakyThrows
    @Override
    public String create(User user) {
        var firestore = FirestoreClient.getFirestore();
        return firestore.collection(COLLECTION_NAME)
                .document(user.getName())
                .set(user)
                .get()
                .getUpdateTime()
                .toString();
    }

    @SneakyThrows
    @Override
    public User getByName(String name) {
        var firestore = FirestoreClient.getFirestore();
        var documentSnapshot = firestore.collection(COLLECTION_NAME).document(name).get().get();

        if (documentSnapshot.exists()) {
            return documentSnapshot.toObject(User.class);
        } else {
            throw new EntityNotFoundException("Document not found");
        }
    }

    @SneakyThrows
    @Override
    public String update(User user) {
        var firestore = FirestoreClient.getFirestore();
        return firestore.collection(COLLECTION_NAME)
                .document(user.getName())
                .set(user)
                .get()
                .getUpdateTime()
                .toString();
    }

    @SneakyThrows
    @Override
    public Set<User> getAll()  {
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
