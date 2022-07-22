package com.example.realestaterent.api;

import com.example.realestaterent.entity.PropertyEntity;
import com.example.realestaterent.model.Property;
import com.example.realestaterent.service.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("property")
@Api(description = "Контроллер недвижимости")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @ApiOperation("Получение списка недвижимости")
    @GetMapping
    public ResponseEntity getProperty(){
        try {
            return ResponseEntity.ok(propertyService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Получение недвижимости по id")
    @GetMapping("/{id}")
    public ResponseEntity getOneProperty(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(propertyService.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Создание новой недвижимости")
    @PostMapping
    public ResponseEntity<String> createProperty(@RequestBody Property property) {
        try {
                PropertyEntity propertyEntity = propertyService.toPropertyEntity(property);
                propertyService.create(propertyEntity);
            return ResponseEntity.ok(propertyEntity.toString());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Обновление даных недвижимости")
    @PutMapping
    public ResponseEntity<String> updateProperty(@RequestBody Property property) {
        try {
            PropertyEntity propertyEntity = propertyService.toPropertyEntity(property);
            propertyService.update(propertyEntity);
            return ResponseEntity.ok(propertyEntity.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление недвижимости по id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProperty(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(propertyService.deleteOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
