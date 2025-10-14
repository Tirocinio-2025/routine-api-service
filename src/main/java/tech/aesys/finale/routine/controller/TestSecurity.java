package tech.aesys.finale.routine.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test-security")
public class TestSecurity {


    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userEndpoint() {
        return "User content";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/reports")
    public String adminReports() {
        return "Admin reports";
    }

}
