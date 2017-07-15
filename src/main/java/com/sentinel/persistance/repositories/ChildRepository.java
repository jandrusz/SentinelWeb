package com.sentinel.persistance.repositories;

import com.sentinel.persistance.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ChildRepository extends JpaRepository<Child, Integer> {

    Child getChildByIdChild(Integer idChild);

    Child getChildByLoginAndPassword(String login, String password);

    @Override
    Child save(Child child);

}
