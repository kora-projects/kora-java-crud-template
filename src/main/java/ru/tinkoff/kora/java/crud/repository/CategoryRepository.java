package ru.tinkoff.kora.java.crud.repository;

import ru.tinkoff.kora.database.common.annotation.Id;
import ru.tinkoff.kora.database.common.annotation.Query;
import ru.tinkoff.kora.database.common.annotation.Repository;
import ru.tinkoff.kora.database.jdbc.JdbcRepository;
import ru.tinkoff.kora.java.crud.model.dao.PetCategory;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JdbcRepository {

    @Query("SELECT %{return#selects} FROM %{return#table} WHERE name = :name")
    Optional<PetCategory> findByName(String name);

    @Id
    @Query("INSERT INTO categories(name) VALUES (:categoryName)")
    long insert(String categoryName);

    @Query("DELETE FROM categories WHERE id = :id")
    void deleteById(long id);
}
