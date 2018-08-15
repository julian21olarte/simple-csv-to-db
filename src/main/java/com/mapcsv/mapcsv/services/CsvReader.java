package com.mapcsv.mapcsv.services;

import com.mapcsv.mapcsv.entities.Data;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class CsvReader {

    public List<Data> readFile(String fileName) throws IOException {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        String [] columns = new String [] {"city", "name", "streetAddress", "locality", "addressRegion", "postalCode", "phone", "website"};

        strategy.setColumnMapping(columns);
        strategy.setType(Data.class);

        FileReader file = new FileReader(fileName);
        System.out.println(fileName);
        CSVReader reader = new CSVReader(file);
        reader.skip(1);

        CsvToBean<Data> csvToBean = new CsvToBean<>();
        return csvToBean.parse(strategy, reader);
    }
}
