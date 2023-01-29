package com.learning.rest;

import com.learning.model.StudentModel;
import com.learning.service.Impl.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
	
	private final StudentService studentService;

	@GetMapping
	public List<StudentModel> getAllRecords() {
		 return studentService.getAllRecords();	
	}
	
	@PostMapping
	public StudentModel save(@RequestBody StudentModel studentModel) {
		return studentService.saveRecord(studentModel);
	}

	@GetMapping("/{id}")
	public StudentModel getRecordById(Long id) {
		return studentService.getRecordById(id).get();
	}
	
	@DeleteMapping("/{id}")
	public void  deleteRecordById(@PathVariable Long id)
	{
		studentService.deleteRecordById(id);
	}

	@PutMapping ("/{id}")
	public StudentModel updateRecordById (@PathVariable Long id, @RequestBody StudentModel studentModel){
		return studentService.updatedRecordById(id, studentModel);
	}

}
