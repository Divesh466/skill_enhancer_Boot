package com.learning.service.Impl;

import com.learning.constants.NumberConstant;
import com.learning.entity.StudentBatchEntity;
import com.learning.model.StudentBatchModel;
import com.learning.repository.StudentBatchRepository;
import com.learning.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentBatchService implements CommonService<StudentBatchModel, Long> {
    private final StudentBatchRepository studentBatchRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<StudentBatchModel> getAllRecords() {
        List<StudentBatchEntity> studentBatchEntityList = studentBatchRepository.findAll();
        if (CollectionUtils.isEmpty(studentBatchEntityList)) {
            List<StudentBatchModel> studentBatchModelList = studentBatchEntityList.stream().map(studentBatchEntity -> {
              //  StudentBatchModel studentbatchModel = new StudentBatchModel();
               // BeanUtils.copyProperties(studentBatchEntity, studentbatchModel);
                StudentBatchModel studentbatchModel = modelMapper.map(studentBatchEntity,StudentBatchModel.class);
                return studentbatchModel;
            }).collect(Collectors.toList());
            return studentBatchModelList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<StudentBatchModel> getLimitedRecords(int count) {
        List<StudentBatchEntity> studentBatchEntityList = studentBatchRepository.findAll();
        if (Objects.nonNull(studentBatchEntityList) && studentBatchEntityList.size() > NumberConstant.ZERO) {
            List<StudentBatchModel> studentBatchModelList = studentBatchEntityList.stream().limit(count).map(studentBatchEntity -> {
               // StudentBatchModel studentBatchModel = new StudentBatchModel();
                //BeanUtils.copyProperties(studentBatchEntity, studentBatchModel);
               StudentBatchModel studentBatchModel = modelMapper.map(studentBatchEntity,StudentBatchModel.class);
                return studentBatchModel;
            }).collect(Collectors.toList());
            return studentBatchModelList;
        } else {
            return Collections.emptyList();
        }
    }


    @Override
    public List<StudentBatchModel> getSortedRecords(String sortBy) {
        List<StudentBatchEntity> studentBatchEntityList = studentBatchRepository.findAll();
        Comparator<StudentBatchEntity> comparator = findSuitableComparator(sortBy);
        if (Objects.nonNull(studentBatchEntityList) && studentBatchEntityList.size() > NumberConstant.ZERO) {
            List<StudentBatchModel> studentBatchModelList = studentBatchEntityList.stream().sorted(comparator).map(studentBatchEntity -> {
               // StudentBatchModel studentBatchModel = new StudentBatchModel();
               // BeanUtils.copyProperties(studentBatchEntity, StudentBatchModel.class);
               StudentBatchModel studentBatchModel= modelMapper.map(studentBatchEntity,StudentBatchModel.class);
                return studentBatchModel;
            }).collect(Collectors.toList());
            return studentBatchModelList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public StudentBatchModel saveRecord(StudentBatchModel record) {
        if (Objects.nonNull(record)) {
            //StudentBatchEntity studentBatchEntity = new StudentBatchEntity();
            //BeanUtils.copyProperties(record, studentBatchEntity);
         StudentBatchEntity   studentBatchEntity = modelMapper.map(record,StudentBatchEntity.class);
            studentBatchRepository.save(studentBatchEntity);
        }
        return record;
    }

    @Override
    public List<StudentBatchModel> saveAll(List<StudentBatchModel> studentBatchModelList) {
        if (Objects.nonNull(studentBatchModelList) && studentBatchModelList.size() > NumberConstant.ZERO) {
            List<StudentBatchEntity> studentBatchEntityList = studentBatchModelList.stream().map(studentBatchModel -> {
               // StudentBatchEntity studentBatchEntity = new StudentBatchEntity();
                //BeanUtils.copyProperties(studentBatchModel, studentBatchEntity);
                StudentBatchEntity studentBatchEntity = modelMapper.map(studentBatchModel, StudentBatchEntity.class);
                return studentBatchEntity;
            }).collect(Collectors.toList());
            studentBatchRepository.saveAll(studentBatchEntityList);
        }
        return studentBatchModelList;
    }

    @Override
    public Optional<StudentBatchModel> getRecordById(Long id) {
        Optional<StudentBatchEntity> optionalEntity = studentBatchRepository.findById(id);
        if (optionalEntity.isPresent()) {
            StudentBatchEntity studentBatchEntity = optionalEntity.get();
           // StudentBatchModel studentBatchModel = new StudentBatchModel();
          //  BeanUtils.copyProperties(studentBatchEntity, studentBatchModel);
            StudentBatchModel studentBatchModel = modelMapper.map(studentBatchEntity ,StudentBatchModel.class);
            return Optional.of(studentBatchModel);
        }
        return Optional.empty();
    }

    @Override
    public void deleteRecordById(Long id) {
        studentBatchRepository.deleteById(id);
    }

    @Override
    public StudentBatchModel updatedRecordById(Long id, StudentBatchModel record) {
        Optional<StudentBatchEntity> optionalStudentBatchEntity = studentBatchRepository.findById(id);
        if (optionalStudentBatchEntity.isPresent()) {
            StudentBatchEntity studentBatchEntity = optionalStudentBatchEntity.get();
           // StudentBatchModel studentbatchModel = new StudentBatchModel();
          //  BeanUtils.copyProperties(record, studentbatchModel);
            modelMapper.map(record, studentBatchEntity);
            studentBatchEntity.setId(id);
            studentBatchRepository.save(studentBatchEntity);
        }
        return record;
    }

    private Comparator<StudentBatchEntity> findSuitableComparator(String sortBy) {
        Comparator<StudentBatchEntity> comparator;
        switch (sortBy) {
            case "fees": {
                comparator = Comparator.comparing(StudentBatchEntity::getFees);
                break;
            }
            case "batchId": {
                comparator = Comparator.comparing(StudentBatchEntity::getBatchId);
                break;
            }
            default: {
                comparator = Comparator.comparing(StudentBatchEntity::getId);
            }
        }
        return comparator;
    }

}
