package ru.kerporation.datageneratormicroservice.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kerporation.datageneratormicroservice.model.Data;
import ru.kerporation.datageneratormicroservice.service.KafkaDataService;
import ru.kerporation.datageneratormicroservice.web.dto.DataDto;
import ru.kerporation.datageneratormicroservice.web.mapper.DataMapper;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final KafkaDataService kafkaDataService;
    private final DataMapper dataMapper;

    @PostMapping("/send")
    public void send(@RequestBody final DataDto dataDto) {
        final Data data = dataMapper.toEntity(dataDto);
        kafkaDataService.send(data);
    }

}