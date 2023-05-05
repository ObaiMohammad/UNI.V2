package com.unipd.universityautomationsystemv2.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CourseModel {

    private  String title;
    private  int credits;

}
