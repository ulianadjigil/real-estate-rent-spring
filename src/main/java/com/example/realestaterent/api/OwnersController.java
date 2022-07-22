package com.example.realestaterent.api;

import com.example.realestaterent.entity.OwnerEntity;
import com.example.realestaterent.exception.OwnerAlreadyExistException;
import com.example.realestaterent.service.OwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("owners")
@Api(description = "Контроллер владельцев")
public class OwnersController {
    @Autowired
    private OwnerService ownerService;

    @ApiOperation("Получение списка всех владельцев")
    @GetMapping("/all")
    public ResponseEntity getOwners(){
        try {
            return ResponseEntity.ok(ownerService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Получение владельца по id")
    @GetMapping("/{id}")
    public ResponseEntity getOneOwner(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(ownerService.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @ApiOperation("Получение части владельцев по номеру странички и заданому количеству")
    @GetMapping
    public ResponseEntity getPaginated(@RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "3") Integer size,
                                       @RequestParam(defaultValue = "idowner") String sortBy) {
        System.out.println("Сюда дошло 0" + page + " " + size + " " + sortBy);
        try {
            return ResponseEntity.ok(ownerService.getPaginated(page, size, sortBy));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage() + "   " + e.getClass());
        }
    }

    @ApiOperation("Создание нового владельца")
    @PostMapping
    public ResponseEntity<String> createOwner(@RequestBody OwnerEntity owner) {
        try {
            if(ownerService.exists(owner)){
                throw new OwnerAlreadyExistException();
            }else{
                ownerService.create(owner);
            }
            return ResponseEntity.ok(owner.toString());
        } catch (OwnerAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Обновление владельца по id")
    @PutMapping
    public ResponseEntity<String> updateOwner(@RequestBody OwnerEntity owner) {
        try {
            ownerService.update(owner);
            return ResponseEntity.ok(owner.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление владельца по id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOwner(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(ownerService.deleteOne(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
