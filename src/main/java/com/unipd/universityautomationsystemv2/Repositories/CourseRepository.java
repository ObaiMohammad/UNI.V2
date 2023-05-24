package com.unipd.universityautomationsystemv2.Repositories;

import com.fasterxml.jackson.databind.JsonNode;
import com.unipd.universityautomationsystemv2.model.Course;
import com.unipd.universityautomationsystemv2.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "select* from get_enrolled_courses(:id);", nativeQuery = true)
    ResultSet getStudents(@Param("id") int courseId);
  

}

