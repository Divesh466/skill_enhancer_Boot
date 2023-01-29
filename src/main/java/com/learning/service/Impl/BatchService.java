package com.learning.service.Impl;

import com.learning.constants.NumberConstant;
import com.learning.entity.BatchEntity;
import com.learning.model.BatchModel;
import com.learning.repository.BatchRepository;
import com.learning.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatchService implements CommonService<BatchModel, Long> {
    private final BatchRepository batchRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BatchModel> getAllRecords() {
        List<BatchEntity> batchEntityList = batchRepository.findAll();
        if (!CollectionUtils.isEmpty(batchEntityList)) {
            List<BatchModel> batchModelList = batchEntityList.stream().map(batchEntity -> {
               // BatchModel batchModel = new BatchModel();
                //BeanUtils.copyProperties(batchEntity, batchModel);
                BatchModel batchModel = modelMapper.map(batchEntity, BatchModel.class);
                return batchModel;

            }).collect(Collectors.toList());
            return batchModelList;
        } else {
            return Collections.emptyList();
        }
    }


    @Override
    public List<BatchModel> getLimitedRecords(int count) {
        List<BatchEntity> batchEntityList = batchRepository.findAll();
        if (Objects.nonNull(batchEntityList) && batchEntityList.size() > NumberConstant.ZERO) {
            List<BatchModel> batchModelList = batchEntityList.stream().limit(count).map(batchEntity -> {
             //  BatchModel batchModel = new BatchModel();
               // BeanUtils.copyProperties(batchEntity, batchModel);
                BatchModel batchModel = modelMapper.map(batchEntity, BatchModel.class);
                return batchModel;
            }).collect(Collectors.toList());
            return batchModelList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<BatchModel> getSortedRecords(String sortBy) {
        List<BatchEntity> batchEntityList = batchRepository.findAll();
        Comparator<BatchEntity> comparator = findSuitableComparator(sortBy);
        if (Objects.nonNull(batchEntityList) && batchEntityList.size() > NumberConstant.ZERO) {
            return batchEntityList.stream().sorted(comparator).map(batchEntity -> {
               // BatchModel batchModel = new BatchModel();
               // BeanUtils.copyProperties(batchEntity, BatchModel.class);
                BatchModel batchModel = modelMapper.map(batchEntity, BatchModel.class);
                return batchModel;
            }).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }


    @Override
    public BatchModel saveRecord(BatchModel record) {
        if (Objects.nonNull(record)) {
           // BatchEntity batchEntity = new BatchEntity();
            //BeanUtils.copyProperties(record, batchEntity);
            BatchEntity batchEntity = modelMapper.map(record, BatchEntity.class);
             batchRepository.save(batchEntity);
        }
        return record;
    }

    @Override
    public List<BatchModel> saveAll(List<BatchModel> batchModelList) {
        if (Objects.nonNull(batchModelList) && batchModelList.size() > NumberConstant.ZERO) {
            List<BatchEntity> batchEntityList = batchModelList.stream().map(batchModel -> {
                //BatchEntity batchEntity = new BatchEntity();
               // BeanUtils.copyProperties(batchModel, BatchEntity.class);
                BatchEntity batchentity = modelMapper.map(batchModel, BatchEntity.class);
                return  batchentity;
            }).collect(Collectors.toList());
            batchRepository.saveAll(batchEntityList);
        }
        return batchModelList;
    }

    @Override
    public Optional<BatchModel> getRecordById(Long id) {
        Optional<BatchEntity> optionalEntity = batchRepository.findById(id);
        if (optionalEntity.isPresent()) {
            BatchEntity batchEntity = optionalEntity.get();
           // BatchModel batchModel = new BatchModel();
           // BeanUtils.copyProperties(batchEntity, batchModel);
            BatchModel batchModel = modelMapper.map(batchEntity, BatchModel.class);
            return Optional.of(batchModel);
        }
        return Optional.empty();
    }

    @Override
    public void deleteRecordById(Long id) {
        batchRepository.deleteById(id);

    }
    @Override
    public BatchModel updatedRecordById(Long id, BatchModel record) {
        Optional<BatchEntity> optionalBatchEntity = batchRepository.findById(id);
        if (optionalBatchEntity.isPresent()) {
            BatchEntity batchEntity = optionalBatchEntity.get();
          //  BatchModel batchModel = new BatchModel();
           // BeanUtils.copyProperties(record, batchModel);
            modelMapper.map(record, batchEntity);
            batchEntity.setId(id);
            batchRepository.save(batchEntity);
        }
        return record;
    }


    private Comparator<BatchEntity> findSuitableComparator(String sortBy) {
        Comparator<BatchEntity> comparator;
        switch (sortBy) {
            case "startDate": {
                comparator = Comparator.comparing(BatchEntity::getStartDate);
                break;
            }
            case "endDate": {
                comparator = Comparator.comparing(BatchEntity::getEndDate);
                break;
            }
            case "studentCount": {
                comparator = Comparator.comparing(BatchEntity::getStudentCount);
                break;
            }
            default: {
                comparator = Comparator.comparing(BatchEntity::getId);
            }
        }
        return comparator;
    }


}
