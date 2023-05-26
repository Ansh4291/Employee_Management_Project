package com.hariwebinfotech.employeemanagement.service;

import com.hariwebinfotech.employeemanagement.entity.Register;
import com.hariwebinfotech.employeemanagement.repository.RegRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegService {
    @Autowired
    private RegRepo regRepo;

    public List<Register> getAllRegistration(){
        return regRepo.findAll();
    }

    public Register getById(int regId){
        return regRepo.findById(regId).orElse(null);
    }

    public Register addRegistration(Register register){
        return regRepo.save(register);
    }

    public String deleteRegistration(int regId){
        regRepo.deleteById(regId);
        return "";
    }

    public Register updateRegistration(Register register){
        Register existingRegister = regRepo.findById(register.getRegId()).orElse(null);
        existingRegister.setUsername(register.getUsername());
        existingRegister.setEmail_id(register.getEmail_id());
        existingRegister.setPassword(register.getPassword());
        existingRegister.setRe_password(register.getRe_password());
        existingRegister.setRole(register.getRole());
        return regRepo.save(existingRegister);
    }

    public List<Register> getRegisterDataExceptID(Integer id){
        return regRepo.getRegisterDataExceptID(id);
    }
}
