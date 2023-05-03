package com.jaya.api.controller;

import com.jaya.api.common.ApiResponse;
import com.jaya.api.dto.UserDTO;
import com.jaya.api.dto.WishlistDTO;
import com.jaya.api.model.User;
import com.jaya.api.repository.IUserRepository;
import com.jaya.api.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> listAll(){
        return ResponseEntity.ok(new ApiResponse<List<User>>(this.userService.listAll()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<User>> findById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(new ApiResponse<User>(this.userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> add(@RequestBody @Valid UserDTO data, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = new ArrayList<String>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new ApiResponse<User>(errors));
        }
        return ResponseEntity.ok(new ApiResponse<User>(this.userService.add(data)));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<User>> update(@PathVariable(name = "id") String id, @RequestBody @Valid  User data, BindingResult result) {
        if(result.hasErrors()){
            List<String> errors = new ArrayList<String>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(new ApiResponse<User>(errors));
        }

        data.setId(id);
        return ResponseEntity.ok(new ApiResponse<User>(this.userService.update(data)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse<Integer>> delete(@PathVariable(name = "id") String id){
        this.userService.delete(id);
        return ResponseEntity.ok(new ApiResponse<Integer>(1));
    }
}
