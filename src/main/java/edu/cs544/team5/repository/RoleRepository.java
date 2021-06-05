package edu.cs544.team5.repository;

import edu.cs544.team5.domain.Role;
import edu.cs544.team5.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r from Role r where r.type = :type")
    public Optional<Role> findRolesByType(RoleType type);
}
