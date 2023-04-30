package com.unipd.universityautomationsystemv2.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserModel {

    private  String firstName;
    private  String lastName;
    private  String email;
    private Role role;


}
