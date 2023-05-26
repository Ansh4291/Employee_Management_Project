package com.hariwebinfotech.employeemanagement.repository;

import com.hariwebinfotech.employeemanagement.entity.Employee;
import com.hariwebinfotech.employeemanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "select * from employee where emp_id!= :emp_id",nativeQuery = true)
    public List<Employee> getEmployeeDataExceptID(@Param("emp_id") Integer id);
}
