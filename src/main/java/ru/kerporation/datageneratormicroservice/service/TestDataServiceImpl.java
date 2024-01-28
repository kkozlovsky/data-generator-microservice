package ru.kerporation.datageneratormicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kerporation.datageneratormicroservice.model.Data;
import ru.kerporation.datageneratormicroservice.model.MeasurementType;
import ru.kerporation.datageneratormicroservice.model.test.DataTestOptions;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TestDataServiceImpl implements TestDataService {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final KafkaDataService kafkaDataService;

    @Override
    public void sendMessages(final DataTestOptions testOptions) {
        if (testOptions.getMeasurementTypes().length > 0) {
            executorService.scheduleAtFixedRate(
                    () -> {
                        final Data data = new Data();
                        data.setSensorId((long) getRandomNumber(1, 10));
                        data.setMeasurement(getRandomNumber(15, 20));
                        data.setMeasurementType(getRandomMeasurement(testOptions.getMeasurementTypes()));
                        data.setTimestamp(LocalDateTime.now());
                        kafkaDataService.send(data);
                    },
                    0,
                    testOptions.getDelayInSeconds(),
                    TimeUnit.SECONDS
            );
        }
    }

    private double getRandomNumber(final double min,
                                   final double max) {
        return (Math.random() * (max - min)) + min;
    }

    private MeasurementType getRandomMeasurement(final MeasurementType[] measurementTypes) {
        int randomTypeId = (int) (Math.random() * measurementTypes.length);
        return measurementTypes[randomTypeId];
    }

}