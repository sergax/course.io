package com.sergax.courseio.service.iml;

import com.google.firebase.cloud.FirestoreClient;
import com.sergax.courseio.entity.Role;
import com.sergax.courseio.service.RoleService;
import com.sergax.courseio.service.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class RolesServiceIml implements RoleService {
    private static final String COLLECTION_ROLES = "roles";

    @SneakyThrows
    @Override
    public Role create(Role role) {
        Map<String, Object> docRole = new HashMap<>();
        docRole.put("name", role.getName());

        FirestoreClient.getFirestore().collection(COLLECTION_ROLES)
                .document()
                .create(docRole);
        return role;
    }

    @SneakyThrows
    @Override
    public Role getById(String id) {
        var firestore = FirestoreClient.getFirestore();
        var documentSnapshot = firestore.collection(COLLECTION_ROLES).document(id).get().get();

        if (documentSnapshot.exists()) {
            return documentSnapshot.toObject(Role.class);
        } else {
            throw new EntityNotFoundException(
                    format("Document not found by role ID: %s", id));
        }
    }

    @SneakyThrows
    @Override
    public Role update(String id, Role role) {
        FirestoreClient.getFirestore().collection(COLLECTION_ROLES)
                .document(id)
                .set(role);
        return role;
    }

    @SneakyThrows
    @Override
    public Set<Role> getAll() {
        var firestore = FirestoreClient.getFirestore();
        var documentRef = firestore.collection(COLLECTION_ROLES).listDocuments().iterator();
        var roles = new HashSet<Role>();

        while (documentRef.hasNext()) {
            var documentSnapshot = documentRef.next().get().get();
            var role = documentSnapshot.toObject(Role.class);
            role.setId(documentSnapshot.getId());
            roles.add(role);
        }
        return roles;
    }

}
