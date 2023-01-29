package com.learning.rest;

import com.learning.model.TimeSlotModel;
import com.learning.service.Impl.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/timeslot")

public class TimeSlotController {

	private final TimeSlotService timeSlotService;


	@GetMapping
	public List<TimeSlotModel> getAllRecords() {
		 return timeSlotService.getAllRecords();
	}
	
	@PostMapping
	public TimeSlotModel save(@RequestBody TimeSlotModel timeSlotModel) {
		return timeSlotService.saveRecord(timeSlotModel);
	}

	@GetMapping("/{id}")
	public TimeSlotModel getRecordById(Long id) {
		return timeSlotService.getRecordById(id).get();
	}

	@DeleteMapping("/{id}")
	public void  deleteRecordById(@PathVariable Long id)
	{
		timeSlotService.deleteRecordById(id);
	}

	@PutMapping
	public TimeSlotModel updateRecordById (@PathVariable Long id, @RequestBody TimeSlotModel timeSlotModel){
		return timeSlotService.updatedRecordById(id, timeSlotModel);
	}
}
