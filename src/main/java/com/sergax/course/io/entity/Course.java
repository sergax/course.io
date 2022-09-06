package com.sergax.course.io.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String title;
    private Set<User> students;
    private Set<User> mentors;
}

