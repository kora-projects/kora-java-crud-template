package ru.tinkoff.kora.java.crud.repository.mapper;

import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.database.jdbc.mapper.result.JdbcResultColumnMapper;
import ru.tinkoff.kora.java.crud.model.dao.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class PetStatusResultMapper implements JdbcResultColumnMapper<Pet.Status> {

    private final Pet.Status[] statuses = Pet.Status.values();

    @Override
    public Pet.Status apply(ResultSet row, int index) throws SQLException {
        final int code = row.getInt(index);
        for (Pet.Status status : statuses) {
            if (code == status.code) {
                return status;
            }
        }

        throw new IllegalStateException("Unknown code: " + code);
    }
}
