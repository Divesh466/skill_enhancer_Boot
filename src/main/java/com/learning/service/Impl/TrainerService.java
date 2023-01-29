package com.learning.service.Impl;

import com.learning.constants.NumberConstant;
import com.learning.entity.TrainerEntity;
import com.learning.model.TrainerModel;
import com.learning.repository.TrainerRepository;
import com.learning.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerService implements CommonService<TrainerModel, Long> {
    private final TrainerRepository trainerRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<TrainerModel> getAllRecords() {
        List<TrainerEntity> trainerEntityList = trainerRepository.findAll();
        if (CollectionUtils.isEmpty(trainerEntityList)) {
            List<TrainerModel> trainerModelList = trainerEntityList.stream().map(trainerEntity -> {
               // TrainerModel trainerModel = new TrainerModel();
              //  BeanUtils.copyProperties(trainerEntity, trainerModel);
                TrainerModel trainerModel = modelMapper.map(trainerEntity,TrainerModel.class);
                return trainerModel;
            }).collect(Collectors.toList());
            return trainerModelList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<TrainerModel> getLimitedRecords(int count) {
        List<TrainerEntity> trainerEntityList = trainerRepository.findAll();
        if (Objects.nonNull(trainerEntityList) && trainerEntityList.size() > NumberConstant.ZERO) {
            List<TrainerModel> trainerModelList = trainerEntityList.stream().limit(count).map(trainerEntity -> {
               // TrainerModel trainerModel = new TrainerModel();
               // BeanUtils.copyProperties(trainerEntity, TrainerModel.class);
               TrainerModel trainerModel = modelMapper.map(trainerEntity,TrainerModel.class);
                return trainerModel;
            }).collect(Collectors.toList());
            return trainerModelList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<TrainerModel> getSortedRecords(String sortBy) {
        List<TrainerEntity> trainerEntityList = trainerRepository.findAll();
        Comparator<TrainerEntity> comparator = findSuitableComparator(sortBy);
        if (Objects.nonNull(trainerEntityList) && trainerEntityList.size() > NumberConstant.ZERO) {
            List<TrainerModel> trainerModelList = trainerEntityList.stream().sorted(comparator).map(trainerEntity -> {
               // TrainerModel trainerModel = new TrainerModel();
              //  BeanUtils.copyProperties(trainerEntity, TrainerModel.class);
               TrainerModel trainerModel = modelMapper.map(trainerEntity,TrainerModel.class);
                return trainerModel;
            }).collect(Collectors.toList());
            return trainerModelList;
        } else {
            return Collections.emptyList();
        }

    }

    @Override
    public TrainerModel saveRecord(TrainerModel record) {
        if (Objects.nonNull(record)) {
            //TrainerEntity trainerEntity = new TrainerEntity();
            //BeanUtils.copyProperties(record, trainerEntity);
           TrainerEntity trainerEntity = modelMapper.map(record,TrainerEntity.class);
            trainerRepository.save(trainerEntity);
        }
        return record;
    }

    @Override
    public List<TrainerModel> saveAll(List<TrainerModel> trainerModelList) {
        if (Objects.nonNull(trainerModelList) && trainerModelList.size() > NumberConstant.ZERO) {
            List<TrainerEntity> trainerEntityList = trainerModelList.stream().map(trainerModel -> {
               // TrainerEntity trainerEntity = new TrainerEntity();
               // BeanUtils.copyProperties(trainerModel, TrainerEntity.class);
               TrainerEntity trainerEntity = modelMapper.map(trainerModel,TrainerEntity.class);
                return trainerEntity;
            }).collect(Collectors.toList());
            trainerRepository.saveAll(trainerEntityList);
        }
        return trainerModelList;
    }

    @Override
    public Optional<TrainerModel> getRecordById(Long id) {
        Optional<TrainerEntity> optionalEntity = trainerRepository.findById(id);
        if (optionalEntity.isPresent()) {
            TrainerEntity trainerEntity = optionalEntity.get();
            //TrainerModel trainerModel = new TrainerModel();
           // BeanUtils.copyProperties(trainerEntity, trainerModel);
            TrainerModel trainerModel = modelMapper.map(trainerEntity,TrainerModel.class);
            return Optional.of(trainerModel);
        }
        return Optional.empty();
    }

    @Override
    public void deleteRecordById(Long id) {
        trainerRepository.deleteById(id);
    }

    @Override
    public TrainerModel updatedRecordById(Long id, TrainerModel record) {
        Optional<TrainerEntity> optionalTrainerEntity = trainerRepository.findById(id);
        if (optionalTrainerEntity.isPresent()) {
            TrainerEntity trainerEntity = optionalTrainerEntity.get();
            //TrainerModel trainerModel = new TrainerModel();
           // BeanUtils.copyProperties(record, trainerModel);
             modelMapper.map(record, trainerEntity);
            trainerEntity.setId(id);
            trainerRepository.save(trainerEntity);
        }
        return record;
    }
    private Comparator<TrainerEntity> findSuitableComparator(String sortBy) {
        Comparator<TrainerEntity> comparator;
        switch (sortBy) {
            case "name": {
                comparator = Comparator.comparing(TrainerEntity::getName);
                break;
            }
            case "specialization": {
                comparator = Comparator.comparing(TrainerEntity::getSpecialization);
                break;
            }
            default: {
                comparator = Comparator.comparing(TrainerEntity::getId);
            }
        }
        return comparator;

    }
}


