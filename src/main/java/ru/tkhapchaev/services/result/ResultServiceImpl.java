package ru.tkhapchaev.services.result;

import ru.tkhapchaev.entities.Result;
import ru.tkhapchaev.repositories.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;

    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public void create(Result result) {
        resultRepository.save(result);
    }

    @Override
    public List<Result> readAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result read(long id) {
        return resultRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Result result, long id) {
        if (resultRepository.existsById(id)) {
            result.setId(id);
            resultRepository.save(result);

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(long id) {
        if (resultRepository.existsById(id)) {
            resultRepository.deleteById(id);

            return true;
        }

        return false;
    }

    @Override
    public boolean existsById(long id) {
        return resultRepository.existsById(id);
    }

    @Override
    public List<Result> findByYclientsActivityId(long id) {
        return resultRepository.findByYclientsActivityId(id);
    }

    @Override
    public List<Result> findByYclientsClientId(long id) {
        return resultRepository.findByYclientsClientId(id);
    }
}