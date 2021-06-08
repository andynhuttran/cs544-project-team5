package edu.cs544.team5.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cs544.team5.dto.BarcodeRecordCreationDto;
import edu.cs544.team5.dto.ClassSessionReadDto;
import edu.cs544.team5.dto.StudentReadDto;
import edu.cs544.team5.repository.ClassSessionRepository;
import edu.cs544.team5.repository.StudentRepository;
import edu.cs544.team5.service.BarcodeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BarcodeController.class)
class BarcodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BarcodeService barcodeService;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private ClassSessionRepository classSessionRepository;

    @Test
    @DisplayName("Load Context")
    void contextLoad() {
        assertAll(
                () -> assertNotNull(barcodeService),
                () -> assertNotNull(studentRepository),
                () -> assertNotNull(classSessionRepository)
        );
    }

    @Test
    @DisplayName("Should create a barcode record for student with a given class session")
    void shouldCreateABarcodeRecordForStudentWithAGivenClassSession() throws Exception {
        BarcodeRecordCreationDto recordDTO = new BarcodeRecordCreationDto();
        recordDTO.setAttendance(LocalDateTime.MAX);
        recordDTO.setStudentReadDto(new StudentReadDto());
        recordDTO.setClassSessionReadDto(new ClassSessionReadDto());
        String jsonRecord = objectMapper.writeValueAsString(recordDTO);
        mockMvc.perform(post("/record").contentType(MediaType.APPLICATION_JSON).content(jsonRecord))
                .andExpect(status().isCreated());
    }
}
