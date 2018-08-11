package com.mapcsv.mapcsv.services.ImplOpenCsvAndHibernate;

import com.mapcsv.mapcsv.entities.Data;
import com.mapcsv.mapcsv.repositories.DataRepository;
import com.mapcsv.mapcsv.services.TaskProcessor;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class TaskProcessorImpl implements TaskProcessor {

    @Autowired
    private DataRepository dataRepository;

    @Override
    public List<Data> readFile(String fileName) throws FileNotFoundException {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        String [] columns = new String [] {"city", "name", "streetAddress", "locality", "addressRegion", "postalCode", "phone", "website"};

        strategy.setColumnMapping(columns);
        strategy.setType(Data.class);

        FileReader file = new FileReader(fileName);
        System.out.println(fileName);
        CSVReader reader = new CSVReader(file);
        try {
            reader.skip(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CsvToBean<Data> csvToBean = new CsvToBean<>();
        return csvToBean.parse(strategy, reader);
    }

    @Override
    public void processData(List<Data> listData) {
        this.dataRepository.saveAll(listData);
    }
}
