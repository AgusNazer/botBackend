package com.agusdev.bottrading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.agusdev.bottrading.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Puedes agregar métodos personalizados si los necesitas
}
