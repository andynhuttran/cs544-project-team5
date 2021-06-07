package edu.cs544.team5.service;

import edu.cs544.team5.domain.Role;
import edu.cs544.team5.domain.RoleType;
import org.springframework.transaction.annotation.Transactional;


public interface RoleService {


    public Role fetchOrInsert(RoleType type);
}
