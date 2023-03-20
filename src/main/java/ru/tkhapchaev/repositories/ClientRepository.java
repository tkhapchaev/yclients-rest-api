package ru.tkhapchaev.repositories;

import ru.tkhapchaev.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByYclientsActivityId(long id);

    boolean existsByYclientsId(long id);

    Client findByYclientsId(long id);
}