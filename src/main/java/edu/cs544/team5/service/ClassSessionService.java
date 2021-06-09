package edu.cs544.team5.service;

import edu.cs544.team5.dto.ClassSessionReadDto;

public interface ClassSessionService {
    ClassSessionReadDto findById(Integer id);
}
