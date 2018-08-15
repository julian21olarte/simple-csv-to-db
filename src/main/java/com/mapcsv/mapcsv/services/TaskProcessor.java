package com.mapcsv.mapcsv.services;

import com.mapcsv.mapcsv.entities.Data;

import java.util.List;

public interface TaskProcessor {
    void processData(List<Data> listData);
}
