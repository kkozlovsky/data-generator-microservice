package ru.kerporation.datageneratormicroservice.web.mapper;

import org.mapstruct.Mapper;
import ru.kerporation.datageneratormicroservice.model.Data;
import ru.kerporation.datageneratormicroservice.web.dto.DataDto;

@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDto> {
}
