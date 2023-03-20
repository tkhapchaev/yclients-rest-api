package ru.tkhapchaev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tkhapchaev.entities.Result;
import ru.tkhapchaev.services.result.ResultService;

import java.util.List;

@CrossOrigin
@RestController
public class ResultController {
    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping(value = "/api/results")
    public ResponseEntity<?> create(@RequestBody Result result) {
        resultService.create(result);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/results")
    public ResponseEntity<List<Result>> readAll() {
        final List<Result> results = resultService.readAll();

        return results != null && !results.isEmpty() ? new ResponseEntity<>(results, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/results/{id}")
    public ResponseEntity<Result> read(@PathVariable(name = "id") long id) {
        final Result result = resultService.read(id);

        return result != null ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/results/activity/{id}")
    public ResponseEntity<List<Result>> findByYclientsActivityId(@PathVariable(name = "id") long id) {
        final List<Result> results = resultService.findByYclientsActivityId(id);

        return results != null && !results.isEmpty() ? new ResponseEntity<>(results, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/results/client/{id}")
    public ResponseEntity<List<Result>> findByYclientsClientId(@PathVariable(name = "id") long id) {
        final List<Result> results = resultService.findByYclientsClientId(id);

        return results != null && !results.isEmpty() ? new ResponseEntity<>(results, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/api/results/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody Result result) {
        final boolean updated = resultService.update(result, id);

        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/results/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        final boolean deleted = resultService.delete(id);

        return deleted ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}