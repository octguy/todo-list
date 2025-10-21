package com.example.todobe.service.implementation;

import com.example.todobe.model.Role;
import com.example.todobe.repository.RoleRepository;
import com.example.todobe.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void createNewRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        roleRepository.save(role);
    }

}
