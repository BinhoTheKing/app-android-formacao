package br.com.cast.turmaformacao.mytaskmanager.model.services;

import java.util.List;

import br.com.cast.turmaformacao.mytaskmanager.model.entities.Label;
import br.com.cast.turmaformacao.mytaskmanager.model.persistence.LabelRepository;

public final class LabelBusinessService {
    private LabelBusinessService() {
        super();
    }

    public static List<Label> findAll() {
        return LabelRepository.getAll();
    }

    public static void save(Label label) {
        LabelRepository.save(label);
    }

    public static void delete(Label selectedLabel) {
        LabelRepository.delete(selectedLabel.getId());
    }

    public static void update(Label selectedLabel) {
        LabelRepository.save(selectedLabel);
    }

    public static Label getById(long id) {
        return LabelRepository.getById(id);
    }
}
