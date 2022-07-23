package com.facenet.address.service.impl;

import com.facenet.address.dto.AddressDTO;
import com.facenet.address.dto.AddressDTO2;
import com.facenet.address.dto.AddressRawDTO;
import com.facenet.address.model.Address;
import com.facenet.address.repository.AddressRepository;
import com.facenet.address.service.AddressService;
import com.facenet.address.utilities.ObjectMapperUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ObjectMapperUtils objectMapperUtils;

    @Autowired
    ObjectMapper mapper;

    @Override
    public List<AddressDTO> findAllAddress() {

        return objectMapperUtils.mapAll(addressRepository.findAll(), AddressDTO.class);
    }


    @Override
    public List<AddressDTO2> findAllAddressByName(String name) {


        List<Address> addresses = addressRepository.findAllByCityNameLikeOrDistrictNameLikeOrNameLike("%" + name + "%", "%" + name + "%", "%" + name + "%");
        Map<String, Map<String, List<AddressDTO2>>> cityDistrictMap = new HashMap<>();
        Map<String, AddressDTO2> cityMap = new HashMap<>();
        Map<String, AddressDTO2> districtMap = new HashMap<>();
        for (Address address : addresses) {

            String cityId = address.getCityId();
            String districtId = address.getDistrictId();
            if (!cityMap.containsKey(cityId)) {
                AddressDTO2 addressDTO2 = new AddressDTO2();
                addressDTO2.setAddressName(address.getCityName());
                addressDTO2.setAddressId(address.getCityId());
                addressDTO2.setLevel("Thành phố / Tỉnh");
                cityMap.put(cityId, addressDTO2);
            }

            if (!districtMap.containsKey(districtId)) {
                AddressDTO2 addressDTO2 = new AddressDTO2();
                addressDTO2.setAddressName(address.getDistrictName());
                addressDTO2.setAddressId(address.getDistrictId());
                addressDTO2.setLevel("Quận / Huyện");
                districtMap.put(districtId, addressDTO2);
            }

            if (!cityDistrictMap.containsKey(cityId)) {
                Map<String, List<AddressDTO2>> booleanMap = new HashMap<>();
                List<AddressDTO2> dto2s = new ArrayList<>();
                AddressDTO2 addressDTO2 = new AddressDTO2();
                addressDTO2.setAddressName(address.getName());
                addressDTO2.setAddressId(address.getAddressId());
                addressDTO2.setLevel(address.getLevelName());
                dto2s.add(addressDTO2);
                booleanMap.put(districtId, dto2s);
                cityDistrictMap.put(cityId, booleanMap);
            }
            else {
                Map<String, List<AddressDTO2>> city = cityDistrictMap.get(cityId);
                if (city.containsKey(districtId)) {
                    List<AddressDTO2> listXaPhuong = city.get(districtId);
                    AddressDTO2 addressDTO2 = new AddressDTO2();
                    addressDTO2.setAddressName(address.getName());
                    addressDTO2.setAddressId(address.getAddressId());
                    addressDTO2.setLevel(address.getLevelName());
                    listXaPhuong.add(addressDTO2);
                    city.put(districtId,listXaPhuong);
                    cityDistrictMap.put(cityId, city);
                } else {
                    List<AddressDTO2> dto2s = new ArrayList<>();
                    AddressDTO2 addressDTO2 = new AddressDTO2();
                    addressDTO2.setAddressName(address.getName());
                    addressDTO2.setAddressId(address.getAddressId());
                    addressDTO2.setLevel(address.getLevelName());
                    dto2s.add(addressDTO2);
                    city.put(districtId, dto2s);
                    cityDistrictMap.put(cityId, city);
                }
            }
        }
        List<AddressDTO2> addressDTO2s = new ArrayList<>();
        for (Map.Entry<String, Map<String, List<AddressDTO2>>> cityDistrictEntrySet : cityDistrictMap.entrySet()) {
            AddressDTO2 addressDTO2City = cityMap.get(cityDistrictEntrySet.getKey());
            addressDTO2City.setListChild(new ArrayList<>());
            for (Map.Entry<String, List<AddressDTO2>> districtEntrySet : cityDistrictEntrySet.getValue().entrySet()) {
                AddressDTO2 addressDTO2District = districtMap.get(districtEntrySet.getKey());
                addressDTO2District.setListChild(districtEntrySet.getValue());
                addressDTO2City.setEachListChild(addressDTO2District);
            }
            addressDTO2s.add(addressDTO2City);
        }
        return addressDTO2s;
    }


    @Override
    public List<AddressDTO> addAllAddress() {

        try {
            List<AddressRawDTO> someClassObject = mapper.readValue(new File("D:\\CODE\\2.HOC_TAP\\8. JAVA\\address\\src\\main\\resources\\input.json"), new TypeReference<>() {
            });

            int count = 0;
            List<Address> addresses = new ArrayList<>();
            for (AddressRawDTO addressRawDTO : someClassObject) {
                Address address = new Address();
                address.setAddressId(addressRawDTO.getId());
                address.setName(addressRawDTO.getTen());
                address.setEnglishName(addressRawDTO.getTenTiengAnh());
                address.setLevelName(addressRawDTO.getCap());
                address.setDistrictId(addressRawDTO.getIdQuanHuyen());
                address.setDistrictName(addressRawDTO.getTenQuanHuyen());
                address.setCityId(addressRawDTO.getIdTP());
                address.setCityName(addressRawDTO.getTenTP());
                addresses.add(address);
            }

            addressRepository.saveAll(addresses);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }

}
