package com.sentinel.persistance.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.sentinel.persistance.domain.Child;

@Transactional
public interface ChildRepository extends JpaRepository<Child, Integer> {

	Child getChildByIdChild(Integer idChild);

	Child getChildByLoginAndPassword(String login, String password);

	@Override
	Child save(Child child);

	@Modifying
	@Query("UPDATE children c SET c.idSchedule = ?1 WHERE c.idChild= ?2")
	Integer setSchedule(Integer idSchedule, Integer idChild);

}
