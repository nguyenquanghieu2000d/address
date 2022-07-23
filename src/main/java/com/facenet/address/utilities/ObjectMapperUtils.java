/*
Class được tạo bởi @author Nguyễn Quang Hiếu vào 19/08/2021 4:36 PM 
*/
package com.facenet.address.utilities;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AutoConfigurationPackage
public class ObjectMapperUtils {

    private final ModelMapper mapper = new ModelMapper();


//    @Autowired
//    public ObjectMapperUtils(ModelMapper mapper) {
//        this.mapper = mapper;
//    }

    public <D,T> List<D> mapAll(final Collection <T> entityList, Class<D> outClass) {

        return entityList.stream().map(chat -> mapper.map(chat, outClass)).collect(Collectors.toList());
    }

    public <D,T> D map(final T entity, Class<D> outClass) {

        return mapper.map(entity, outClass);
    }

    public <S, D> D map(final S source, D destination) {

        mapper.map(source, destination);
        return destination;
    }

}
