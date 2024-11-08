package com.tp.crudmongodbdemo.dao;

import com.tp.crudmongodbdemo.model.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Addressdao {

    public Address createAddress(Address address);

    public String deleteAddress(String id);

    public Address updateAddress(Address address);

    public List<Address> getAddressList();
}
