package com.hariwebinfotech.employeemanagement.controller;

import com.hariwebinfotech.employeemanagement.dto.HttpResponse;
import com.hariwebinfotech.employeemanagement.entity.Register;
import com.hariwebinfotech.employeemanagement.entity.Role;
import com.hariwebinfotech.employeemanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/Role/GetAllRole")
    public HttpResponse getAllRole(){
       List<Role> role =  roleService.getAllRole();
        if (role.size() > 0){
            return new  HttpResponse ((short) 200, "Role found successfully" , role);
        }else {
            return new HttpResponse((short) 401, "role not found" , null);
        }
    }
    @GetMapping("/Role/GetRoleByID/{roleId}")
    public HttpResponse getByRoleId(@PathVariable int roleId){
        Role role =  roleService.getByRoleId(roleId);
        if (role != null){
            return new HttpResponse((short) 200, "Role found successfully" , role);
        }else {
            return new HttpResponse((short) 401, "Role not found" , null);
        }

    }

    @PostMapping("/Role/AddRole")
    public HttpResponse addRole(@RequestBody Role role) {
        if (role.getRoleType() == null || role.getRoleType().equals("")) {
            return new HttpResponse((short) 400, "Role type is null", null);
        }
        if (role.getAuthority() == null || role.getAuthority().equals("")) {
            return new HttpResponse((short) 400, "Role Authority is null", null);
        }
        for (Role role1 : roleService.getAllRole()) {
            if (role1.getRoleType().equalsIgnoreCase(role.getRoleType())) {
                return new HttpResponse((short) 400, "Role type is already exists", null);
            }
        }
            Role roleList = roleService.addRole(role);
        return new HttpResponse((short) 200, "Role is created successfully", roleList);
    }
    @DeleteMapping("/Role/DeleteRole/{roleId}")
    public HttpResponse deleteRole(@PathVariable int roleId){
        Role role = roleService.getByRoleId(roleId);
        if (role == null){
            return new HttpResponse((short) 401, "Id not found", null);
        }else {
            roleService.deleteRole(roleId);
            return new HttpResponse((short) 200, "Id is found data is deleted", role);
        }
    }

    @PutMapping("/Role/UpdateRole")
    public HttpResponse updateRole(@RequestBody Role role){

        if (role.getRoleType() == null || role.getRoleType().equals("")) {
            return new HttpResponse((short) 400, "Role type is null", null);
        }
        if (role.getAuthority() == null || role.getAuthority().equals("")) {
            return new HttpResponse((short) 400, "ROle Authority is null", null);
        } if (role.getRoleId() == null || role.getRoleId().equals("")) {
            return new HttpResponse((short) 400, "Role Id is null", null);
        }
        for (Role role1 : roleService.getRoleDataExceptID(role.getRoleId())) {
            if (role1.getRoleType().equalsIgnoreCase(role.getRoleType())) {
                return new HttpResponse((short) 400, "Role type is already exists", null);
            }
        }
        Role roleData = roleService.getByRoleId(role.getRoleId());
        if (roleData == null){
            return new HttpResponse((short) 401, "Data not found", null);
        }else {
            Role roleList = roleService.updateRole(role);
            return new HttpResponse((short) 200, "Role is Updated successfully", roleList);
        }

    }
}
