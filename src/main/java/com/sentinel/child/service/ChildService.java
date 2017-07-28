package com.sentinel.child.service;

import com.sentinel.persistance.domain.Child;
import com.sentinel.persistance.repositories.ChildRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Objects;

@Component
public class ChildService {

    private ChildRepository childRepository;

    @Inject
    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Transactional
    public boolean saveChild(Child child) {
        if (Objects.isNull(getChildByLoginAndPassword(child))) {
            childRepository.save(child);
            return true;
        }
        return false;
    }

    public Child getChildByIdChild(Integer idChild) {
        return childRepository.getChildByIdChild(idChild);
    }

    public Child getChildByLoginAndPassword(Child child) {
        if (validateCredentials(child)) {
            return childRepository.getChildByLoginAndPassword(child.getLogin(), child.getPassword());
        }
        return null;
    }

    public boolean setSchedule(Integer idSchedule, Integer idChild) {
        return childRepository.setSchedule(idSchedule, idChild) != 0;
    }

    private boolean validateCredentials(Child child) {
        return Objects.nonNull(child.getLogin()) && Objects.nonNull(child.getPassword());
    }

}
