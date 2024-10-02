package com.hobby.portal.service.impl;

import com.hobby.portal.model.entities.AppClient;
import com.hobby.portal.model.entities.Hobby;
import com.hobby.portal.model.entities.Test;
import com.hobby.portal.model.repostiory.TestRepository;
import com.hobby.portal.service.HobbyService;
import com.hobby.portal.service.TestService;
import com.hobby.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final UserService userService;
    private final HobbyService hobbyService;


    @Autowired
    public TestServiceImpl(TestRepository testRepository, UserService userService, HobbyService hobbyService) {
        this.testRepository = testRepository;
        this.userService = userService;
        this.hobbyService = hobbyService;
    }

    @Override
    public void saveTestResults(Test results) {
        AppClient currentUserAppClient = this.userService.findAppClientByUsername(results.getUsername());
        if (currentUserAppClient.getTestResults() != null) {
            results.setId(currentUserAppClient.getTestResults().getId());
        }
        this.testRepository.save(results);
        currentUserAppClient.setTestResults(results);

        Set<Hobby> hobbyMatches = this.hobbyService.findHobbyMatches(currentUserAppClient.getUsername());
        currentUserAppClient.setHobby_matches(hobbyMatches);
        this.userService.saveUpdatedUserClient(currentUserAppClient);
    }
}
