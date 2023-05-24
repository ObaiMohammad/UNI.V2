package com.unipd.universityautomationsystemv2.Controllers;

import com.github.fge.jsonpatch.JsonPatch;
import com.unipd.universityautomationsystemv2.Services.UserServices;
import com.unipd.universityautomationsystemv2.model.Course;
import com.unipd.universityautomationsystemv2.model.StudentRequest;
import com.unipd.universityautomationsystemv2.model.User;
import com.unipd.universityautomationsystemv2.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v2/users")
public class UserController {

    private final UserServices service;

    @Autowired
    public UserController(UserServices service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody UserModel user) {
        return service.create(user);
    }

    @PostMapping("/multi")
    public User[] addMultiableUser(@RequestBody UserModel[] userModel) {
        User[] users = new User[userModel.length];
//        for (UserModel u : user)
//        {
//          users.add(service.create(u))  ;
//        }
        for (int i = 0; i < users.length; i++) {
            users[i] = (service.create(userModel[i]));
        }
        return users;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserModel user) {
        ;
        return ResponseEntity.ok(service.updateOne(id, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody JsonPatch patch) {
        return ResponseEntity.ok(service.patchOne(id, patch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllUsers() {
        service.deleteAll();
        return ResponseEntity.ok("Deleted successfully");
    }

    @PostMapping("/{studentId}/enroll")
    public ResponseEntity<String> enrolleStudent(@PathVariable Long studentId, @RequestBody StudentRequest request) {

           service.enrollStudent(request.getCourseId(), studentId);

        return ResponseEntity.ok("Student is enrolled in course: " + request.getCourseId());
    }

    @PostMapping("/{studentId}/enroll/light")
    public ResponseEntity<String> enrolleStudentLight(@PathVariable Long studentId, @RequestBody StudentRequest request) {

        service.enrollStudentLight(request.getCourseId(), studentId);

        return ResponseEntity.ok("Student is enrolled in course: " + request.getCourseId());
    }

    @GetMapping("{studentId}/courses")
    public Set<Course> getEnrolledCourses(@PathVariable Long studentId) {
        return service.getEnrolledCourses(studentId);
    }

    @DeleteMapping("/{studentId}/unroll")
    public  ResponseEntity<String> unrollCourse (@PathVariable Long studentId, @RequestBody StudentRequest request){
        service.unrollCourse(studentId,request.getCourseId());
         return ResponseEntity.noContent().build();
    }


}
