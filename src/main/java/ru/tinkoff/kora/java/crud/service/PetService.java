package ru.tinkoff.kora.java.crud.service;

import java.util.Optional;
import ru.tinkoff.kora.cache.annotation.CacheInvalidate;
import ru.tinkoff.kora.cache.annotation.CachePut;
import ru.tinkoff.kora.cache.annotation.Cacheable;
import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.java.crud.model.dao.Pet;
import ru.tinkoff.kora.java.crud.openapi.http.server.model.PetCreateTO;
import ru.tinkoff.kora.java.crud.openapi.http.server.model.PetUpdateTO;
import ru.tinkoff.kora.java.crud.repository.PetRepository;
import ru.tinkoff.kora.resilient.circuitbreaker.annotation.CircuitBreaker;
import ru.tinkoff.kora.resilient.retry.annotation.Retry;
import ru.tinkoff.kora.resilient.timeout.annotation.Timeout;

@Component
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Cacheable(PetCache.class)
    @CircuitBreaker("pet")
    @Retry("pet")
    @Timeout("pet")
    public Optional<Pet> findByID(long petId) {
        return petRepository.findById(petId);
    }

    @CircuitBreaker("pet")
    @Timeout("pet")
    public Pet add(PetCreateTO createTO) {
        var pet = new Pet(0, createTO.name(), Pet.Status.AVAILABLE);
        var petId = petRepository.insert(pet);
        return new Pet(petId, pet.name(), pet.status());
    }

    @CircuitBreaker("pet")
    @Timeout("pet")
    @CachePut(value = PetCache.class, parameters = "id")
    public Optional<Pet> update(long id, PetUpdateTO updateTO) {
        final Optional<Pet> existing = petRepository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }

        if (existing.get().name().equals(updateTO.name()) && existing.get().status().equals(updateTO.status())) {
            return existing;
        }

        var status = (updateTO.status() == null)
                ? existing.get().status()
                : toStatus(updateTO.status());
        var result = new Pet(existing.get().id(), updateTO.name(), status);
        petRepository.update(result);
        return Optional.of(result);
    }

    @CircuitBreaker("pet")
    @Timeout("pet")
    @CacheInvalidate(PetCache.class)
    public boolean delete(long petId) {
        return petRepository.deleteById(petId).value() == 1;
    }

    private static Pet.Status toStatus(PetUpdateTO.StatusEnum statusEnum) {
        return switch (statusEnum) {
            case AVAILABLE -> Pet.Status.AVAILABLE;
            case PENDING -> Pet.Status.PENDING;
            case SOLD -> Pet.Status.SOLD;
        };
    }
}
