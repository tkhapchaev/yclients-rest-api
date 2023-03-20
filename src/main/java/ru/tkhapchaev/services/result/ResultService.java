package ru.tkhapchaev.services.result;

import ru.tkhapchaev.entities.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultService {
    void create(Result result);

    List<Result> readAll();

    Result read(long id);

    boolean update(Result result, long id);

    boolean delete(long id);

    boolean existsById(long id);

    List<Result> findByYclientsActivityId(long id);

    List<Result> findByYclientsClientId(long id);
}