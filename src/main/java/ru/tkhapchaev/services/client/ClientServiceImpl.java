package ru.tkhapchaev.services.client;

import ru.tkhapchaev.entities.Client;
import ru.tkhapchaev.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client read(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Client client, long id) {
        if (clientRepository.existsById(id)) {
            client.setId(id);
            clientRepository.save(client);

            return true;
        }

        return false;
    }

    @Override
    public boolean delete(long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);

            return true;
        }

        return false;
    }

    @Override
    public boolean existsById(long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public List<Client> findByYclientsActivityId(long id) {
        return clientRepository.findByYclientsActivityId(id);
    }

    @Override
    public boolean existsByYClientsId(long id) {
        return clientRepository.existsByYclientsId(id);
    }

    @Override
    public Client findByYclientsId(long id) {
        return clientRepository.findByYclientsId(id);
    }
}