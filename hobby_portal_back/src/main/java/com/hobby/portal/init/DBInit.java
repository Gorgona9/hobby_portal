package com.hobby.portal.init;


import com.hobby.portal.service.CategoryService;
import com.hobby.portal.service.HobbyService;
import com.hobby.portal.service.LocationService;
import com.hobby.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final UserService userService;
    private final CategoryService categoryService;
    private final HobbyService hobbyService;
    private final LocationService locationService;

    @Autowired
    public DBInit(UserService userService, CategoryService categoryService, HobbyService hobbyService, LocationService locationService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.hobbyService = hobbyService;
        this.locationService = locationService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.seedUsersAndUserRoles();
        this.categoryService.initCategories();
        this.locationService.initLocations();
    }

}
