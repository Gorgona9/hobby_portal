package com.hobby.portal.service;

import com.hobby.portal.model.entities.AppClient;
import com.hobby.portal.model.entities.Hobby;

import java.util.List;
import java.util.Set;

public interface HobbyService {
    Hobby findHobbieById(Long id);

    void saveUpdatedHobby(Hobby hobby) throws Exception;

    boolean deleteHobby(long id) throws Exception;

    Set<Hobby> findHobbyMatches(String username);

    boolean saveHobbyForClient(Hobby hobby, String username);

    boolean removeHobbyForClient(Hobby hobby, String username);

    boolean isHobbySaved(Long hobbyId, String username);

    List<Hobby> findSavedHobbies(AppClient appClient);

    Set<Hobby> getAllHobbiesForBusiness(String username);
    
    Set<Hobby> getAllHobbieMatchesForClient(String username);

    void createHobby(Hobby offer);
}
