package com.horizon.service.impl;

import com.horizon.domain.RoomType;
import com.horizon.domain.Services;
import com.horizon.dto.ServicesDto;
import com.horizon.mapper.ServicesMapper;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.repository.ServicesRepository;
import com.horizon.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public ServicesServiceImpl(ServicesRepository servicesRepository, RoomTypeRepository roomTypeRepository) {
        this.servicesRepository = servicesRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public ServicesDto createServices(ServicesDto servicesDto) {
        Services services = ServicesMapper.mapToService(servicesDto);
        Services saveServices = servicesRepository.save(services);
        return ServicesMapper.mapToServicesDto(saveServices);
    }


    @Override
    public ServicesDto updateServices(Integer serviceId, ServicesDto updateServices) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Services is not exist with given id: " + serviceId));
        RoomType roomType = roomTypeRepository.findById(updateServices.getRoomTypeId()).orElseThrow(
                () -> new ResourceNotFoundException("RoomType is not exist with given id: " + updateServices.getRoomTypeId()));
        services.setRoomType(roomType);
        services.setDescription(updateServices.getDescription());
        services.setStartedTime(updateServices.getStartedTime());
        services.setEndTime(updateServices.getEndTime());

        Services updateServicesObj = servicesRepository.save(services);
        return ServicesMapper.mapToServicesDto(updateServicesObj);
    }

    @Override
    public void deleteServices(Integer serviceId) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Services is not exist with given id: " + serviceId)
        );
        servicesRepository.delete(services);
    }

    @Override
    public ServicesDto getServicesById(Integer serviceId) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Services is not exist with given id: " + serviceId)
        );
        return ServicesMapper.mapToServicesDto(services);
    }

    @Override
    public List<ServicesDto> getAllServices() {
        List<Services> services = servicesRepository.findAll();
        return services.stream().map((service) -> ServicesMapper.mapToServicesDto(service)).collect(Collectors.toList());
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        servicesRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void delete(Services entity) {
        servicesRepository.delete(entity);
    }

    @Override
    public <S extends Services> boolean exists(Example<S> example) {
        return servicesRepository.exists(example);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Services> entities) {
        servicesRepository.deleteInBatch(entities);
    }

    @Override
    public List<Services> findAll(Sort sort) {
        return servicesRepository.findAll(sort);
    }

    @Override
    public void deleteById(Integer integer) {
        servicesRepository.deleteById(integer);
    }

    @Override
    public <S extends Services> long count(Example<S> example) {
        return servicesRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<Services> entities) {
        servicesRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return servicesRepository.count();
    }

    @Override
    public <S extends Services> List<S> findAll(Example<S> example) {
        return servicesRepository.findAll(example);
    }

    @Override
    public <S extends Services> List<S> findAll(Example<S> example, Sort sort) {
        return servicesRepository.findAll(example, sort);
    }

    @Override
    public <S extends Services> Page<S> findAll(Example<S> example, Pageable pageable) {
        return servicesRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Services> S saveAndFlush(S entity) {
        return servicesRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Services> List<S> saveAllAndFlush(Iterable<S> entities) {
        return servicesRepository.saveAllAndFlush(entities);
    }

    @Override
    public boolean existsById(Integer integer) {
        return servicesRepository.existsById(integer);
    }

    @Override
    @Deprecated
    public Services getById(Integer integer) {
        return servicesRepository.getById(integer);
    }

    @Override
    public <S extends Services> List<S> saveAll(Iterable<S> entities) {
        return servicesRepository.saveAll(entities);
    }

    @Override
    public Services getReferenceById(Integer integer) {
        return servicesRepository.getReferenceById(integer);
    }

    @Override
    public Optional<Services> findById(Integer integer) {
        return servicesRepository.findById(integer);
    }

    @Override
    public void flush() {
        servicesRepository.flush();
    }

    @Override
    public Page<Services> findAll(Pageable pageable) {
        return servicesRepository.findAll(pageable);
    }

    @Override
    public void deleteAll() {
        servicesRepository.deleteAll();
    }

    @Override
    public <S extends Services> Optional<S> findOne(Example<S> example) {
        return servicesRepository.findOne(example);
    }

    @Override
    public void deleteAll(Iterable<? extends Services> entities) {
        servicesRepository.deleteAll(entities);
    }

    @Override
    public List<Services> findAllById(Iterable<Integer> integers) {
        return servicesRepository.findAllById(integers);
    }

    @Override
    @Deprecated
    public Services getOne(Integer integer) {
        return servicesRepository.getOne(integer);
    }

    @Override
    public <S extends Services, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return servicesRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends Services> S save(S entity) {
        return servicesRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        servicesRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        servicesRepository.deleteAllInBatch();
    }

    @Override
    public List<Services> findAll() {
        return servicesRepository.findAll();
    }
}
