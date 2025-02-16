package ru.tinkoff.kora.java.crud.service;

import ru.tinkoff.kora.cache.annotation.Cache;
import ru.tinkoff.kora.cache.caffeine.CaffeineCache;
import ru.tinkoff.kora.java.crud.model.dao.Pet;

@Cache("pet-cache")
public interface PetCache extends CaffeineCache<Long, Pet> {

}
