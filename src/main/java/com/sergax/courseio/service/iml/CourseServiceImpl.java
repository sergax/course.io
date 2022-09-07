package com.sergax.courseio.service.iml;

import com.google.firebase.cloud.FirestoreClient;
import com.sergax.courseio.model.course.Course;
import com.sergax.courseio.service.CourseService;
import com.sergax.courseio.service.exceptions.EntityNotFoundException;
import com.sergax.courseio.service.exceptions.NotUniqueDataException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;

@Service
public class CourseServiceImpl implements CourseService {
    private static final String COLLECTION_COURSES = "courses";

    @SneakyThrows
    @Override
    public Course getById(String id) {
        var documentSnapshot =
                FirestoreClient.getFirestore()
                        .collection(COLLECTION_COURSES)
                        .document(id)
                        .get().get();
        if (documentSnapshot.exists()) {
            return documentSnapshot.toObject(Course.class);
        } else {
            throw new EntityNotFoundException(
                    format("Document not found for course id: %s", id));
        }
    }

    @SneakyThrows
    @Override
    public Set<Course> getAll() {
        var documentRef =
                FirestoreClient.getFirestore()
                        .collection(COLLECTION_COURSES)
                        .listDocuments()
                        .iterator();
        var courses = new HashSet<Course>();

        while (documentRef.hasNext()) {
            var documentSnapshot = documentRef.next().get().get();
            var course = documentSnapshot.toObject(Course.class);
            if (course != null) {
                course.setId(documentSnapshot.getId());
            }
            courses.add(course);
        }
        return courses;
    }

    @Override
    public Course create(Course course) {
        if (isCourseExistsByName(course.getTitle())) {
            throw new NotUniqueDataException(
                    format("Course by title: %s already present", course.getTitle()));
        }
        Map<String, Object> docCourse = new HashMap<>();
        docCourse.put("title", course.getTitle());
        docCourse.put("description", course.getDescription());
        docCourse.put("logoUrl", course.getLogoUrl());
        docCourse.put("startDate", course.getStartDate());
        docCourse.put("endDate", course.getEndDate());
        docCourse.put("contents", course.getContents());
        docCourse.put("mentorIds", course.getMentorIds());

        FirestoreClient.getFirestore().collection(COLLECTION_COURSES)
                .document()
                .create(docCourse);
        return course;
    }

    @Override
    public Course update(String id, Course course) {
        FirestoreClient.getFirestore().collection(COLLECTION_COURSES)
                .document(id)
                .set(course);
        return course;
    }

    private boolean isCourseExistsByName(String title) {
        return getAll().stream().anyMatch(course -> course.getTitle().equals(title));
    }
}
