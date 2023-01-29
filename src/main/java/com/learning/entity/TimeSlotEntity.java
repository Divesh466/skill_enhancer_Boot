package com.learning.entity;

import java.time.LocalTime;
import java.util.Objects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="timeSlot")
public class TimeSlotEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private LocalTime startTime;
	private LocalTime endTime;
	private Long trainerId;
}
