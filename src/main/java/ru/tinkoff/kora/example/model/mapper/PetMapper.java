package ru.tinkoff.kora.example.model.mapper;

import org.mapstruct.Mapper;
import ru.tinkoff.kora.example.model.dao.Pet;
import ru.tinkoff.kora.example.openapi.http.server.model.PetTO;

@Mapper
public interface PetMapper {

    PetTO asDTO(Pet pet);
}
