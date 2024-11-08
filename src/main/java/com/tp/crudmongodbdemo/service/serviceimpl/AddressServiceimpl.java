package com.tp.crudmongodbdemo.service.serviceimpl;

import com.tp.crudmongodbdemo.dao.Addressdao;
import com.tp.crudmongodbdemo.dto.Addressdto;
import com.tp.crudmongodbdemo.model.Address;
import com.tp.crudmongodbdemo.model.ResponseStructure;
import com.tp.crudmongodbdemo.service.AddressService;
import com.tp.crudmongodbdemo.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceimpl implements AddressService {

    private final Addressdao addressdao;
    private final ModelMapper mapper;

    @Override
    public ResponseEntity<ResponseStructure> createAddress(Addressdto addressdto){

        Address savedAddress=null;
        try {
            //using model mapper:
            Address address= mapper.map(addressdto, Address.class);

            savedAddress = addressdao.createAddress(address);
        }
        catch (Exception e)
        {
            //error statement
        }
        return ResponseUtil.getCreatedResponse(savedAddress);

    }

    @Override
    public ResponseEntity<ResponseStructure> deleteAddress(String id) {
        String deletedMsg=null;
      try{
          deletedMsg = addressdao.deleteAddress(id);
      }catch (Exception e){
          //error statement
      }
      return ResponseUtil.getOkResponse(deletedMsg);
    }

    @Override
    public ResponseEntity<ResponseStructure> updateAddress(Addressdto addressdto){

        Address address= mapper.map(addressdto, Address.class);
        Address updtdAddress = addressdao.updateAddress(address);

        return  ResponseUtil.getOkResponse(updtdAddress);


    }

    @Override
    public ResponseEntity<ResponseStructure> getAddressList(){
        List<Address> addressList= addressdao.getAddressList();
        return ResponseUtil.getOkResponse(addressList);
    }
}
