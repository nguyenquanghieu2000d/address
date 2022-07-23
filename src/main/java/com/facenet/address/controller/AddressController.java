package com.facenet.address.controller;

import com.facenet.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    /**
     * Api get books
     *
     * @return
     */
    @GetMapping(value = "/")
    public ResponseEntity<?> getAllBooks() {

        return new ResponseEntity<>(addressService.findAllAddress(), HttpStatus.OK);
    }

    @GetMapping(value = "/2")
    public ResponseEntity<?> addAllBooks() {

        return new ResponseEntity<>(addressService.addAllAddress(), HttpStatus.OK);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<?> findAllByNameContains(@RequestParam String name) {

        System.out.println(name);
        return new ResponseEntity<>(addressService.findAllAddressByName(name), HttpStatus.OK);
    }


}
