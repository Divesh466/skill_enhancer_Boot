package com.learning.entity;

import com.learning.enums.BatchStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer studentCount;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(value = EnumType.STRING)
    private BatchStatus batchStatus;
    private Long courseId;
    private Long timeslotId;

}