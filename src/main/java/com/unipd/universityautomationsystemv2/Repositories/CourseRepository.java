package com.unipd.universityautomationsystemv2.Repositories;

import com.unipd.universityautomationsystemv2.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {}
