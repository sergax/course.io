package com.sergax.courseio.model.course;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    @DocumentId
    private String id;
    private String name;
    private String text;
    private String movie_url;
    private TypeContent type;
    private String courseId;
}
