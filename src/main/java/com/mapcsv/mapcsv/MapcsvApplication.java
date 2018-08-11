package com.mapcsv.mapcsv;

import com.mapcsv.mapcsv.entities.Data;
import com.mapcsv.mapcsv.services.ImplOpenCsvAndHibernate.TaskProcessorImpl;
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

    public static void main(String[] args) throws IOException {
        SpringApplication.run(MapcsvApplication.class, args);
    }


    // TIME: 18000.csv  -->  7seg. aprox.
    // TIME: 1000000.csv  -->  409 seg. aprox.
    @PostConstruct
    private void runProcess() throws IOException {
        long start = System.currentTimeMillis();
        String path = new ClassPathResource(FILE_MILLION).getFile().getAbsolutePath();
        List<Data> data = this.taskProcessor.readFile(path);
        this.taskProcessor.processData(data);

        long finish = System.currentTimeMillis();

        long time = (finish - start);
        System.out.println("Finish load results");
        System.out.println("Time: " + time);
        System.out.println("second: " + (time / 1000));
    }
}
