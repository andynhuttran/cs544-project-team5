package edu.cs544.team5.controller;

import edu.cs544.team5.domain.Timeslot;
import edu.cs544.team5.dto.TimeslotCreationDto;
import edu.cs544.team5.dto.TimeslotReadDto;
import edu.cs544.team5.service.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@ResponseBody
@RequestMapping("/timeslot")
public class TimeSlotController {

    @Autowired
    private AbstractService<Timeslot> timeslotService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public List<TimeslotReadDto> findAll() {
        List<Timeslot> TimeslotList = timeslotService.findAll();
        return TimeslotList.stream()
                .map(Timeslot -> modelMapper.map(Timeslot, TimeslotReadDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeslotReadDto> find(@PathVariable("id") int id) {
        Timeslot Timeslot = timeslotService.findById(id);
        if (Timeslot == null) {
            return ResponseEntity.notFound().build();
        } else {
            TimeslotReadDto readDto = modelMapper.map(Timeslot, TimeslotReadDto.class);
            return ResponseEntity.ok(readDto);
        }
    }

    @PostMapping("/")
    public ResponseEntity<TimeslotReadDto> create(@Valid @RequestBody TimeslotCreationDto newTimeslotDto) {

        if(newTimeslotDto.getEndTime().compareTo(newTimeslotDto.getStartTime()) <= 0)
            return ResponseEntity.badRequest().build();

        Timeslot newTimeslot = modelMapper.map(newTimeslotDto, Timeslot.class);

        Timeslot Timeslot = timeslotService.create(newTimeslot);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(Timeslot.getId())
                .toUri();

        return ResponseEntity.created(uri).body(modelMapper.map(Timeslot, TimeslotReadDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeslotReadDto> update(@Valid @RequestBody Timeslot timeslot, @PathVariable int id) {
        timeslot.setId(id);
        if(timeslot.getEndTime().compareTo(timeslot.getStartTime()) <= 0)
            return ResponseEntity.badRequest().build();
        Timeslot updatedTimeslot = timeslotService.update(timeslot);

        if (updatedTimeslot == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(modelMapper.map(updatedTimeslot, TimeslotReadDto.class));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        timeslotService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
