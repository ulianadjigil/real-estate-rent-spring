package com.example.realestaterent.api;

import com.example.realestaterent.entity.AddressEntity;
import com.example.realestaterent.exception.AddressAlreadyExistException;
import com.example.realestaterent.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
@Api(description = "Контроллер адресов")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation("Получение списка всех адресов")
    @GetMapping
    public ResponseEntity getAddresses(){
        try {
            return ResponseEntity.ok(addressService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Получение адреса по id")
    @GetMapping("/{id}")
    public ResponseEntity getOneAddress(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(addressService.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Создание нового адреса")
    @PostMapping
    public ResponseEntity<String> createAddress(@RequestBody AddressEntity address) {
        try {
            if(addressService.exists(address)){
                throw new AddressAlreadyExistException();
            }else{
                addressService.create(address);
            }
            return ResponseEntity.ok(address.toString());
        } catch (AddressAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Изменение адреса по id")
    @PutMapping
    public ResponseEntity<String> updateAddress(@RequestBody AddressEntity address) {
        try {
            addressService.update(address);
            return ResponseEntity.ok(address.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление адреса по id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(addressService.deleteOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
