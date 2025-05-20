package com.kriNad.backend.controller;

import com.kriNad.backend.DTO.LoginRequest;
import com.kriNad.backend.DTO.RegisterRequest;
import com.kriNad.backend.model.personne.Personne;
import com.kriNad.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


//Kristina
@RestController
@RequestMapping("api/Auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest request) {
        String result = authService.registerCustomer(request);
        if ("success".equals(result)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "success");
            response.put("role", "customer");
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("error", result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request) {
        Personne user = authService.getUserByEmail(request.getEmail());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        if (!authService.checkPassword(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("idUser", user.getId());
        response.put("fname", user.getFname());
        response.put("lname", user.getLname());
        response.put("email", user.getEmail());
        response.put("phone", user.getPhone());
        response.put("role", authService.getRole(user));

        return ResponseEntity.ok(response);
    }
}
