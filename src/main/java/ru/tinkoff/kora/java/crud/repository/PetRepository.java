package ru.tinkoff.kora.java.crud.repository;

import java.util.Optional;
import ru.tinkoff.kora.database.common.UpdateCount;
import ru.tinkoff.kora.database.common.annotation.Id;
import ru.tinkoff.kora.database.common.annotation.Query;
import ru.tinkoff.kora.database.common.annotation.Repository;
import ru.tinkoff.kora.database.jdbc.JdbcRepository;
import ru.tinkoff.kora.java.crud.model.dao.Pet;

@Repository
public interface PetRepository extends JdbcRepository {

    @Query("SELECT %{return#selects} FROM %{return#table} WHERE id = :id")
    Optional<Pet> findById(long id);

    @Id
    @Query("INSERT INTO %{entity#inserts -= id}")
    long insert(Pet entity);

    @Query("UPDATE %{entity#table} SET %{entity#updates} WHERE %{entity#where = @id}")
    void update(Pet entity);

    @Query("DELETE FROM pets WHERE id = :id")
    UpdateCount deleteById(long id);
}
