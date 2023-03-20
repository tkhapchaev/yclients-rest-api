package ru.tkhapchaev.services.activity;

import ru.tkhapchaev.entities.Activity;
import ru.tkhapchaev.repositories.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void create(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public List<Activity> readAll() {
        return activityRepository.findAll();
    }

    @Override
    public Activity read(long id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Activity activity, long id) {
        if (activityRepository.existsById(id)) {
            activity.setId(id);
            activityRepository.save(activity);

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(long id) {
        if (activityRepository.existsById(id)) {
            activityRepository.deleteById(id);

            return true;
        }

        return false;
    }

    @Override
    public boolean existsById(long id) {
        return activityRepository.existsById(id);
    }

    @Override
    public boolean existsByYClientsId(long id) {
        return activityRepository.existsByYclientsId(id);
    }

    @Override
    public Activity findByYclientsId(long id) {
        return activityRepository.findByYclientsId(id);
    }
}