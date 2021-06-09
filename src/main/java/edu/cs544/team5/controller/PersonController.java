package edu.cs544.team5.controller;

import edu.cs544.team5.domain.Person;
import edu.cs544.team5.dto.PersonCreationDto;
import edu.cs544.team5.dto.PersonReadDto;
import edu.cs544.team5.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonReadDto> create(@Valid @RequestBody PersonCreationDto p) {
        PersonReadDto created = personService.create(p);
        return ResponseEntity.created(URI.create("")).body(created);
    }

    @GetMapping
    public Page<Person> fetchAll(Pageable pageable) {
        return personService.fetchAll(pageable);
    }

}
