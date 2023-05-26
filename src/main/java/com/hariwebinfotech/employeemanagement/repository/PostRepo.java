package com.hariwebinfotech.employeemanagement.repository;

import com.hariwebinfotech.employeemanagement.entity.Post;
import com.hariwebinfotech.employeemanagement.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    @Query(value = "select * from post where post_id!= :post_id", nativeQuery = true)
    public List<Post> getPostDataExceptID(@Param("post_id") Integer id);
}
