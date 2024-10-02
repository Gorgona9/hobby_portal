package com.hobby.portal.service;


import com.hobby.portal.model.entities.Location;
import com.hobby.portal.model.entities.enums.LocationEnum;

import java.util.List;

public interface LocationService {
    List<Location> initLocations();

    Location getLocationByName(LocationEnum locationEnum);
}
