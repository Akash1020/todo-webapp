package edu.aam.app.service.task;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.repository.TaskRepository;
import edu.aam.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getOne(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(TaskViewModel taskViewModel, String username) {

        Task task = new Task();
        task.setDescription(taskViewModel.getDescription());
        task.setTaskName(taskViewModel.getTaskName());
        task.setUser(userRepository.findByEmail(username));
        return save(task);
    }

    private Task save(Task task) {
        Task saveTask = taskRepository.saveAndFlush(task);
        return saveTask;
    }

    @Override
    public Task getTaskByUserName(Long id, String username) {
        Task task = taskRepository.findTaskById(id, username);
        try {
            task.setTodoList(task.getTodoList().stream()
                    .sorted(Comparator.comparing(Todo::getTargetDate).reversed())
                    .sorted(Comparator.comparing(Todo::getStatus))
                    .collect(Collectors.toList()));
        } catch (NullPointerException e) {
            log.info(e.getLocalizedMessage());
        }
        return task;
    }

    @Override
    public Task putTask(Long id, TaskViewModel taskViewModel) {
        Task task = taskRepository.getOne(id);
        if(task == null) {
            return null;
        }
        task.setTaskName(taskViewModel.getTaskName());
        task.setDescription(taskViewModel.getDescription());

        Task saveTask = taskRepository.save(task);
        return saveTask;
    }

    @Override
    public TaskViewModel mapTaskViewModel(Long taskId, String username) {
        Task task = taskRepository.findTaskById(taskId, username);
        if(task != null) {
            TaskViewModel taskViewModel = new TaskViewModel(task.getId(), task.getTaskName(), task.getDescription());
            return taskViewModel;
        }
        return null;
    }
}
