package com.example.realestaterent.api;

import com.example.realestaterent.entity.AddressEntity;
import com.example.realestaterent.entity.PropertyEntity;
import com.example.realestaterent.exception.PropertyNotFoundException;
import com.example.realestaterent.exception.RequiredParamNotEnteredException;
import com.example.realestaterent.model.Property;
import com.example.realestaterent.service.PropertyService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("property")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @ApiOperation("Получение списка недвижимости")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Список недвижимости", response = Property.class),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Недвижимость не найдена")
    })
    @GetMapping("/all")
    public ResponseEntity getProperty(){
        try {
            return ResponseEntity.ok(propertyService.getAll());
        } catch (PropertyNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @ApiOperation("Получение недвижимости по id")
    @ApiImplicitParam(paramType="path", name = "id", value = "id обьекта недвижимости",
            required = true, dataType = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Обьект недвижимости", response = Property.class),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Обьект недвижимости не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity getOneProperty(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(propertyService.getOne(id));
        } catch (PropertyNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @ApiOperation("Создание новой недвижимости")
    @ApiImplicitParam(paramType="body", name = "property", value = "обьект недвижимости",
            required = true, dataType = "property")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Обьект недвижимости создан", response = Property.class),
            @ApiResponse(code = 400, message = "Введены не все обязательные параметры"),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос")
    })
    @PostMapping
    public ResponseEntity createProperty(@RequestBody Property property) {
        try {
            System.out.println(property.toString());
            if((property.getPropertyType() != null) &&
                    (property.getRentType() != null) &&
                    (property.getRoomsAmount() != null) &&
                    (property.getSquare() != null) &&
                    (property.getPriceDollar() != null)){
                return ResponseEntity.status(201).body(propertyService.create(property));
            }else{
                throw new RequiredParamNotEnteredException();
            }
        }catch (RequiredParamNotEnteredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Обновление даных недвижимости")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "id обьекта недвижимости",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "body", name = "property", value = "обьект недвижимости",
                    required = true, dataType = "property")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Обьект недвижимости обновлен", response = Property.class),
            @ApiResponse(code = 400, message = "Введены не все обязательные параметры"),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Обьект недвижимости не найден")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateProperty(@PathVariable("id") Long id, @RequestBody Property property) {
        try {
            if((property.getPropertyType() != null) &&
                    (property.getRentType() != null) &&
                    (property.getRoomsAmount() != null) &&
                    (property.getSquare() != null) &&
                    (property.getPriceDollar() != null)){
                return ResponseEntity.ok(propertyService.update(id, property));
            }else{
                throw new RequiredParamNotEnteredException();
            }
        } catch (RequiredParamNotEnteredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (PropertyNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }

    @ApiOperation("Удаление недвижимости по id")
    @ApiImplicitParam(paramType="path", name = "id", value = "id обьекта недвижимости",
            required = true, dataType = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Обьект недвижимости удален", response = Long.class),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Обьект недвижимости не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProperty(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(propertyService.deleteOne(id));
        } catch (PropertyNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}
