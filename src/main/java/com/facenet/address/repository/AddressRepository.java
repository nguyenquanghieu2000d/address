package com.facenet.address.repository;

import com.facenet.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    List<Address> findAllByCityNameLikeOrDistrictNameLikeOrNameLike(String name, String name2, String name3);
}
