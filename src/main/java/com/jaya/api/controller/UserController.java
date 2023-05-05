package com.jaya.api.controller;

import com.jaya.api.domain.dto.UserDTO;
import com.jaya.api.domain.dto.UserUpdateDTO;
import com.jaya.api.domain.model.User;
import com.jaya.api.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @GetMapping
    public ResponseEntity<Page<User>> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        return ResponseEntity.ok(this.userService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(this.userService.findById(id));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody @Valid UserDTO data, UriComponentsBuilder uriComponentsBuilder){
        var user = this.userService.add(data);
        var uri = uriComponentsBuilder.path("api/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid UserUpdateDTO data){
        var user = this.userService.findById(data.id());
        user.userUpdate(data);
        return ResponseEntity.ok(this.userService.update(user));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Integer> delete(@PathVariable(name = "id") String id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
