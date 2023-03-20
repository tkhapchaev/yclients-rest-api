package ru.tkhapchaev.controllers;

import ru.tkhapchaev.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tkhapchaev.services.client.ClientService;

import java.util.List;

@CrossOrigin
@RestController
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/api/clients")
    public ResponseEntity<?> create(@RequestBody Client client) {
        clientService.create(client);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/clients")
    public ResponseEntity<List<Client>> readAll() {
        final List<Client> clients = clientService.readAll();

        return clients != null && !clients.isEmpty() ? new ResponseEntity<>(clients, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") long id) {
        final Client client = clientService.read(id);

        return client != null ? new ResponseEntity<>(client, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/clients/activity/{id}")
    public ResponseEntity<List<Client>> findByYclientsActivityId(@PathVariable(name = "id") long id) {
        final List<Client> clients = clientService.findByYclientsActivityId(id);

        return clients != null && !clients.isEmpty() ? new ResponseEntity<>(clients, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/api/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody Client client) {
        final boolean updated = clientService.update(client, id);

        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/api/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        final boolean deleted = clientService.delete(id);

        return deleted ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}