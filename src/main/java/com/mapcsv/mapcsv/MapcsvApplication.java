package com.mapcsv.mapcsv;

import com.mapcsv.mapcsv.entities.Data;
import com.mapcsv.mapcsv.services.CsvReader;
import com.mapcsv.mapcsv.services.TaskProcessorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class MapcsvApplication {

    private static String FILE18000 = "18000.csv";
    private static String FILE_MILLION = "1000000.csv";

    @Autowired
    private TaskProcessorImpl taskProcessor;

    @Autowired
    private CsvReader csvReader;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MapcsvApplication.class, args);
    }


    // TIME: 18000.csv  -->  7seg. aprox.
    // TIME: 1000000.csv  -->  409 seg. aprox.
    //@PostConstruct
    private void runProcess() throws IOException {
        long start = System.currentTimeMillis();

        String path = new ClassPathResource(FILE18000).getFile().getAbsolutePath();
        List<Data> data = this.csvReader.readFile(path);

        // Inserting with repository.saveAll()
        this.taskProcessor.processData(data);

        long finish = System.currentTimeMillis();

        long time = (finish - start);
        System.out.println("Finish load results");
        System.out.println("Time: " + time);
        System.out.println("second: " + (time / 1000));
    }

    // TIME: 18000.csv  -->  8seg. aprox.
    // TIME: 1000000.csv  -->  620 seg. aprox.
    //@PostConstruct
    private void runProcess2() throws IOException {
        long start = System.currentTimeMillis();

        String path = new ClassPathResource(FILE18000).getFile().getAbsolutePath();
        List<Data> data = this.csvReader.readFile(path);

        // Using entityManager Batch
        this.taskProcessor.processDataWithBatch(data);

        long finish = System.currentTimeMillis();
        long time = (finish - start);
        System.out.println("Finish load results");
        System.out.println("Time: " + time);
        System.out.println("second: " + (time / 1000));
    }

    // TIME: 18000.csv  -->  0seg. (360ms) aprox.
    // TIME: 1000000.csv  -->  15 seg. aprox.
    @PostConstruct
    private void runProcess3() throws IOException {
        long start = System.currentTimeMillis();
        String path = new ClassPathResource(FILE18000).getFile().getAbsolutePath();

        // Using LOAD DATA INFILE instruction from Mysql
        this.taskProcessor.processDataWithLoadDataInfile(path);

        long finish = System.currentTimeMillis();
        long time = (finish - start);
        System.out.println("Finish load results");
        System.out.println("Time: " + time);
        System.out.println("second: " + (time / 1000));
    }
}
