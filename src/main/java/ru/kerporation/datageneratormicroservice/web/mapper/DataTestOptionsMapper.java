package ru.kerporation.datageneratormicroservice.web.mapper;

import org.mapstruct.Mapper;
import ru.kerporation.datageneratormicroservice.model.test.DataTestOptions;
import ru.kerporation.datageneratormicroservice.web.dto.DataTestOptionsDto;

@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper extends Mappable<DataTestOptions, DataTestOptionsDto> {
}
