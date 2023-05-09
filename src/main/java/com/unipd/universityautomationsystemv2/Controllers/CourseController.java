package com.unipd.universityautomationsystemv2.Controllers;

import com.github.fge.jsonpatch.JsonPatch;
import com.unipd.universityautomationsystemv2.Services.CourseServices;
import com.unipd.universityautomationsystemv2.Services.UserServices;
import com.unipd.universityautomationsystemv2.model.Course;
import com.unipd.universityautomationsystemv2.model.CourseModel;
import com.unipd.universityautomationsystemv2.model.User;
import com.unipd.universityautomationsystemv2.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v2/courses")
public class CourseController {

    private final CourseServices service;

    @Autowired
    public CourseController(CourseServices service) {
        this.service = service;
    }

    @PostMapping
    public Course addCourse (@RequestBody CourseModel courseModel){
        return service.create(courseModel);
    }

    @PostMapping("/multi")
    public Course[] addMultiableUser(@RequestBody CourseModel[] courseModel) {
        Course [] courses = new Course[courseModel.length];

        for (int i =0; i < courses.length ;i++){
            courses [i] = (service.create(courseModel[i]));
        }
        return  courses ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById (@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public List<Course> getAllCourseBy (){
        return service.findAll();
    }

    @PatchMapping ("/{id}")
    public ResponseEntity<Course> updateCourse (@PathVariable long id, @RequestBody JsonPatch patch){
        return ResponseEntity.ok(service.patchOne(id,patch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllCourses(){
        service.deleteAll();
        return ResponseEntity.ok("Deleted successfully");
    }
    @GetMapping ("/{courseId}")
    public Set<User> getEnrolledCourses (@PathVariable long courseId){
        return service.getEnrolledStudents(courseId);
    }

}
