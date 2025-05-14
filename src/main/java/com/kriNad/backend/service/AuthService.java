package com.kriNad.backend.service;

import com.kriNad.backend.DTO.RegisterRequest;
import com.kriNad.backend.model.personne.Customer;
import com.kriNad.backend.model.personne.Personne;
import com.kriNad.backend.repositories.AdminRepository;
import com.kriNad.backend.repositories.AgentRepository;
import com.kriNad.backend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
// kristina
@Service
public class AuthService {

    @Autowired private CustomerRepository customerRepo;
    @Autowired private AgentRepository agentRepo;
    @Autowired private AdminRepository adminRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerCustomer(RegisterRequest request) {
        if (emailExists(request.getEmail())) {
            return "Email already exists";
        }

        Customer customer = new Customer();
        customer.setFname(request.getFname());
        customer.setLname(request.getLname());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setAgentAcces(false);
        customer.setAdminAcces(false);

        customerRepo.save(customer);
        return "success";
    }

    public boolean emailExists(String email) {
        return adminRepo.findByEmail(email).isPresent()
                || agentRepo.findByEmail(email).isPresent()
                || customerRepo.findByEmail(email).isPresent();
    }

    public Personne getUserByEmail(String email) {
        return adminRepo.findByEmail(email)
                .map(u -> (Personne) u)
                .or(() -> agentRepo.findByEmail(email)
                        .map(u -> (Personne) u))
                .or(() -> customerRepo.findByEmail(email)
                        .map(u -> (Personne) u))
                .orElse(null);
    }

    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    public String getRole(Personne p) {
        if (Boolean.TRUE.equals(p.getAdminAcces())) return "admin";
        if (Boolean.TRUE.equals(p.getAgentAcces())) return "agent";
        return "customer";
    }
}
