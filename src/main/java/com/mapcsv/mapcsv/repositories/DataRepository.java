package com.mapcsv.mapcsv.repositories;

import com.mapcsv.mapcsv.entities.Data;
import org.springframework.data.repository.CrudRepository;

public interface DataRepository extends CrudRepository<Data, Long> {
}
