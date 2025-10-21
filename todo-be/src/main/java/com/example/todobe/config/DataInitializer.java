package com.example.todobe.config;

import com.example.todobe.service.IRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final IRoleService roleService;

    public DataInitializer(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Initializing data...");
        initializeRoles();
    }

    private void initializeRoles() {
        System.out.println("Current roles in the system:");
        if (roleService.findAll().isEmpty()) {
            roleService.createNewRole("USER");
            roleService.createNewRole("ADMIN");
        } else {
            System.out.println("Current roles in the system:");
        }
    }
}
