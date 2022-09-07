package com.sergax.courseio.controller;

import com.sergax.courseio.entity.Role;
import com.sergax.courseio.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleRestController {
    private final RoleService roleService;

    @GetMapping()
    public ResponseEntity<Set<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable String id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.create(role));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable String roleId,
                                        @RequestBody Role role) {
        return ResponseEntity.ok(roleService.update(roleId, role));
    }

}
