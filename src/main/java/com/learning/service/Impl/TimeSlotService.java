package com.learning.service.Impl;

import com.learning.constants.NumberConstant;
import com.learning.entity.TimeSlotEntity;
import com.learning.model.TimeSlotModel;
import com.learning.repository.TimeSlotRepository;
import com.learning.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeSlotService implements CommonService<TimeSlotModel, Long> {
    private final TimeSlotRepository timeSlotRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<TimeSlotModel> getAllRecords() {
        List<TimeSlotEntity> timeSlotEntityList = timeSlotRepository.findAll();
        if (CollectionUtils.isEmpty(timeSlotEntityList)) {
            List<TimeSlotModel> timeSlotModelList = timeSlotEntityList.stream().map(timeSlotEntity -> {
              //  TimeSlotModel timeSlotModel = new TimeSlotModel();
               // BeanUtils.copyProperties(timeSlotEntity, timeSlotModel);
              TimeSlotModel timeSlotModel =  modelMapper.map(timeSlotEntityList,TimeSlotModel.class);
                return timeSlotModel;
            }).collect(Collectors.toList());
            return timeSlotModelList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<TimeSlotModel> getLimitedRecords(int count) {
        List<TimeSlotEntity> timeSlotEntityList = timeSlotRepository.findAll();
        if (Objects.nonNull(timeSlotEntityList) && timeSlotEntityList.size() > NumberConstant.ZERO) {
            List<TimeSlotModel> timeSlotModelList = timeSlotEntityList.stream().limit(count).map(timeSlotEntity -> {
              //  TimeSlotModel timeSlotModel = new TimeSlotModel();
             //   BeanUtils.copyProperties(timeSlotEntity, TimeSlotModel.class);
              TimeSlotModel timeSlotModel = modelMapper.map(timeSlotEntity,TimeSlotModel.class);
                return timeSlotModel;
            }).collect(Collectors.toList());
            return timeSlotModelList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<TimeSlotModel> getSortedRecords(String sortBy) {
        List<TimeSlotEntity> timeSlotEntityList = timeSlotRepository.findAll();
        Comparator<TimeSlotEntity> comparator = findSuitableComparator(sortBy);
        if (Objects.nonNull(timeSlotEntityList) && timeSlotEntityList.size() > NumberConstant.ZERO) {
            List<TimeSlotModel> timeSlotModelList = timeSlotEntityList.stream().sorted(comparator).map(timeSlotEntity -> {
               // TimeSlotModel timeSlotModel = new TimeSlotModel();
             // BeanUtils.copyProperties(timeSlotEntity, TimeSlotModel.class);
             TimeSlotModel timeSlotModel = modelMapper.map(timeSlotEntity,TimeSlotModel.class);
                return timeSlotModel;
            }).collect(Collectors.toList());
            return timeSlotModelList;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public TimeSlotModel saveRecord(TimeSlotModel record) {
        if (Objects.nonNull(record)) {
           // TimeSlotEntity timeSlotEntity = new TimeSlotEntity();
           // BeanUtils.copyProperties(record, timeSlotEntity);
           TimeSlotEntity timeSlotEntity = modelMapper.map(record,TimeSlotEntity.class);
            timeSlotRepository.save(timeSlotEntity);
        }
        return record;
    }

    @Override
    public List<TimeSlotModel> saveAll(List<TimeSlotModel> timeSlotModelList) {
        if (Objects.nonNull(timeSlotModelList) && timeSlotModelList.size() > NumberConstant.ZERO) {
            List<TimeSlotEntity> timeSlotEntityList = timeSlotModelList.stream().map(timeSlotModel -> {
                //TimeSlotEntity timeSlotEntity = new TimeSlotEntity();
                //BeanUtils.copyProperties(timeSlotModel, TimeSlotEntity.class);
               TimeSlotEntity timeSlotEntity = modelMapper.map(timeSlotModel,TimeSlotEntity.class);
                return timeSlotEntity;
            }).collect(Collectors.toList());
            timeSlotRepository.saveAll(timeSlotEntityList);
        }
        return timeSlotModelList;
    }

    @Override
    public Optional<TimeSlotModel> getRecordById(Long id) {
        Optional<TimeSlotEntity> optionalEntity = timeSlotRepository.findById(id);
        if (optionalEntity.isPresent()) {
            TimeSlotEntity timeSlotEntity = optionalEntity.get();
            //TimeSlotModel timeSlotModel = new TimeSlotModel();
           // BeanUtils.copyProperties(timeSlotEntity, timeSlotModel);
           TimeSlotModel timeSlotModel = modelMapper.map(timeSlotEntity,TimeSlotModel.class);
            return Optional.of(timeSlotModel);
        }
        return Optional.empty();
    }

    @Override
    public void deleteRecordById(Long id) {
        timeSlotRepository.deleteById(id);
    }

    @Override
    public TimeSlotModel updatedRecordById(Long id, TimeSlotModel record) {
        Optional<TimeSlotEntity> optionalTimeSlotEntity = timeSlotRepository.findById(id);
        if (optionalTimeSlotEntity.isPresent()) {
            TimeSlotEntity timeSlotEntity = optionalTimeSlotEntity.get();
           // TimeSlotModel timeSlotModel = new TimeSlotModel();
           // BeanUtils.copyProperties(record, timeSlotModel);
             modelMapper.map(record, timeSlotEntity);
            timeSlotEntity.setId(id);
            timeSlotRepository.save(timeSlotEntity);
        }
        return record;
    }
    private Comparator<TimeSlotEntity> findSuitableComparator(String sortBy) {
        Comparator<TimeSlotEntity> comparator;
        switch (sortBy) {
            case "startTime": {
                comparator = Comparator.comparing(TimeSlotEntity::getStartTime);
                break;
            }
            case "endTime": {
                comparator = Comparator.comparing(TimeSlotEntity::getEndTime);
                break;
            }
            default: {
                comparator = Comparator.comparing(TimeSlotEntity::getId);
            }

        }

        return comparator;

    }


}

