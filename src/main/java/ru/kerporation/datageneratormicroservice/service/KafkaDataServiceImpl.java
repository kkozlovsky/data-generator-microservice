package ru.kerporation.datageneratormicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import ru.kerporation.datageneratormicroservice.model.Data;

@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

    private final KafkaSender<String, Object> sender;

    @Override
    public void send(final Data data) {
        final String topic = switch (data.getMeasurementType()) {
            case TEMPERATURE -> "data-temperature";
            case VOLTAGE -> "data-voltage";
            case POWER -> "data-power";
        };

        sender.send(Mono.just(SenderRecord
                        .create(topic,
                                0,
                                System.currentTimeMillis(),
                                String.valueOf(data.hashCode()),
                                data,
                                null)
                )
        ).subscribe();
    }

}