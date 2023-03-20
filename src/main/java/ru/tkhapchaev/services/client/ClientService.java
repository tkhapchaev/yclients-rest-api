package ru.tkhapchaev.services.client;

import ru.tkhapchaev.entities.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    void create(Client client);

    List<Client> readAll();

    Client read(long id);

    boolean update(Client client, long id);

    boolean delete(long id);

    boolean existsById(long id);

    List<Client> findByYclientsActivityId(long id);

    boolean existsByYClientsId(long id);

    Client findByYclientsId(long id);
}