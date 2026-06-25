package com.ecommerce.todolistbackend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepo extends JpaRepository<Tasks,Integer> {
    List<Tasks> findByUserId(Integer id);

    void deleteByIdAndUserId(Integer taskId, Integer userId);
}
