package edu.cs544.team5.service;

import edu.cs544.team5.domain.Role;
import edu.cs544.team5.domain.RoleType;
import edu.cs544.team5.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role fetchOrInsert(RoleType type) {

        Optional<Role> optionalRole = roleRepository.findRolesByType(type);
        if (optionalRole.isPresent()){
            return optionalRole.get();
        }

        Role role = new Role();
        role.setType(type);
        roleRepository.save(role);
        return role;
    }
}
