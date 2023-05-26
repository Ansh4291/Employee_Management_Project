package com.hariwebinfotech.employeemanagement.repository;

import com.hariwebinfotech.employeemanagement.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RegRepo extends JpaRepository<Register, Integer> {
    @Query(value = "select * from registration where reg_id!= :reg_id", nativeQuery = true)
    public List<Register> getRegisterDataExceptID(@Param("reg_id") Integer id);
}
