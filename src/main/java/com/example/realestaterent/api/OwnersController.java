package com.example.realestaterent.api;

import com.example.realestaterent.entity.OwnerEntity;
import com.example.realestaterent.exception.IncorrectFilterException;
import com.example.realestaterent.exception.OwnerAlreadyExistException;
import com.example.realestaterent.exception.OwnerNotFoundException;
import com.example.realestaterent.exception.RequiredParamNotEnteredException;
import com.example.realestaterent.service.OwnerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("owners")
public class OwnersController {
    @Autowired
    private OwnerService ownerService;

    private static ArrayList<String> ownerFilters = new ArrayList<>();

    static {
        ownerFilters.add("idowner");
        ownerFilters.add("name");
        ownerFilters.add("surname");
        ownerFilters.add("patronymic");
        ownerFilters.add("email");
        ownerFilters.add("phone");
    }

    @ApiOperation("Получение списка всех владельцев")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Данные владельцев", response = OwnerEntity.class),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Владельцы не найдены")
    })
    @GetMapping("/all")
    public ResponseEntity getOwners(){
        try {
            return ResponseEntity.ok(ownerService.getAll());
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @ApiOperation("Получение владельца по id")
    @ApiImplicitParam(paramType="path", name = "id", value = "id владельца",
            required = true, dataType = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Данные владельца", response = OwnerEntity.class),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Владелец не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity getOneOwner(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(ownerService.getOne(id));
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @ApiOperation("Получение владельца по имени и фамилии")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "name", value = "Имя владельца",
                    required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "surname", value = "Фамилия владельца",
                    required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Данные владельца", response = OwnerEntity.class),
            @ApiResponse(code = 400, message = "Введены не все обязательные параметры"),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Владелец не найден")
    })
    @GetMapping("/byNameSurname")
    public ResponseEntity getOwnerByNameSurname(@RequestParam String name,
                                                @RequestParam String surname){
        try {
            if((name != null) && (surname != null)){
                return ResponseEntity.ok(ownerService.getByNameSurname(name, surname));
            }else{
                throw new RequiredParamNotEnteredException();
            }
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (RequiredParamNotEnteredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ApiOperation("Получение части владельцев по номеру странички и заданому количеству")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "page",
                    value = "Страница", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "size",
                    value = "Количество обьектов на страницу", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "sortBy",
                    value = "Параметр сортировки", dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Данные владельцев", response = OwnerEntity.class),
            @ApiResponse(code = 400, message = "Введен не правильный параметр фильтрации"),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Владельцы не найдены")
    })
    @GetMapping
    public ResponseEntity getPaginated(@RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "3") Integer size,
                                       @RequestParam(defaultValue = "idowner") String sortBy) {
        try {
            boolean flag = false;
            for (String filter : ownerFilters) {
                if (filter.equals(sortBy)){
                    flag = true;
                    break;
                }
            }
            if (!flag){
                throw new IncorrectFilterException();
            }
            return ResponseEntity.ok(ownerService.getPaginated(page, size, sortBy));
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IncorrectFilterException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Создание нового владельца")
    @ApiImplicitParam(paramType="body", name = "owner", value = "владелец",
            required = true, dataType = "owner")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Владелец создан", response = OwnerEntity.class),
            @ApiResponse(code = 400, message = "Введены не все обязательные параметры"),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 409, message = "Владелец с таким телефоном или почтой уже существует")
    })
    @PostMapping
    public ResponseEntity createOwner(@RequestBody OwnerEntity owner) {
        try {
            if((owner.getName() == null) ||
                    (owner.getSurname() == null) ||
                    (owner.getPatronymic() == null) ||
                    (owner.getEmail() == null) ||
                    (owner.getPhone() == null)){
                throw new RequiredParamNotEnteredException();
            }
            if(ownerService.exists(owner)){
                throw new OwnerAlreadyExistException();
            }
            return ResponseEntity.status(201).body(ownerService.create(owner));
        } catch (OwnerAlreadyExistException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (RequiredParamNotEnteredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ApiOperation("Обновление владельца по id")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "id владельца",
                    required = true, dataType = "Long"),
            @ApiImplicitParam(paramType = "body", name = "owner", value = "владелец",
                    required = true, dataType = "owner")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Данные владельца обновлены", response = OwnerEntity.class),
            @ApiResponse(code = 400, message = "Введены не все обязательные параметры"),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Владелец не найден")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateOwner(@PathVariable("id") Long id, @RequestBody OwnerEntity owner) {
        try {
            if((owner.getName() != null) &&
                    (owner.getSurname() != null) &&
                    (owner.getPatronymic() != null) &&
                    (owner.getEmail() != null) &&
                    (owner.getPhone() != null)){;
                return ResponseEntity.ok(ownerService.update(id, owner));
            }else{
                throw new RequiredParamNotEnteredException();
            }
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (RequiredParamNotEnteredException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("Удаление владельца по id")
    @ApiImplicitParam(paramType="path", name = "id", value = "id владельца",
            required = true, dataType = "Long")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Владелец удален", response = Long.class),
            @ApiResponse(code = 401, message = "Клиен не аутентифицирован"),
            @ApiResponse(code = 403, message = "Клиент не уполномочен делать этот запрос"),
            @ApiResponse(code = 404, message = "Владелец не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteOwner(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(ownerService.deleteOne(id));
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
