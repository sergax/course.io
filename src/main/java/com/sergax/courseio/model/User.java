package com.sergax.courseio.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @DocumentId
    private String id;
    private String name;
    private String email;
    private String password;
    private String roleId;
    private List<String> courseIds;
}
