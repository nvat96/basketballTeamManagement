package com.axonactive.basketball.apis;

import com.axonactive.basketball.services.dtos.UserDTO;
import com.axonactive.basketball.services.impl.UserServiceImpl;
import com.axonactive.basketball.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UserResources.PATH)
public class UserResources {
    public static final String PATH = "/api/user";
    @Autowired
    UserServiceImpl userService;
    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(UserMapper.INSTANCE.toDTOs(userService.findAll()));
    }
}
