package br.com.cast.turmaformacao.mytaskmanager.model.services;

import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.model.entities.Task;
import br.com.cast.turmaformacao.mytaskmanager.model.persistence.TaskRepository;

public final class TaskBusinessService {
    private TaskBusinessService() {
        super();
    }

    public static List<Task> findAll() {
        return TaskRepository.getAll();
    }

    public static List<Task> findUsersAll(Long userId){
        return TaskRepository.getAllByUserId(userId);
    }

    public static void save(Task task) {
        TaskRepository.save(task);
    }

    public static void delete(Task selectedTask) {
        TaskRepository.delete(selectedTask.getId());
    }

    public static void update(Task selectedTask) {
        TaskRepository.save(selectedTask);
    }
}
