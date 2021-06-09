package edu.cs544.team5.util;

import edu.cs544.team5.domain.Person;
import edu.cs544.team5.domain.Role;
import edu.cs544.team5.repository.PersonRepository;
import edu.cs544.team5.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static edu.cs544.team5.domain.RoleType.*;

@RequiredArgsConstructor
@Component
public class Igniter implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        List<Role> roles = Arrays.asList(new Role(ADMIN), new Role(STUDENT), new Role(FACULTY), new Role(PERSONNEL));

        Person admin = new Person();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setUsername("admin");
        admin.setActive(true);
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        personRepository.save(admin);
    }
}
