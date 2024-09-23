package ru.tinkoff.kora.java.crud.model.mapper;

import org.mapstruct.Mapper;
import ru.tinkoff.kora.java.crud.model.dao.PetCategory;
import ru.tinkoff.kora.java.crud.model.dao.PetWithCategory;
import ru.tinkoff.kora.java.crud.openapi.http.server.model.CategoryTO;
import ru.tinkoff.kora.java.crud.openapi.http.server.model.PetTO;

@Mapper
public interface PetMapper {

    PetTO asDTO(PetWithCategory pet);

    CategoryTO asDTO(PetCategory category);
}
