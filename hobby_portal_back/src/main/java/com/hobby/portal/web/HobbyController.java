package com.hobby.portal.web;

import com.hobby.portal.model.dto.HobbyInfoDto;
import com.hobby.portal.model.dto.HobbyInfoUpdateDto;
import com.hobby.portal.model.entities.*;
import com.hobby.portal.service.CategoryService;
import com.hobby.portal.service.HobbyService;
import com.hobby.portal.service.LocationService;
import com.hobby.portal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/hobbies")
@CrossOrigin(origins = "http://localhost:4200")
public class HobbyController {
    private final HobbyService hobbyService;
    private final CategoryService categoryService;
    private final LocationService locationService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public HobbyController(HobbyService hobbyService, CategoryService categoryService, LocationService locationService, UserService userService, ModelMapper modelMapper) {
        this.hobbyService = hobbyService;
        this.categoryService = categoryService;
        this.locationService = locationService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @Operation(summary = "Create new hobby", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<HttpStatus> saveHobby(@RequestBody HobbyInfoDto info) {
        Hobby offer = this.modelMapper.map(info, Hobby.class);
        Category category = this.categoryService.findByName(info.getCategory());
        Location location = this.locationService.getLocationByName(info.getLocation());
        offer.setLocation(location);
        offer.setCategory(category);
        BusinessOwner business = this.userService.findBusinessByUsername(info.getCreator());
        Set<Hobby> hobby_offers = business.getHobby_offers();
        hobby_offers.add(offer);
        business.setHobby_offers(hobby_offers);
        this.hobbyService.createHobby(offer);
        this.userService.saveUpdatedUser(business);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/is-saved")
    @Operation(summary = "Show if hobby is saved in favorites", security = @SecurityRequirement(name = "bearerAuth"))
    public boolean isHobbySaved(@RequestParam Long id, @RequestParam String username) {
        return this.hobbyService.isHobbySaved(id, username);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Show hobby details", security = @SecurityRequirement(name = "bearerAuth"))
    public Hobby getHobbyDetails(@PathVariable Long id) {
        return this.hobbyService.findHobbieById(id);
    }


    @PostMapping("/save")
    @Operation(summary = "Save hobby in favorites", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Long> save(@RequestParam Long id, @RequestParam String username) {
        Hobby hobby = this.hobbyService.findHobbieById(id);
        boolean isSaved = this.hobbyService.saveHobbyForClient(hobby, username);
        if (!isSaved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    @Operation(summary = "Remove hobby from favorites", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Long> removeHobby(@RequestParam Long id, @RequestParam String username) {
        Hobby hobby = this.hobbyService.findHobbieById(id);
        boolean isRemoved = this.hobbyService.removeHobbyForClient(hobby, username);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @PutMapping
    @Operation(summary = "Update hobby,(use existing hobby id)", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<?> updateHobby(@RequestBody HobbyInfoUpdateDto info) throws Exception {
        Hobby offer = this.modelMapper.map(info, Hobby.class);
        Category category = this.categoryService.findByName(info.getCategory());
        Location location = this.locationService.getLocationByName(info.getLocation());
        offer.setLocation(location);
        offer.setCategory(category);
        this.hobbyService.saveUpdatedHobby(offer);
        return new ResponseEntity<Hobby>(offer, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete hobby", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Long> deleteHobby(@PathVariable Long id) throws Exception {
        boolean isRemoved = this.hobbyService.deleteHobby(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/saved")
    @Operation(summary = "Show hobbies that are saved in favorites", security = @SecurityRequirement(name = "bearerAuth"))
    public List<Hobby> savedHobbies(@RequestParam String username) {
        AppClient appClient = this.userService.findAppClientByUsername(username);
        return this.hobbyService.findSavedHobbies(appClient);

    }
}

