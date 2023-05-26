package com.hariwebinfotech.employeemanagement.repository;

import com.hariwebinfotech.employeemanagement.entity.Register;
import com.hariwebinfotech.employeemanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
    @Query(value = "SELECT * FROM role WHERE role_type = :role_type", nativeQuery = true)
    Role findByRoleType(@Param("role_type") String roleType);

    @Query(value = "select * from role where role_id!= :role_id", nativeQuery = true)
    public List<Role> getRoleDataExceptID(@Param("role_id") Integer id);
}
