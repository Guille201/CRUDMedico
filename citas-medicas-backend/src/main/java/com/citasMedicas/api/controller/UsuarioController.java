package com.citasMedicas.api.controller;

import com.citasMedicas.api.models.User;
import com.citasMedicas.api.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    private final UserRepository userRepository;

    public UsuarioController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsuarios() {
        return userRepository.findAll();
    }

    @PostMapping
    public User crearUsuario(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User actualizarUsuario(@PathVariable Long id, @RequestBody User userActualizado) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userActualizado.getUsername());
                    user.setEmail(userActualizado.getEmail());
                    user.setDni(userActualizado.getDni());
                    user.setBirthYear(userActualizado.getBirthYear());
                    user.setPhone(userActualizado.getPhone());
                    user.setAddress(userActualizado.getAddress());
                    user.setRole(userActualizado.getRole());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}