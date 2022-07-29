package com.facenet.address.controller;

import com.facenet.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getListAddress(@PathVariable String id) {

        return new ResponseEntity<>(addressService.getListAddress(id), HttpStatus.OK);
    }


}
