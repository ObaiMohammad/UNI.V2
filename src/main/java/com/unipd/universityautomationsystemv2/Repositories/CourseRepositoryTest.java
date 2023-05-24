package com.unipd.universityautomationsystemv2.Repositories;

import com.unipd.universityautomationsystemv2.Exceptions.EntityNotFoundException;
import com.unipd.universityautomationsystemv2.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository

public class CourseRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    public List<Course> findAll(){
        TypedQuery<Course> q = entityManager.createQuery("select * from courses", Course.class);
        return q.getResultList();

    }

    public Course findById (Long id){
        Course course = entityManager.find(Course.class,id);
        if (course != null){
            return course;
        }
        else throw new EntityNotFoundException("course dose not exist with id :" + id);
    }

    public Course save (Course course){
        entityManager.persist(Course.class);
        return course;
    }

    public Course deleteById (Long id){
        Course course = findById(id);
        entityManager.remove( course);
        return course;
    }
    public int deleteAll (){
        Query query = entityManager.createQuery("DELETE FROM courses");
        return query.executeUpdate();

    }

    public void getStudents (Long courseId){
        Query q = entityManager.createQuery("select get_students (:course_id)");
        q.setParameter("course_id",courseId );
        q.
    }
}
