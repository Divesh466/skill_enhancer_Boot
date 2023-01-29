package com.learning.rest;

import com.learning.model.TrainerModel;
import com.learning.service.Impl.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trainer")
public class TrainerController {

    public final TrainerService trainerService;

    @GetMapping
    public List<TrainerModel> getAllRecords() {
        return trainerService.getAllRecords();
    }

    @PostMapping
    public TrainerModel save(@RequestBody TrainerModel trainermodel) {
        return trainerService.saveRecord(trainermodel);
    }

    @GetMapping("/{id}")
    public TrainerModel getRecordById(Long id) {
        return trainerService.getRecordById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteRecordById(@PathVariable Long id) {
        trainerService.deleteRecordById(id);
    }

    @PutMapping
    public TrainerModel updateRecordById(@PathVariable Long id, @RequestBody TrainerModel trainermodel) {
        return trainerService.updatedRecordById(id, trainermodel);
    }
}

