package com.facenet.address.service;

import com.facenet.address.dto.AddressDTO;
import com.facenet.address.dto.Generation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    List<Generation> getAddress(String id);

    AddressDTO getListAddress(String id);

}
