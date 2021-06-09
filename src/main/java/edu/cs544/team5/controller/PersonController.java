package edu.cs544.team5.controller;

import edu.cs544.team5.domain.Person;
import edu.cs544.team5.dto.PersonCreationDto;
import edu.cs544.team5.dto.PersonReadDto;
import edu.cs544.team5.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PersonReadDto> create(@Valid @RequestBody PersonCreationDto p) {
        PersonReadDto created = personService.create(p);
        return ResponseEntity.created(URI.create("")).body(created);
    }

    @PutMapping
    public ResponseEntity<PersonReadDto> update(@Valid @RequestBody PersonCreationDto p) {
        PersonReadDto created = personService.update(p);
        return ResponseEntity.accepted().body(created);
    }

    @GetMapping
    public Page<Person> fetchAll(Pageable pageable) {
        return personService.fetchAll(pageable);
    }

    @PostMapping("/passw")
    public ResponseEntity<PersonReadDto> updatePass(@RequestBody PersonCreationDto userReq) {
        personService.updateUserPass(userReq);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<PersonReadDto> get(@PathVariable String username) {
        Person person = personService.findByUsername(username);
        return ResponseEntity.ok(modelMapper.map(person, PersonReadDto.class));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> delete(@PathVariable String username) {
        personService.removeByUsername(username);
        return ResponseEntity.noContent().build();
    }
}
