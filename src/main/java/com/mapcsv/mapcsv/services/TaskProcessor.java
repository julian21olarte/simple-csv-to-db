package com.mapcsv.mapcsv.services;

import com.mapcsv.mapcsv.entities.Data;

import java.io.FileNotFoundException;
import java.util.List;

public interface TaskProcessor {

    List<Data> readFile(String fileName) throws FileNotFoundException;
    void processData(List<Data> listData);
}
