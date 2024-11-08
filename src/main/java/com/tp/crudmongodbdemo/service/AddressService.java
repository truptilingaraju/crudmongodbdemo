package com.tp.crudmongodbdemo.service;

import com.tp.crudmongodbdemo.dto.Addressdto;
import com.tp.crudmongodbdemo.model.ResponseStructure;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    public ResponseEntity<ResponseStructure> createAddress(Addressdto addressdto);

    public ResponseEntity<ResponseStructure> deleteAddress(String id);

    public ResponseEntity<ResponseStructure> updateAddress(Addressdto addressdto);

    public ResponseEntity<ResponseStructure> getAddressList();
}
