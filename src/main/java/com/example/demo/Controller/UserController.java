package com.example.demo.Controller;
import com.example.demo.Entity.AppUser;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private String token;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AppUser user, @RequestParam String roleName) {
        // Проверяем, существует ли уже пользователь с таким именем
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        // Получаем роль из базы данных
        roleRepository.findByRoleName(roleName);
        return ResponseEntity.badRequest().body("Role not found!");

        // Присваиваем роль пользователю
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getUser(@PathVariable String username) {
        AppUser user = userRepository.findByUsername(username);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/hello")
    public String hello() {

        return "hello";
    }

    @GetMapping("/current")
    public ResponseEntity<AppUser> getCurrentUser(@RequestHeader("Authorization") String token) {
        this.token = token;
        // Получаем текущего аутентифицированного пользователя из контекста безопасности
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();  // Имя пользователя из контекста безопасности
        AppUser user = userRepository.findByUsername(username);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
}
