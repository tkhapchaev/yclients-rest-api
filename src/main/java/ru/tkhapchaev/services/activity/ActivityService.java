package ru.tkhapchaev.services.activity;

import ru.tkhapchaev.entities.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityService {
    void create(Activity activity);

    List<Activity> readAll();

    Activity read(long id);

    boolean update(Activity activity, long id);

    boolean delete(long id);

    boolean existsById(long id);

    boolean existsByYClientsId(long id);

    Activity findByYclientsId(long id);
}