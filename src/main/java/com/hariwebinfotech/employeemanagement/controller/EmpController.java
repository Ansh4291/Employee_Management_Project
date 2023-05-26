package com.hariwebinfotech.employeemanagement.controller;

import com.hariwebinfotech.employeemanagement.dto.HttpResponse;
import com.hariwebinfotech.employeemanagement.entity.Employee;
import com.hariwebinfotech.employeemanagement.entity.Post;
import com.hariwebinfotech.employeemanagement.entity.Register;
import com.hariwebinfotech.employeemanagement.entity.Role;
import com.hariwebinfotech.employeemanagement.service.EmpService;
import com.hariwebinfotech.employeemanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @Autowired
    private RoleService roleService;


    @GetMapping("/Employee/getAllEmployee")
    public HttpResponse getAllEmployees() {
        List<Employee> employeeList = empService.getAllEmployee();
        if (employeeList.size() > 0) {
            return new HttpResponse((short) 200, "Data is found sucessfully", employeeList);
        } else {
            return new HttpResponse((short) 404, "Data is not found", null);
        }
    }

    @GetMapping("/Employee/getAllEmployee/{empId}")
    public HttpResponse getById(@PathVariable int empId) {
        Employee employee = empService.getEmpById(empId);
        if (employee != null) {
            return new HttpResponse((short) 200, "Employee found successfully", employee);
        } else {
            return new HttpResponse((short) 401, "Employee not found", null);
        }
    }

    @PostMapping("/Employee/addEmployee")
    public HttpResponse addAllEmployee(@RequestBody Employee employee) {

        if (employee.getEmpName() == null || employee.getEmpName().equals("")) {
            return new HttpResponse((short) 404, "Employee name is null", null);
        }
        if (employee.getEmpEmail() == null || employee.getEmpEmail().equals("")) {
            return new HttpResponse((short) 404, "Employee email is null", null);
        }
        if (employee.getEmpPassword() == null || employee.getEmpPassword().equals("")) {
            return new HttpResponse((short) 404, "Employee password is null", null);
        }

        for (Employee employee1 : empService.getAllEmployee()) {
            if (employee1.getEmpEmail().equalsIgnoreCase(employee.getEmpEmail())) {
                return new HttpResponse((short) 400, "Employee Email is already exists", null);
            }
        }
        Role role = roleService.getByRoleType(employee.getRoleType());
        if (role == null) {
            return new HttpResponse((short) 400, "Role is not mentioned in the role list", null);
        }
        Employee empList = empService.AddEmployee(employee);
        return new HttpResponse((short) 200, "Employee is successfully Added", empList);
    }

    @DeleteMapping("/Employee/deleteEmployee/{empId}")
    public HttpResponse deleteEmployee(@PathVariable int empId) {

        Employee emp = empService.getEmpById(empId);
        if (emp == null) {
            return new HttpResponse((short) 404, "Id not found ", emp);
        } else {
            empService.EmpDelete(empId);
            return new HttpResponse((short) 200, "Id is found data is deleted", emp);
        }
    }

    @PutMapping("/Employee/UpdateEmployee")
    public HttpResponse updateEmployee(@RequestBody Employee employee) {
        if (employee.getEmpName() == null || employee.getEmpName().equals("")) {
            return new HttpResponse((short) 404, "Employee name is null", null);
        }
        if (employee.getEmpEmail() == null || employee.getEmpEmail().equals("")) {
            return new HttpResponse((short) 404, "Employee email is null", null);
        }
        if (employee.getEmpPassword() == null || employee.getEmpPassword().equals("")) {
            return new HttpResponse((short) 404, "Employee password is null", null);
        }

        for (Employee employee1 : empService.getEmployeeDataExceptID(employee.getEmpId())) {
            if (employee1.getEmpEmail().equalsIgnoreCase(employee.getEmpEmail())) {
                return new HttpResponse((short) 400, "Employee Email is already exists", null);
            }
        }
        Role role = roleService.getByRoleType(employee.getRoleType());
        if (role == null) {
            return new HttpResponse((short) 400, "Role is not mentioned in the role list", null);
        }
        Employee emp = empService.getEmpById(employee.getEmpId());
        if (emp == null) {
            return new HttpResponse((short) 404, "Data not found ", emp);
        }else {
            Employee empList = empService.updateEmployee(employee);
            return new HttpResponse((short) 200, "Employee is successfully updated", empList);
        }

    }
}