package edu.cs544.team5.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeslotReadDto {

    private Integer id;
    private String timeslotId;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
}
