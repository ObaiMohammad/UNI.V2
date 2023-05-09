package com.unipd.universityautomationsystemv2.Repositories;

import com.unipd.universityautomationsystemv2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);

    @Modifying
    @Query(value = "insert into course_student values (course_id,student_id) " +
            "values (:course_id,:student_id)", nativeQuery = true)
    void enrollStudent (Long courseId, Long studentId);

}
