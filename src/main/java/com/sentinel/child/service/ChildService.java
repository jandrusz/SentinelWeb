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
    public Child saveChild(Child child) {
        return Objects.isNull(getChildByLoginAndPassword(child)) ? childRepository.save(child) : null;
    }

    public Child getChildByIdChild(Integer idChild) {
        return childRepository.getChildByIdChild(idChild);
    }

    public Child getChildByLoginAndPassword(Child child) {
        return validateCredentials(child) ? childRepository.getChildByLoginAndPassword(child.getLogin(), child.getPassword()) : null;
    }

    public boolean setSchedule(Integer idSchedule, Integer idChild) {
        return childRepository.setSchedule(idSchedule, idChild) != 0;
    }

    private boolean validateCredentials(Child child) {
        return Objects.nonNull(child.getLogin()) && Objects.nonNull(child.getPassword());
    }

}
