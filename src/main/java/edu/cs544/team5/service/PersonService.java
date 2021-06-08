package edu.cs544.team5.service;

import edu.cs544.team5.domain.Person;

public interface PersonService {
    Person findByUsername(String username);
}
