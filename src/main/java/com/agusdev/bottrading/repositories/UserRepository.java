// package com.agusdev.bottrading.repositories;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import com.agusdev.bottrading.entity.UserEntity;

// public interface UserRepository extends JpaRepository<UserEntity, Long> {
//     //  métodos ejemplo
//     Optional<UserEntity> findByUsername(String username);
// }

package com.agusdev.bottrading.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agusdev.bottrading.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
