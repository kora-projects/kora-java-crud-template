package ru.tinkoff.kora.java.crud.model.mapper;

import org.mapstruct.Mapper;
import ru.tinkoff.kora.java.crud.model.dao.Pet;
import ru.tinkoff.kora.java.crud.openapi.http.server.model.PetTO;

@Mapper
public interface PetMapper {

    PetTO asDTO(Pet pet);
}
