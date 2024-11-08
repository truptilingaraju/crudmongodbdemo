package com.tp.crudmongodbdemo.repository;

import com.tp.crudmongodbdemo.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address , String> {
}
