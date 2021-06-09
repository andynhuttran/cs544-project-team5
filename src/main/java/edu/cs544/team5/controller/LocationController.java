package edu.cs544.team5.controller;

import edu.cs544.team5.domain.Location;
import edu.cs544.team5.dto.LocationCreationDto;
import edu.cs544.team5.dto.LocationReadDto;
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
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private AbstractService<Location> locationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public List<LocationReadDto> findAll() {
        List<Location> LocationList = locationService.findAll();
        return LocationList.stream()
                .map(Location -> modelMapper.map(Location, LocationReadDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationReadDto> find(@PathVariable("id") int id) {
        Location Location = locationService.findById(id);
        if (Location == null) {
            return ResponseEntity.notFound().build();
        } else {
            LocationReadDto readDto = modelMapper.map(Location, LocationReadDto.class);
            return ResponseEntity.ok(readDto);
        }
    }

    @PostMapping("/")
    public ResponseEntity<LocationReadDto> create(@Valid  @RequestBody LocationCreationDto newLocationDto) {
        Location newLocation = modelMapper.map(newLocationDto, Location.class);
        Location Location = locationService.create(newLocation);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(Location.getId())
                .toUri();

        return ResponseEntity.created(uri).body(modelMapper.map(Location, LocationReadDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationReadDto> update(@Valid @RequestBody Location Location, @PathVariable int id) {
        Location.setId(id);
        Location updatedLocation = locationService.update(Location);

        if (updatedLocation == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(modelMapper.map(updatedLocation, LocationReadDto.class));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
