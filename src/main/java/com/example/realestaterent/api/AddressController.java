package com.example.realestaterent.api;

import com.example.realestaterent.entity.AddressEntity;
import com.example.realestaterent.entity.OwnerEntity;
import com.example.realestaterent.exception.AddressAlreadyExistException;
import com.example.realestaterent.exception.AddressNotFoundException;
import com.example.realestaterent.exception.RequiredParamNotEnteredException;
import com.example.realestaterent.service.AddressService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Список всех адресов",
            notes = "Получение списка всех адресов, если они есть")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Все адреса", response = AddressEntity.class),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Адреса не найдены")
    })
    @GetMapping("/all")
    public ResponseEntity getAddresses(){
        try {
            return ResponseEntity.ok(addressService.getAll());
        } catch (AddressNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @ApiOperation(value = "Получение адреса по id",
            notes = "Получение адреса по id, если он есть")
    @ApiImplicitParam(paramType="path", name = "id", value = "id адреса",
            required = true, dataType = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Адрес найден по id", response = AddressEntity.class),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Адрес не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity getOneAddress(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(addressService.getOne(id));
        } catch (AddressNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    @ApiOperation(value = "Создание нового адреса",
            notes = "Получение адреса по id, если он есть")
    @ApiImplicitParam(paramType="body", name = "address", value = "адрес",
            required = true, dataType = "address")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Обьект создан", response = AddressEntity.class),
            @ApiResponse(code = 400, message = "Введены не все обязательные параметры"),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 409, message = "Адрес с таким номером телефона или почтой уже существует")
    })
    @PostMapping
    public ResponseEntity<String> createAddress(@RequestBody AddressEntity address) {
        try {
            if((address.getCountry() == null) ||
                    (address.getCity() == null) ||
                    (address.getStreet() == null) ||
                    (address.getHouseNumber() == null)){
                throw new RequiredParamNotEnteredException();
            }
            if(addressService.exists(address)){
                throw new AddressAlreadyExistException();
            }else{
                addressService.create(address);
            }
            return ResponseEntity.status(201).body(address.toString());
        } catch (AddressAlreadyExistException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (RequiredParamNotEnteredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Изменение адреса по id")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "id адреса",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "body", name = "address", value = "адрес",
                    required = true, dataType = "address")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Адрес обновлен", response = AddressEntity.class),
            @ApiResponse(code = 400, message = "Введены не все обязательные параметры"),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Адрес не найден")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAddress(@PathVariable("id") Long id, @RequestBody AddressEntity address) {
        try {
            if((address.getCountry() != null) &&
                    (address.getCity() != null) &&
                    (address.getStreet() != null) &&
                    (address.getHouseNumber() != null)){
                addressService.update(id, address);
            }else{
                throw new RequiredParamNotEnteredException();
            }
            return ResponseEntity.ok(address.toString());
        } catch (AddressNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (RequiredParamNotEnteredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление адреса по id")
    @ApiImplicitParam(paramType="path", name = "id", value = "id адреса",
            required = true, dataType = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Адрес удален", response = Long.class),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Адрес не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAddress(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(addressService.deleteOne(id));
        } catch (AddressNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
