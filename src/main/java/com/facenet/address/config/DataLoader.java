package com.facenet.address.config;

import com.facenet.address.dto.AddressRecursiveParam;
import com.facenet.address.dto.Generation;
import com.facenet.address.repository.AddressRepository;
import com.facenet.address.service.impl.AddressServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());

    @Autowired
    EntityManager entityManager;

    @Autowired
    ApplicationContext applicationContext;

    public void run(ApplicationArguments args) {
//        LOGGER.info("Cache Loading ....");
//        AddressRecursiveParam addressRecursiveParam = applicationContext.getBean(AddressRecursiveParam.class);
//        Query query = entityManager.createNativeQuery("CALL district_recursive(?1);", "AddressMapping").setParameter(1, "-1");
//        List<Generation> addressRecursiveDTOS = query.getResultList();
//        addressRecursiveParam.setGenerations(addressRecursiveDTOS);
//        LOGGER.info("Cache size: " + addressRecursiveDTOS.size());
//        LOGGER.info("Cache Loaded Success !!!!!");
    }
}