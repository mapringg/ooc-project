package com.mapringg.bab.repositories;

import com.mapringg.bab.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);

    boolean existsByEmail(String email);
}
