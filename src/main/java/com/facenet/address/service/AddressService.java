package com.facenet.address.service;

import com.facenet.address.dto.AddressDTO;
import com.facenet.address.dto.AddressDTO2;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AddressService {

    List<AddressDTO> findAllAddress();

    List<AddressDTO2> findAllAddressByName(String name);

    List<AddressDTO> addAllAddress();
}
