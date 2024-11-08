package com.tp.crudmongodbdemo.controller;

import com.tp.crudmongodbdemo.dto.Addressdto;
import com.tp.crudmongodbdemo.model.ResponseStructure;
import com.tp.crudmongodbdemo.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @Operation(description = "To create Address Info" ,summary="Address will be saved in the database")
    @ApiResponses(value = @ApiResponse(description = "Address created", responseCode="201"))
    @PostMapping("/create")
    public ResponseEntity<ResponseStructure> createAddress(@RequestBody Addressdto addressdto)
    {
        return addressService.createAddress(addressdto);
    }

    @Operation(description = "To delete Address Info",summary="Address will be deleted from database" )
    @ApiResponses(value = @ApiResponse(description = "Address deleted", responseCode="200"))
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseStructure> deleteAddress(@RequestParam String id)
    {
        return addressService.deleteAddress(id);
    }

    @Operation(description = "To update Address Info",summary="Address will be updated in the database" )
    @ApiResponses(value = @ApiResponse(description = "Address updated", responseCode="200"))
    @PutMapping("/update")
    public ResponseEntity<ResponseStructure> updateAddress(@RequestBody Addressdto addressdto)
    {
        return addressService.updateAddress(addressdto);
    }

    @Operation(description = "To get Address Info",summary="Address will be fetched from the database" )
    @ApiResponses(value = @ApiResponse(description = "Address fetched", responseCode="200"))
    @GetMapping("/get/address")
    public ResponseEntity<ResponseStructure> getAddressList()
    {
        return addressService.getAddressList();
    }



}
