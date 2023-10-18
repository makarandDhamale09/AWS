package com.myproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Random;

@Component
public class Producer {

    private final KafkaTemplate<String, Message> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Producer(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceMessage() {
        String filePath = "data/indexProcessed.csv";

        Resource resource = new ClassPathResource(filePath);

        try (Reader reader = new FileReader(resource.getFile());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            for (CSVRecord csvRecord: csvParser) {
                var record = csvRecord.values();
                RandomRecord randomRecord = new RandomRecord(record[0], record[1], record[2],
                        record[3], record[4], record[5], record[6], record[7], record[8]);
                String sJson = objectMapper.writeValueAsString(randomRecord);
                Message message = new Message("greetings", sJson);
                kafkaTemplate.send("demo_test",message);
                System.out.println("Message Published");
                //kafkaTemplate.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
