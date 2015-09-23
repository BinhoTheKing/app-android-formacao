package br.com.cast.turmaformacao.mytaskmanager.model.services;

import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.model.entities.User;
import br.com.cast.turmaformacao.mytaskmanager.model.persistence.UserRepository;

/**
 * Created by Administrador on 23/09/2015.
 */
public class UserBusinessService {
    private UserBusinessService() {
        super();
    }

    public static List<User> findAll() {
        return UserRepository.getAll();
    }

    public static void save(User user) {
        UserRepository.save(user);
    }

    public static void delete(User selectedUser) {
        UserRepository.delete(selectedUser.getId());
    }

    public static void update(User selectedUser) {
        UserRepository.save(selectedUser);
    }

    public static User getById(long id) {
        return UserRepository.getById(id);
    }
}
