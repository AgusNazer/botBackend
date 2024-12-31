// package com.agusdev.bottrading.controllers;

// import com.agusdev.bottrading.entity.UserEntity;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/users")
// public class UserController {

//     @Autowired
//     private UserService userService;

//     // Obtener todos los usuarios
//     @GetMapping
//     public List<UserEntity> getAllUsers() {
//         return userService.getAllUsers();
//     }

//     // Obtener un usuario por ID
//     @GetMapping("/{id}")
//     public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
//         Optional<UserEntity> user = userService.getUserById(id);
//         return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//     }

//     // Crear un nuevo usuario
//     @PostMapping
//     public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
//         UserEntity createdUser = userService.createUser(user);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//     }

//     // Actualizar un usuario existente
//     @PutMapping("/{id}")
//     public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userDetails) {
//         UserEntity updatedUser = userService.updateUser(id, userDetails);
//         return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
//     }

//     // Eliminar un usuario
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//         return userService.deleteUser(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
//     }
// }

package com.agusdev.bottrading.controllers;

import com.agusdev.bottrading.entity.UserEntity;
import com.agusdev.bottrading.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    // private final PasswordEncoder passwordEncoder; 

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role) {
        try {
            UserEntity user = userService.registerUser(username, email, password, role);
            return ResponseEntity.ok("User registered successfully! ID: " + user.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error registering user: " + e.getMessage());
        }
    }
    // @PostMapping("/login")
    // public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
    //     try {
    //         UserEntity user = userService.findUserByUsername(username);
            
    //         if (user != null && passwordEncoder.matches(password, user.getPassword())) {  // Usa matches()
    //             return ResponseEntity.ok("Login successful!");
    //         } else {
    //             return ResponseEntity.status(401).body("Invalid username or password");
    //         }
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("Error logging in: " + e.getMessage());
    //     }
    // }
}
