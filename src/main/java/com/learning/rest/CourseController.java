package com.learning.rest;

import com.learning.model.CourseModel;
import com.learning.service.Impl.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;


    @GetMapping
    public List<CourseModel> getAllRecords() {
        return courseService.getAllRecords();
    }

    @PostMapping
    public CourseModel save(@RequestBody CourseModel courseModel) {
        return courseService.saveRecord(courseModel);
    }

    @GetMapping("/{id}")
    public CourseModel getRecordById(Long id) {
        return courseService.getRecordById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteRecordById(@PathVariable Long id) {
        courseService.deleteRecordById(id);
    }

    @PutMapping
    public CourseModel updateRecordById(@PathVariable Long id, @RequestBody CourseModel courseModel) {
        return courseService.updatedRecordById(id, courseModel);
    }
}
