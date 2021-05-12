package com.demoservice.service.common;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappingService<E, D> {
    @Autowired
    private ModelMapper modelMapper;

    public <T> T convertToDto(E entity, Class<T> classType) {
        return modelMapper.map(entity, classType);
    }

    public <T> T convertToEntity(D dto, Class<T> classType) {
        return modelMapper.map(dto, classType);
    }

    public void updateEntityFromDto(D dto, E entity) {
        modelMapper.map(dto, entity);
    }
}
