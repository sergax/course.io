package com.sergax.courseio.model.course;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @DocumentId
    private String id;
    private String title;
    private String description;
    private String logoUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> studentIds;
    private List<String> mentorIds;
    private List<Content> contents;
}

