package com.learning.service.Impl;

import com.learning.constants.NumberConstant;
import com.learning.entity.StudentEntity;
import com.learning.model.StudentModel;
import com.learning.repository.StudentRepository;
import com.learning.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService implements CommonService<StudentModel, Long> {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<StudentModel> getAllRecords() {
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        if (!CollectionUtils.isEmpty(studentEntityList)) {
            List<StudentModel> studentModelList = studentEntityList.stream().map(studentEntity -> {
                 //StudentModel studentModel = new StudentModel();
                // BeanUtils.copyProperties(studentEntity, studentModel);
                StudentModel studentModel = modelMapper.map(studentEntity,StudentModel.class);
                return studentModel;
            }).collect(Collectors.toList());
            return studentModelList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<StudentModel> getLimitedRecords(int count) {
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        if (Objects.nonNull(studentEntityList) && studentEntityList.size() > NumberConstant.ZERO) {
            List<StudentModel> studentModelList = studentEntityList.stream().limit(count).map(studentEntity -> {
                // StudentModel studentModel = new StudentModel();
                //  BeanUtils.copyProperties(studentEntity, studentModel);
                StudentModel studentModel = modelMapper.map(studentEntity, StudentModel.class);
                return studentModel;
            }).collect(Collectors.toList());
            return studentModelList;
        } else {
            return Collections.emptyList();
        }
    }


    @Override
    public List<StudentModel> getSortedRecords(String sortBy) {
        List<StudentEntity> studentEntityList = studentRepository.findAll();
        Comparator<StudentEntity> comparator = findSuitableComparator(sortBy);
        if (Objects.nonNull(studentEntityList) && studentEntityList.size() > NumberConstant.ZERO) {
            List<StudentModel> studentModelList = studentEntityList.stream().sorted(comparator).map(studentEntity -> {
                // StudentModel studentModel = new StudentModel();
                //BeanUtils.copyProperties(studentEntity, StudentModel.class);
                StudentModel studentModel = modelMapper.map(studentEntity, StudentModel.class);
                return studentModel;
            }).collect(Collectors.toList());
            return studentModelList;
        } else {
            return Collections.emptyList();
        }

    }

    @Override
    public StudentModel saveRecord(StudentModel record) {
        if (Objects.nonNull(record)) {
            //StudentEntity studentEntity = new StudentEntity();
            //BeanUtils.copyProperties(record, studentEntity);
            StudentEntity studentEntity = modelMapper.map(record, StudentEntity.class);
            studentRepository.save(studentEntity);
        }
        return record;
    }

    @Override
    public List<StudentModel> saveAll(List<StudentModel> studentModelList) {
        if (Objects.nonNull(studentModelList) && studentModelList.size() > NumberConstant.ZERO) {
            List<StudentEntity> studentEntityList = studentModelList.stream().map(studentModel -> {
                // StudentEntity studentEntity = new StudentEntity();
                //  BeanUtils.copyProperties(studentModel, studentEntity);
                StudentEntity studentEntity = modelMapper.map(studentModel, StudentEntity.class);
                return studentEntity;
            }).collect(Collectors.toList());
            studentRepository.saveAll(studentEntityList);
        }
        return studentModelList;
    }

    @Override
    public Optional<StudentModel> getRecordById(Long id) {
        Optional<StudentEntity> optionalEntity = studentRepository.findById(id);
        if (optionalEntity.isPresent()) {
            StudentEntity studentEntity = optionalEntity.get();
            StudentModel studentModel = modelMapper.map(studentEntity, StudentModel.class);
            // StudentModel studentModel = new StudentModel();
            //   BeanUtils.copyProperties(studentEntity, studentModel);
            return Optional.of(studentModel);
        }
        return Optional.empty();
    }

    @Override
    public void deleteRecordById(Long id) {
        studentRepository.deleteById(id);

    }

    @Override
    public StudentModel updatedRecordById(Long id, StudentModel record) {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(id);
        if (optionalStudentEntity.isPresent()) {
            StudentEntity studentEntity = optionalStudentEntity.get();
            //  StudentModel studentModel = new StudentModel();
            // BeanUtils.copyProperties(record, studentModel);
            modelMapper.map(record, studentEntity);
            studentEntity.setId(id);
            studentRepository.save(studentEntity);
        }
        return record;
    }

    private Comparator<StudentEntity> findSuitableComparator(String sortBy) {
        Comparator<StudentEntity> comparator;
        switch (sortBy) {
            case "name": {
                comparator = Comparator.comparing(StudentEntity::getName);
                break;
            }
            case "email": {
                comparator = Comparator.comparing(StudentEntity::getEmail);
                break;
            }
            default: {
                comparator = Comparator.comparing(StudentEntity::getId);
            }
        }
        return comparator;
    }


}

