package com.facenet.address.service.impl;

import com.facenet.address.dto.AddressDTO;
import com.facenet.address.dto.Generation;
import com.facenet.address.model.Address;
import com.facenet.address.repository.AddressRepository;
import com.facenet.address.service.AddressService;
import com.facenet.address.utilities.ObjectMapperUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());


    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ObjectMapperUtils objectMapperUtils;

    @Autowired
    ObjectMapper mapper;

//    @Override
//    public List<AddressDTO2> findAllAddressByName(String name) {

//        try {
////            List<AddressRawDTO> someClassObject = mapper.readValue(new File("D:\\CODE\\2.HOC_TAP\\8. JAVA\\address\\src\\main\\resources\\input.json"), new TypeReference<>() {
////            });
////
////            int count = 0;
////            List<Address> addresses = new ArrayList<>();
////            for (AddressRawDTO addressRawDTO : someClassObject) {
////                Address address = new Address();
////                address.setAddressId(addressRawDTO.getId());
////                address.setName(addressRawDTO.getTen());
////                address.setEnglishName(addressRawDTO.getTenTiengAnh());
////                address.setLevelName(addressRawDTO.getCap());
////                address.setDistrictId(addressRawDTO.getIdQuanHuyen());
////                address.setDistrictName(addressRawDTO.getTenQuanHuyen());
////                address.setCityId(addressRawDTO.getIdTP());
////                address.setCityName(addressRawDTO.getTenTP());
////                addresses.add(address);
////            }
//
//            List<Address> addresses = addressRepository.findAllByCityNameLikeOrDistrictNameLikeOrNameLike("%" + name + "%", "%" + name + "%", "%" + name + "%");
//
////        List<Address> addresses1 = (List<Address>) entityManager.createQuery("SELECT * FROM ADDRESS").getResultList();
////        System.out.println("----------------------------------------------------------");
////        System.out.println(addresses1.size());
//            Map<String, Map<String, List<AddressDTO2>>> cityDistrictMap = new HashMap<>();
//            Map<String, AddressDTO2> cityMap = new HashMap<>();
//            Map<String, AddressDTO2> districtMap = new HashMap<>();
//            for (Address address : addresses) {
//
//                String cityId = address.getCityId();
//                String districtId = address.getDistrictId();
//                if (!cityMap.containsKey(cityId)) {
//                    AddressDTO2 addressDTO2 = new AddressDTO2();
//                    addressDTO2.setAddressName(address.getCityName());
//                    addressDTO2.setAddressId(address.getCityId());
//                    addressDTO2.setLevel("Thành phố / Tỉnh");
//                    cityMap.put(cityId, addressDTO2);
//                }
//
//                if (!districtMap.containsKey(districtId)) {
//                    AddressDTO2 addressDTO2 = new AddressDTO2();
//                    addressDTO2.setAddressName(address.getDistrictName());
//                    addressDTO2.setAddressId(address.getDistrictId());
//                    addressDTO2.setLevel("Quận / Huyện");
//                    districtMap.put(districtId, addressDTO2);
//                }
//
//                if (!cityDistrictMap.containsKey(cityId)) {
//                    Map<String, List<AddressDTO2>> booleanMap = new HashMap<>();
//                    List<AddressDTO2> dto2s = new ArrayList<>();
//                    AddressDTO2 addressDTO2 = new AddressDTO2();
//                    addressDTO2.setAddressName(address.getName());
//                    addressDTO2.setAddressId(address.getAddressId());
//                    addressDTO2.setLevel(address.getLevelName());
//                    dto2s.add(addressDTO2);
//                    booleanMap.put(districtId, dto2s);
//                    cityDistrictMap.put(cityId, booleanMap);
//                } else {
//                    Map<String, List<AddressDTO2>> city = cityDistrictMap.get(cityId);
//                    if (city.containsKey(districtId)) {
//                        List<AddressDTO2> listXaPhuong = city.get(districtId);
//                        AddressDTO2 addressDTO2 = new AddressDTO2();
//                        addressDTO2.setAddressName(address.getName());
//                        addressDTO2.setAddressId(address.getAddressId());
//                        addressDTO2.setLevel(address.getLevelName());
//                        listXaPhuong.add(addressDTO2);
//                        city.put(districtId, listXaPhuong);
//                        cityDistrictMap.put(cityId, city);
//                    } else {
//                        List<AddressDTO2> dto2s = new ArrayList<>();
//                        AddressDTO2 addressDTO2 = new AddressDTO2();
//                        addressDTO2.setAddressName(address.getName());
//                        addressDTO2.setAddressId(address.getAddressId());
//                        addressDTO2.setLevel(address.getLevelName());
//                        dto2s.add(addressDTO2);
//                        city.put(districtId, dto2s);
//                        cityDistrictMap.put(cityId, city);
//                    }
//                }
//            }
//            List<AddressDTO2> addressDTO2s = new ArrayList<>();
//            for (Map.Entry<String, Map<String, List<AddressDTO2>>> cityDistrictEntrySet : cityDistrictMap.entrySet()) {
//                AddressDTO2 addressDTO2City = cityMap.get(cityDistrictEntrySet.getKey());
//                addressDTO2City.setListChild(new ArrayList<>());
//                for (Map.Entry<String, List<AddressDTO2>> districtEntrySet : cityDistrictEntrySet.getValue().entrySet()) {
//                    AddressDTO2 addressDTO2District = districtMap.get(districtEntrySet.getKey());
//                    addressDTO2District.setListChild(districtEntrySet.getValue());
//                    addressDTO2City.setEachListChild(addressDTO2District);
//                }
//                addressDTO2s.add(addressDTO2City);
//            }
//
//            List<Addressrecursive> addressrecursives = new ArrayList<>();
//            for (AddressDTO2 addressDTO2 : addressDTO2s) {
//
//                Addressrecursive addressrecursive = new Addressrecursive();
//                addressrecursive.setAddressId(addressDTO2.getAddressId());
//                addressrecursive.setName(addressDTO2.getAddressName());
//                addressrecursive.setParentId("0");
//                addressrecursive.setType(addressDTO2.getLevel());
//                addressrecursives.add(addressrecursive);
////                address2Repository.save(addressrecursive);
//
//                for (AddressDTO2 addressDTO21 : addressDTO2.getListChild()) {
//
//                    Addressrecursive addressrecursive2 = new Addressrecursive();
//                    addressrecursive2.setAddressId(addressDTO21.getAddressId());
//                    addressrecursive2.setName(addressDTO21.getAddressName());
//                    addressrecursive2.setParentId(addressrecursive.getAddressId());
//                    addressrecursive2.setType(addressDTO21.getLevel());
////                    address2Repository.save(addressrecursive2);
//                    addressrecursives.add(addressrecursive2);
//                    for (AddressDTO2 addressDTO22 : addressDTO21.getListChild()) {
//                        Addressrecursive addressrecursive3 = new Addressrecursive();
//                        addressrecursive3.setAddressId(addressDTO22.getAddressId());
//                        addressrecursive3.setName(addressDTO22.getAddressName());
//                        addressrecursive3.setParentId(addressrecursive2.getAddressId());
//                        addressrecursive3.setType(addressDTO22.getLevel());
////                        address2Repository.save(addressrecursive3);
//                        addressrecursives.add(addressrecursive3);
//                    }
//                }
//            }
//            address2Repository.saveAll(addressrecursives);
//
//
//            return addressDTO2s;
//        } catch (Exception e) {
//            LOGGER.info(e.getMessage());
//        }
//        return null;
//        return null;
//    }


    public AddressDTO getAddressRecursive(String id, List<Address> addresses) {

        AddressDTO addressDTO2 = new AddressDTO();
        Address address = addresses.stream().filter(v -> v.getAddressId().equals(id)).collect(Collectors.toList()).get(0);
        addressDTO2.setAddressId(address.getAddressId());
        addressDTO2.setAddressName(address.getName());
        addressDTO2.setLevel(address.getType());
        List<Address> childNode = addresses.stream().filter(v -> v.getParentId().equals(address.getAddressId())).collect(Collectors.toList());
        if (childNode.size() == 0) return addressDTO2;
        List<AddressDTO> addressDTO2s = new ArrayList<>();
        for (Address address2 : childNode) {
            AddressDTO addressDTO21 = getAddressRecursive(address2.getAddressId(), addresses);
            addressDTO2s.add(addressDTO21);
        }
        addressDTO2.setListChild(addressDTO2s);
        return addressDTO2;
    }


    public List<Generation> getAddress(String id) {

        Query query = entityManager.createNativeQuery("CALL district_recursive(?1);", "AddressMapping").setParameter(1, id);
        List<Generation> addressRecursiveDTOS = query.getResultList();
        return addressRecursiveDTOS;
    }

    @Override
    public AddressDTO getListAddress(String root_id) {

        List<Generation> generations = getAddress(root_id);
        if (generations == null || generations.size() == 0) return new AddressDTO();
        LOGGER.info(generations.size());
        Generation generation1 = generations.get(0);
        AddressDTO addressDTO2s = new AddressDTO();
        addressDTO2s.setAddressId(generation1.getParentAddressId());
        addressDTO2s.setAddressName(generation1.getParentName());
        addressDTO2s.setLevel(generation1.getParentType());
        addressDTO2s.setListChild(new ArrayList<>());
        Map<String, AddressDTO> addressDTO2Map = new HashMap<>();
        addressDTO2Map.put(root_id, addressDTO2s);
        for (Generation generation : generations) {
            String parent_id = generation.getParentAddressId();
            AddressDTO parentAddressDTO2 = addressDTO2Map.get(parent_id);
            AddressDTO addressDTO2 = new AddressDTO();
            addressDTO2.setAddressId(generation.getChildAddressId());
            addressDTO2.setAddressName(generation.getChildName());
            addressDTO2.setLevel(generation.getChildType());
            addressDTO2.setListChild(new ArrayList<>());
            addressDTO2Map.put(addressDTO2.getAddressId(), addressDTO2);
            if (parentAddressDTO2.getListChild() == null) {
                LOGGER.info(parentAddressDTO2.toString());
            }
            parentAddressDTO2.setEachListChild(addressDTO2);
        }
        return addressDTO2s;
    }
}
