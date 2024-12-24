package com.agusdev.bottrading.services;

import com.agusdev.bottrading.entity.UserEntity;
import com.agusdev.bottrading.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Obtener todos los usuarios
    public List<UserEntity> getAllUsers() {
    return userRepository.findAll();
    }

    // Obtener un usuario por ID
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Crear un nuevo usuario
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    // Actualizar un usuario existente
    public UserEntity updateUser(Long id, UserEntity userDetails) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword()); // Asegúrate de que la contraseña esté cifrada antes de guardarla
            return userRepository.save(existingUser);
        }
        return null; // Si no se encuentra el usuario, retorna null
    }

    // Eliminar un usuario
    public boolean deleteUser(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
