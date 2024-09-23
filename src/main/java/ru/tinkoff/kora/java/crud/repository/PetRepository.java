package ru.tinkoff.kora.java.crud.repository;

import ru.tinkoff.kora.database.common.UpdateCount;
import ru.tinkoff.kora.database.common.annotation.Id;
import ru.tinkoff.kora.database.common.annotation.Query;
import ru.tinkoff.kora.database.common.annotation.Repository;
import ru.tinkoff.kora.database.jdbc.JdbcRepository;
import ru.tinkoff.kora.java.crud.model.dao.Pet;
import ru.tinkoff.kora.java.crud.model.dao.PetWithCategory;

import java.util.Optional;

@Repository
public interface PetRepository extends JdbcRepository {

    @Query("""
            SELECT p.id, p.name, p.status, p.category_id, c.name as category_name
            FROM pets p
            JOIN categories c on c.id = p.category_id
            WHERE p.id = :id
            """)
    Optional<PetWithCategory> findById(long id);

    @Id
    @Query("INSERT INTO %{entity#inserts -= id}")
    long insert(Pet entity);

    @Query("UPDATE %{entity#table} SET %{entity#updates} WHERE %{entity#where = @id}")
    void update(Pet entity);

    @Query("DELETE FROM pets WHERE id = :id")
    UpdateCount deleteById(long id);
}
