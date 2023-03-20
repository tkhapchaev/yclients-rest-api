package ru.tkhapchaev.repositories;

import ru.tkhapchaev.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    boolean existsByYclientsId(long id);

    Activity findByYclientsId(long id);
}