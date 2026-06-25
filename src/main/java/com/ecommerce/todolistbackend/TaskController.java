package com.ecommerce.todolistbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

@CrossOrigin(origins = "https://todoapp-git-main-akiransrisai-8374s-projects.vercel.app")
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

//    List<Tasks> tasks =  new ArrayList<>(List.of(
//            new Tasks(1,"eat"),
//            new Tasks(2,"sleep"),
//            new Tasks(3,"code"),
//            new Tasks(4,"wash dishes"),
//            new Tasks(5,"bath")
//    ));

    @GetMapping("{id}")
    public List<TasksResponse> getAllTasks(@PathVariable("id") Integer userId){
        return service.getAllTasks(userId);
    }

    @PostMapping("/{id}")
    public void addTask(@PathVariable("id") Integer userId,@RequestBody TaskRequest request){
        service.addTask(userId,request);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Integer taskId,@RequestHeader("userId") Integer userId){
        service.deleteTask(taskId,userId);

    }
}
