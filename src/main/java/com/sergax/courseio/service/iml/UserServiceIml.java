package com.sergax.courseio.service.iml;

import com.google.firebase.cloud.FirestoreClient;
import com.sergax.courseio.entity.Role;
import com.sergax.courseio.entity.User;
import com.sergax.courseio.service.RoleService;
import com.sergax.courseio.service.UserService;
import com.sergax.courseio.service.exceptions.EntityNotFoundException;
import com.sergax.courseio.service.exceptions.NotUniqueDataException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceIml implements UserService {
    private final RoleService roleService;
    private static final String COLLECTION_NAME = "users";

    @SneakyThrows
    @Override
    public User create(User user) {
        if (isUserExistsByEmail(user.getEmail())) {
            throw new NotUniqueDataException(
                    format("User by email: %s already present", user.getEmail()));
        }
        Map<String, Object> docUser = new HashMap<>();
        docUser.put("name", user.getName());
        docUser.put("email", user.getEmail());
        docUser.put("password", user.getPassword());
        docUser.put("roleId", user.getRoleId());
        docUser.put("courses", user.getCourseIds());

        FirestoreClient.getFirestore().collection(COLLECTION_NAME)
                .document()
                .create(docUser);
        return user;
    }

    @SneakyThrows
    @Override
    public User getById(String id) {
        var firestore = FirestoreClient.getFirestore();
        var documentSnapshot = firestore.collection(COLLECTION_NAME).document(id).get().get();

        if (documentSnapshot.exists()) {
            return documentSnapshot.toObject(User.class);
        } else {
            throw new EntityNotFoundException(
                    format("Document not found for user id: %s", id));
        }
    }

    @SneakyThrows
    @Override
    public User update(String id, User user) {
        FirestoreClient.getFirestore().collection(COLLECTION_NAME)
                .document(id)
                .set(user);

        Role role = roleService.getById(user.getRoleId());
        role.getUserIds().add(id);
        roleService.update(user.getRoleId(), role);
        return user;
    }

    @SneakyThrows
    @Override
    public Set<User> getAll() {
        var firestore = FirestoreClient.getFirestore();
        var documentRef = firestore.collection(COLLECTION_NAME).listDocuments().iterator();
        var users = new HashSet<User>();

        while (documentRef.hasNext()) {
            var documentSnapshot = documentRef.next().get().get();
            var user = documentSnapshot.toObject(User.class);
            user.setId(documentSnapshot.getId());
            users.add(user);
        }
        return users;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        return getAll().stream().anyMatch(user -> user.getEmail().equals(email));
    }
}
