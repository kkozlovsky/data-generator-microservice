package ru.kerporation.datageneratormicroservice.service;

import ru.kerporation.datageneratormicroservice.model.Data;

public interface KafkaDataService {

    void send(final Data data);

}