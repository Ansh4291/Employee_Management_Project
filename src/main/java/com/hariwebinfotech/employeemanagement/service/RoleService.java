package com.hariwebinfotech.employeemanagement.service;

import com.hariwebinfotech.employeemanagement.entity.Role;
import com.hariwebinfotech.employeemanagement.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    public Role addRole(Role role){
        return roleRepo.save(role);
    }
    public Role getByRoleType(String roleType){
        return roleRepo.findByRoleType(roleType);
    }

    public List<Role> getAllRole(){
        return roleRepo.findAll();
    }
    public Role getByRoleId(int roleId){
        return roleRepo.findById(roleId).orElse(null);
    }

    public List<Role> getRoleDataExceptID(Integer roleId){
        return roleRepo.getRoleDataExceptID(roleId);
    }
    public void deleteRole(int roleId){
        roleRepo.deleteById(roleId);
    }

    public Role updateRole(Role role){
        Role existingRole = roleRepo.findById(role.getRoleId()).orElse(null);
        existingRole.setRoleType(role.getRoleType());
        existingRole.setAuthority(role.getAuthority());
        return roleRepo.save(existingRole);
    }
}
