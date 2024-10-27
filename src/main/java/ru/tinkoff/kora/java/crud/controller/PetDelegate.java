package ru.tinkoff.kora.java.crud.controller;

import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.java.crud.model.mapper.PetMapper;
import ru.tinkoff.kora.java.crud.openapi.http.server.api.PetApiDelegate;
import ru.tinkoff.kora.java.crud.openapi.http.server.api.PetApiResponses;
import ru.tinkoff.kora.java.crud.openapi.http.server.model.MessageTO;
import ru.tinkoff.kora.java.crud.openapi.http.server.model.PetCreateTO;
import ru.tinkoff.kora.java.crud.openapi.http.server.model.PetUpdateTO;
import ru.tinkoff.kora.java.crud.service.PetService;

@Component
public final class PetDelegate implements PetApiDelegate {

    private final PetMapper petMapper;
    private final PetService petService;

    public PetDelegate(PetMapper petMapper, PetService petService) {
        this.petMapper = petMapper;
        this.petService = petService;
    }

    @Override
    public PetApiResponses.GetPetByIdApiResponse getPetById(long petId) {
        if (petId < 0) {
            return new PetApiResponses.GetPetByIdApiResponse.GetPetById400ApiResponse(malformedId(petId));
        }

        var pet = petService.findByID(petId);
        if (pet.isPresent()) {
            var body = petMapper.asDTO(pet.get());
            return new PetApiResponses.GetPetByIdApiResponse.GetPetById200ApiResponse(body);
        } else {
            return new PetApiResponses.GetPetByIdApiResponse.GetPetById404ApiResponse(notFound(petId));
        }
    }

    @Override
    public PetApiResponses.AddPetApiResponse addPet(PetCreateTO petCreateTO) {
        var pet = petService.add(petCreateTO);
        var body = petMapper.asDTO(pet);
        return new PetApiResponses.AddPetApiResponse.AddPet200ApiResponse(body);
    }

    @Override
    public PetApiResponses.UpdatePetApiResponse updatePet(long petId, PetUpdateTO petUpdateTO) {
        if (petId < 0) {
            return new PetApiResponses.UpdatePetApiResponse.UpdatePet400ApiResponse(malformedId(petId));
        }

        var updated = petService.update(petId, petUpdateTO);
        if (updated.isPresent()) {
            var body = petMapper.asDTO(updated.get());
            return new PetApiResponses.UpdatePetApiResponse.UpdatePet200ApiResponse(body);
        } else {
            return new PetApiResponses.UpdatePetApiResponse.UpdatePet404ApiResponse(notFound(petId));
        }
    }

    @Override
    public PetApiResponses.DeletePetApiResponse deletePet(long petId) {
        if (petId < 0) {
            return new PetApiResponses.DeletePetApiResponse.DeletePet400ApiResponse(malformedId(petId));
        }

        if (petService.delete(petId)) {
            return new PetApiResponses.DeletePetApiResponse.DeletePet200ApiResponse(
                    new MessageTO("Successfully deleted pet with ID: " + petId));
        } else {
            return new PetApiResponses.DeletePetApiResponse.DeletePet404ApiResponse(notFound(petId));
        }
    }

    private static MessageTO notFound(long petId) {
        return new MessageTO("Pet not found for ID: " + petId);
    }

    private static MessageTO malformedId(long petId) {
        return new MessageTO("Pet malformed ID: " + petId);
    }
}
