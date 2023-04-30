package com.unipd.universityautomationsystemv2.Controllers;

import com.unipd.universityautomationsystemv2.Services.UserServices;
import com.unipd.universityautomationsystemv2.model.User;
import com.unipd.universityautomationsystemv2.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/users")
public class UserController {

    private final UserServices service;

    @Autowired
    public UserController(UserServices service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return service.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody UserModel user) {
        return service.create(user);
    }

    @PostMapping("/multi")
    public User [] addMultiableUser(@RequestBody UserModel [] user) {
       User [] users = new User[user.length];
//        for (UserModel u : user)
//        {
//          users.add(service.create(u))  ;
//        }
        for (int i =1; i < users.length ;i++){
            users [i] = (service.create(user[0]));
        }
        return  users ;
    }



    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserModel user){
;
        return ResponseEntity.ok(service.updateOne(id,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllUsers(){
        service.deleteAll();
        return ResponseEntity.ok("Deleted successfully");
    }


}
