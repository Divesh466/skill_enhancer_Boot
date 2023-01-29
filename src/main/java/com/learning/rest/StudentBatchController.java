package com.learning.rest;

import com.learning.model.StudentBatchModel;
import com.learning.service.Impl.StudentBatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping ("/studentBatch")
   public class StudentBatchController { 
	private final StudentBatchService studentBatchService ;

	@GetMapping
	public List<StudentBatchModel>getAllRecords(){
		return studentBatchService.getAllRecords();
	}
	@PostMapping
	public StudentBatchModel save(@RequestBody StudentBatchModel studentBatchModel) {
		return studentBatchService.saveRecord(studentBatchModel);
	}
	@GetMapping("/{id}")
	public StudentBatchModel getRecordById(Long id) {
		return studentBatchService.getRecordById(id).get();
	}
	@DeleteMapping("/{id}")
	public void  deleteRecordById(Long id) {
		studentBatchService.deleteRecordById(id);
	}
	@PutMapping
	public StudentBatchModel updateRecordById (@PathVariable Long id,
											   @RequestBody StudentBatchModel studentBatchModel){
		return studentBatchService.updatedRecordById(id ,studentBatchModel);
	}
}
