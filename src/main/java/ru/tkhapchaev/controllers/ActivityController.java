package ru.tkhapchaev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tkhapchaev.entities.Activity;
import ru.tkhapchaev.services.activity.ActivityService;

import java.util.List;


@CrossOrigin
@RestController
public class ActivityController {
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping(value = "/api/activities")
    public ResponseEntity<?> create(@RequestBody Activity activity) {
        activityService.create(activity);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/activities")
    public ResponseEntity<List<Activity>> readAll() {
        final List<Activity> activities = activityService.readAll();

        return activities != null && !activities.isEmpty() ? new ResponseEntity<>(activities, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/activities/{id}")
    public ResponseEntity<Activity> read(@PathVariable(name = "id") long id) {
        final Activity activity = activityService.read(id);

        return activity != null ? new ResponseEntity<>(activity, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/api/activities/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody Activity activity) {
        final boolean updated = activityService.update(activity, id);

        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/activities/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        final boolean deleted = activityService.delete(id);

        return deleted ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}