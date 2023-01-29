package com.learning.rest;

import com.learning.model.BatchModel;
import com.learning.service.Impl.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    @GetMapping
    public List<BatchModel> getAllRecords() {
        return batchService.getAllRecords();
    }

    @PostMapping
    public BatchModel save(@RequestBody BatchModel batchModel) {
        return batchService.saveRecord(batchModel);
    }

    @GetMapping("/{id}")
    public BatchModel getRecordById(Long id) {
        return batchService.getRecordById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteRecordById(@PathVariable Long id) {
        batchService.deleteRecordById(id);
    }

    @PutMapping
    public BatchModel updateRecordById(@PathVariable Long id, @RequestBody BatchModel batchModel) {
        return batchService.updatedRecordById(id, batchModel);
    }

}
