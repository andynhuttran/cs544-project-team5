package edu.cs544.team5.service;

import edu.cs544.team5.domain.Role;
import edu.cs544.team5.domain.RoleType;
import jdk.nashorn.internal.runtime.options.Option;

import java.util.Optional;

public interface RoleService {


    public Role fetchOrInsert(RoleType type);
}
