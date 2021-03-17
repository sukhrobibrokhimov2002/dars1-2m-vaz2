package uz.pdp.lesson1vaz2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1vaz2.entity.Example;
import uz.pdp.lesson1vaz2.entity.Task;
import uz.pdp.lesson1vaz2.payload.ApiResult;
import uz.pdp.lesson1vaz2.payload.ExampleDto;
import uz.pdp.lesson1vaz2.repository.ExampleRepository;
import uz.pdp.lesson1vaz2.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;
    @Autowired
    TaskRepository taskRepository;

    public ApiResult add(ExampleDto exampleDto) {
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent()) return new ApiResult("Task not found", false);
        Example example = new Example();
        example.setTask(optionalTask.get());
        example.setText(exampleDto.getText());
        exampleRepository.save(example);
        return new ApiResult("Successfully added", true);
    }

    public List<Example> getAll() {
        List<Example> all = exampleRepository.findAll();
        return all;
    }

    public Example getOneById(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent()) return null;
        return optionalExample.get();
    }

    public ApiResult delete(Integer id) {
        try {
            exampleRepository.deleteById(id);
            return new ApiResult("Successfully deleted", true);
        } catch (Exception r) {
            return new ApiResult("Error in deleting", false);
        }
    }

    public ApiResult edit(Integer id, ExampleDto exampleDto) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent()) return new ApiResult("Example not found", false);
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResult("Task not found", false);
        Example example = optionalExample.get();

        example.setText(exampleDto.getText());
        example.setTask(optionalTask.get());
        exampleRepository.save(example);
        return new ApiResult("Successfully edited", true);


    }
}
