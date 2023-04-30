package com.unipd.universityautomationsystemv2.Repositories;

import com.unipd.universityautomationsystemv2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
