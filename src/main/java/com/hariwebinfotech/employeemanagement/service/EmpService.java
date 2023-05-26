package com.hariwebinfotech.employeemanagement.service;

import com.hariwebinfotech.employeemanagement.entity.Employee;
import com.hariwebinfotech.employeemanagement.entity.Role;
import com.hariwebinfotech.employeemanagement.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired
  private EmpRepository empRepository;

    public List<Employee> getAllEmployee(){
        return empRepository.findAll();
    }

    public Employee AddEmployee(Employee employee){
        return empRepository.save(employee);
    }

    public String EmpDelete(int empId){
       empRepository.deleteById(empId);
       return "";
    }
    public Employee getEmpById(int EId){
      return empRepository.findById(EId).orElse(null);
    }

    public List<Employee> getEmployeeDataExceptID(Integer empid){
        return empRepository.getEmployeeDataExceptID(empid);
    }

    public Employee updateEmployee(Employee employee){
        Employee existingEmp = empRepository.findById(employee.getEmpId()).orElse(null);
        existingEmp.setEmpId(employee.getEmpId());
        existingEmp.setEmpName(employee.getEmpName());
        existingEmp.setEmpEmail(employee.getEmpEmail());
        existingEmp.setEmpPassword(employee.getEmpPassword());
        return empRepository.save(existingEmp);
    }
}
