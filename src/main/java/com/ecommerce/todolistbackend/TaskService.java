package com.ecommerce.todolistbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TasksRepo tasksRepo;
    private final UserRepo userRepo;

    public void addTask(Integer userId, TaskRequest request) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found with id : " + userId));
        Tasks task = new Tasks();
        task.setName(request.name());
        task.setUser(user);
        tasksRepo.save(task);
    }

    public List<TasksResponse> getAllTasks(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found with id : " + userId));
        return  tasksRepo.findByUserId(user.getId())
                .stream()
                .map(this::mapToTaskResponse)
                .toList();
    }

    private TasksResponse mapToTaskResponse(Tasks tasks) {
        return new TasksResponse(tasks.getId(), tasks.getName());
    }

    @Transactional
    public void deleteTask(Integer taskId, Integer userId) {
        tasksRepo.deleteByIdAndUserId(taskId,userId);
    }
}
