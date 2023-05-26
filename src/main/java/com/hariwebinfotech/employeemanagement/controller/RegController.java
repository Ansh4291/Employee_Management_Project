package com.hariwebinfotech.employeemanagement.controller;

import com.hariwebinfotech.employeemanagement.dto.HttpResponse;
import com.hariwebinfotech.employeemanagement.entity.Register;
import com.hariwebinfotech.employeemanagement.entity.Role;
import com.hariwebinfotech.employeemanagement.service.RegService;
import com.hariwebinfotech.employeemanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegController {
    @Autowired
    private RegService regService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/Register/GetAllRegistration")
    public HttpResponse getAllRegistrations(){
        List<Register> registers = regService.getAllRegistration();
        if (registers.size() > 0){
            return new HttpResponse((short) 200, "Registration Details found successfully" , registers);
        }else {
            return new HttpResponse((short) 401, "Registration Details not found" , null);
        }
    }

    @GetMapping("/Register/GetAllById/{regId}")
    public HttpResponse getAllById(@PathVariable int regId){
   Register registers=  regService.getById(regId);
        if (registers != null){
            return new HttpResponse((short) 200, "Registration Details found successfully" , registers);
        }else {
            return new HttpResponse((short) 401, "Registration Details not found" , null);
        }

    }

    @PostMapping("/Register/AddRegistration")
    public HttpResponse addRegistration(@RequestBody Register register1){

           if (register1.getUsername() == null || register1.getUsername().equals("")){
               return new HttpResponse((short) 400, "Username is null" , null);
           }if (register1.getPassword() == null || register1.getPassword().equals("")){
               return new HttpResponse((short) 400, "Password is null" , null);
           }if (register1.getRole() == null || register1.getRole().equals("")){
               return new HttpResponse((short) 400, "Role is null" , null);
           }if (register1.getEmail_id() == null || register1.getEmail_id().equals("")){
               return new HttpResponse((short) 400, "Email id is null" , null);
           }
       for (Register register2 : regService.getAllRegistration() ){
           if (register2.getUsername().equalsIgnoreCase(register1.getUsername())){
               return new HttpResponse((short) 400, "Username is already exists" , null);
           }
           if (register2.getEmail_id().equalsIgnoreCase(register1.getEmail_id())){
               return new HttpResponse((short) 400, "Email is already exists" , null);
           }
       }
       if (! register1.getPassword().equals(register1.getRe_password())){
           return new HttpResponse((short) 400, "Password is not match with Re_Password" , null);
       }
       Role role = roleService.getByRoleType(register1.getRole());
       if (role == null){
           return new HttpResponse((short) 400, "Role is not mentioned in the role list" , null);
       }
        Register registerList = regService.addRegistration(register1);
        return new HttpResponse((short) 200, "Registration is successfully done", registerList);
    }

    @DeleteMapping("/Register/DeleteRegistration/{regId}")
    public HttpResponse deleteRegistration(@PathVariable int regId){
        Register reg = regService.getById(regId);
        if (reg == null){
            return new HttpResponse((short) 401, "Id not found", null);
        }else {
            regService.deleteRegistration(regId);
            return new HttpResponse((short) 200, "Id is found data is deleted", reg);
        }
    }

//    @PutMapping("/Register/UpdateRegistration")
//    public RegDto updateRegistration(@RequestBody Register register1){
//        if (register1.getUsername() == null || register1.getUsername().equals("")){
//            return new RegDto((short) 400, "Username is null" , null);
//        }if (register1.getPassword() == null || register1.getPassword().equals("")){
//            return new RegDto((short) 400, "Password is null" , null);
//        }if (register1.getRole() == null || register1.getRole().equals("")){
//            return new RegDto((short) 400, "Role is null" , null);
//        }if (register1.getEmail_id() == null || register1.getEmail_id().equals("")){
//            return new RegDto((short) 400, "Email id is null" , null);
//        }if (register1.getRegId() == null){
//            return new RegDto((short) 400, "Reg Id is null" , null);
//        }
//        for (Register register2 : regService.getRegisterDataExceptID(register1.getRegId()) ){
//            if (register2.getUsername().equalsIgnoreCase(register1.getUsername())){
//                return new RegDto((short) 400, "Username is already exists" , null);
//            }
//            if (register2.getEmail_id().equalsIgnoreCase(register1.getEmail_id())){
//                return new RegDto((short) 400, "Email is already exists" , null);
//            }
//        }
//        if (! register1.getPassword().equals(register1.getRe_password())){
//            return new RegDto((short) 400, "Password is not match with Re_Password" , null);
//        }
//        Role role = roleService.getByRoleType(register1.getRole());
//        if (role == null){
//            return new RegDto((short) 400, "Role is not mentioned in the role list" , null);
//        }
//        Register reg = regService.getById(register1.getRegId());
//        if (reg == null){
//            return new RegDto((short) 401, "Data not found", null);
//        }else{
//            regService.updateRegistration(register1);
//            return new RegDto((short) 200, "Data is updated", reg);
//        }
//    }
}
