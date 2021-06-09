package edu.cs544.team5.controller;

import edu.cs544.team5.domain.AcademicBlock;
import edu.cs544.team5.dto.AcademicBlockCreationDto;
import edu.cs544.team5.dto.AcademicBlockReadDto;
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
@RequestMapping("/block")
public class AcademicBlockController {

    @Autowired
    private AbstractService<AcademicBlock> academicBlockService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public List<AcademicBlockReadDto> findAll() {
        List<AcademicBlock> AcademicBlockList = academicBlockService.findAll();
        return AcademicBlockList.stream()
                .map(AcademicBlock -> modelMapper.map(AcademicBlock, AcademicBlockReadDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicBlockReadDto> find(@PathVariable("id") int id) {
        AcademicBlock AcademicBlock = academicBlockService.findById(id);
        if (AcademicBlock == null) {
            return ResponseEntity.notFound().build();
        } else {
            AcademicBlockReadDto readDto = modelMapper.map(AcademicBlock, AcademicBlockReadDto.class);
            return ResponseEntity.ok(readDto);
        }
    }

    @PostMapping("/")
    public ResponseEntity<AcademicBlockReadDto> create(@Valid @RequestBody AcademicBlockCreationDto newAcademicBlockDto) {
        AcademicBlock newAcademicBlock = modelMapper.map(newAcademicBlockDto, AcademicBlock.class);
        AcademicBlock AcademicBlock = academicBlockService.create(newAcademicBlock);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(AcademicBlock.getId())
                .toUri();

        return ResponseEntity.created(uri).body(modelMapper.map(AcademicBlock, AcademicBlockReadDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademicBlockReadDto> update(@Valid @RequestBody AcademicBlockCreationDto AcademicBlock, @PathVariable int id) {
        AcademicBlock updatedAcademicBlock = modelMapper.map(AcademicBlock, AcademicBlock.class);
        updatedAcademicBlock.setId(id);
        AcademicBlock responseAcademicBlock = academicBlockService.update(updatedAcademicBlock);

        if (responseAcademicBlock == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(modelMapper.map(responseAcademicBlock, AcademicBlockReadDto.class));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        academicBlockService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
