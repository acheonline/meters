package ru.achernyavskiy0n.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.achernyavskiy0n.model.Meter;

@Repository
public interface MeterRepository extends CrudRepository<Meter, String> {
}
