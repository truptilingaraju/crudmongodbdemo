package com.tp.crudmongodbdemo.dao.daoimpl;

import com.tp.crudmongodbdemo.dao.Addressdao;
import com.tp.crudmongodbdemo.model.Address;
import com.tp.crudmongodbdemo.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class Addressdaoimpl implements Addressdao {

    private final AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        Address savedAddress = null;
        try{
            savedAddress= addressRepository.save(address);
        }
        catch(Exception e){
            //error Statement
        }
        return  savedAddress;
    }

    @Override
    public String deleteAddress(String id) {
        String deletedMsg = null;
        try {
           Optional<Address> optAddress= addressRepository.findById(id);
            if (optAddress.isPresent()){
                Address delAddress= optAddress.get();
                addressRepository.delete(delAddress);
                deletedMsg = "Address Deleted Successfully";
            }
        }catch (Exception e){
            //error statement
        }
        return  deletedMsg;
    }

    @Override
    public Address updateAddress(Address address){

        Address savedAddress = null;
        try{
            savedAddress= addressRepository.save(address);
        }
        catch(Exception e){
            //error Statement
        }
        return  savedAddress;
    }

    @Override
    public List<Address> getAddressList() {
        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }
}
