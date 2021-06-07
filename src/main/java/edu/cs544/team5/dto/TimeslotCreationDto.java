package edu.cs544.team5.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
public class TimeslotCreationDto {

    @NotNull
    private String timeslotId;
    @NotNull
    private String title;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
}
