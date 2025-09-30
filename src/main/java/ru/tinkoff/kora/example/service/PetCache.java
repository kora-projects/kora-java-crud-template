package ru.tinkoff.kora.example.service;

import ru.tinkoff.kora.cache.annotation.Cache;
import ru.tinkoff.kora.cache.caffeine.CaffeineCache;
import ru.tinkoff.kora.example.model.dao.Pet;

@Cache("pet-cache")
public interface PetCache extends CaffeineCache<Long, Pet> {

}
