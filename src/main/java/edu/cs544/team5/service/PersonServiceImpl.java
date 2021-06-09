package edu.cs544.team5.service;

import edu.cs544.team5.domain.Person;
import edu.cs544.team5.domain.Role;
import edu.cs544.team5.dto.PersonCreationDto;
import edu.cs544.team5.dto.PersonReadDto;
import edu.cs544.team5.exception.NoSuchRecordFoundException;
import edu.cs544.team5.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username).orElseThrow(() -> new NoSuchRecordFoundException("Username not found"));
    }


    @Override
    public PersonReadDto update(PersonCreationDto p) {
        /**
         * @Todo
         * update not create
         */
        Optional<Person> byUsername = personRepository.findByUsername(p.getUsername());
        if (!byUsername.isPresent()) {
            throw new NoSuchRecordFoundException("Not registered user. Please use different username.");
        }
        Person userModel = byUsername.get();
        userModel.setPassword(passwordEncoder.encode(p.getPassword()));
        userModel.getRoles().forEach(r -> userModel.removeRole(r));
        userModel.setFirstName(p.getFirstName());
        userModel.setLastName(p.getLastName());
        p.getRoles().forEach(
                r -> {
                    Role role = this.roleService.fetchOrInsert(r.getType());
                    userModel.addRole(role);
                }
        );
        Person person = personRepository.save(userModel);
        return modelMapper.map(person, PersonReadDto.class);
    }

    @Override
    public PersonReadDto create(PersonCreationDto p) {
        Optional<Person> byUsername = personRepository.findByUsername(p.getUsername());
        if (byUsername.isPresent()) {
            throw new NoSuchRecordFoundException("User already registered. Please use different username.");
        }
        return getPersonReadWriteDto(p);
    }

    private PersonReadDto getPersonReadWriteDto(PersonCreationDto p) {
        Person userModel = modelMapper.map(p, Person.class);
        userModel.setPassword(passwordEncoder.encode(p.getPassword()));
        p.getRoles().forEach(
                r -> {
                    Role role = this.roleService.fetchOrInsert(r.getType());
                    userModel.addRole(role);
                }
        );
        Person person = personRepository.save(userModel);
        return modelMapper.map(person, PersonReadDto.class);
    }

    public void updateUserPass(PersonCreationDto userReq) {
        Optional<Person> user = personRepository.findByUsername(userReq.getUsername());
        Person updated = user.map(p -> {
            p.setPassword(passwordEncoder.encode(userReq.getPassword()));
            return p;
        }).orElseThrow(NoSuchRecordFoundException::new);
        personRepository.save(updated);
    }

    @Override
    public void removeByUsername(String username) {
/**
 * @Todo
 * remove cascade by delete
 */

        Person person = findByUsername(username);
        personRepository.delete(person);
    }

    @Override
    public Page<Person> fetchAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }
}
