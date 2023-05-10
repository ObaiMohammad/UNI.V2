package com.unipd.universityautomationsystemv2.Repositories;

import com.unipd.universityautomationsystemv2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "insert into course_student(course_id, student_id) values (:course_id,:student_id)", nativeQuery = true)
    void enrollStudent (@Param("course_id")  Long courseId, @Param("student_id")Long studentId);

}
