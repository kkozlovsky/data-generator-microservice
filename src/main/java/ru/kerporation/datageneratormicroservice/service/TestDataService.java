package ru.kerporation.datageneratormicroservice.service;

import ru.kerporation.datageneratormicroservice.model.test.DataTestOptions;

public interface TestDataService {

    void sendMessages(final DataTestOptions testOptions);

}