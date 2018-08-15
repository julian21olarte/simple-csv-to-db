package com.mapcsv.mapcsv.services;

import com.mapcsv.mapcsv.entities.Data;
import com.mapcsv.mapcsv.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class TaskProcessorImpl implements TaskProcessor {

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void processData(List<Data> listData) {
        this.dataRepository.saveAll(listData);
    }

    @Transactional
    public void processDataWithBatch(List<Data> listData) {
        long count = 0;
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for(Data data: listData) {
            entityManager.persist(data);
            count++;
            if(count == 50) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Transactional
    public void processDataWithLoadDataInfile(String filePath) {
        System.out.println(filePath);
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String query =
                "LOAD DATA INFILE '" + filePath.replace("\\", "\\\\") + "'" +
                "INTO TABLE data " +
                "CHARACTER SET UTF8 " +
                "FIELDS TERMINATED BY ',' " +
                "ENCLOSED BY '\"' " +
                "LINES TERMINATED BY '\\r\\n' " +
                "IGNORE 1 LINES " +
                "(city, name, street_address, locality, address_region, postal_code, phone, website) " +
                "SET id = NULL";
        entityManager.createNativeQuery(query).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
