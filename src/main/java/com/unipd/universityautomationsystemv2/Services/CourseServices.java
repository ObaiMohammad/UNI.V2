package com.unipd.universityautomationsystemv2.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.unipd.universityautomationsystemv2.Exceptions.EntityNotFoundException;
import com.unipd.universityautomationsystemv2.Repositories.CourseRepository;
import com.unipd.universityautomationsystemv2.model.Course;
import com.unipd.universityautomationsystemv2.model.CourseModel;
import com.unipd.universityautomationsystemv2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseServices {

    private final CourseRepository repository;

    @Autowired
    public CourseServices(CourseRepository repository) {
        this.repository = repository;
    }

    public Course create (CourseModel course){
        var newCourse = Course.builder()
                .title(course.getTitle())
                .credits(course.getCredits())
                .build();
        return repository.save(newCourse);
    }

    public Course findById(long id) {
        Course course =  repository.findById(id).orElseThrow(() -> new EntityNotFoundException("course not exist with id :" + id));
        return course;
    }

    public List<Course> findAll(){
        return repository.findAll().stream().toList();
    }

    public Course patchOne(long id, JsonPatch patch) {
        Course course = findById(id);
        Course patchedCourse = patchCourse(course,patch);
        return repository.save(patchedCourse);
    }

    private Course patchCourse(Course course, JsonPatch patch) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode courseJson = objectMapper.convertValue(course, JsonNode.class);
        try {
            JsonNode patched = patch.apply(courseJson);
            course = objectMapper.treeToValue(patched,Course.class);
            return course;
        } catch (JsonPatchException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(long id) {
        repository.deleteById(id);
        return true ;
    }

    public boolean deleteAll() {
        repository.deleteAll();
        return true;
    }

    public Set<User> getEnrolledStudents (Long courseId) {
        Course course = findById(courseId);
        return course.getUsersMap();
    }


}
