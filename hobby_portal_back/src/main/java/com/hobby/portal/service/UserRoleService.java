package com.hobby.portal.service;

import com.hobby.portal.model.entities.UserRoleEntity;
import com.hobby.portal.model.entities.enums.UserRoleEnum;

public interface UserRoleService {
    UserRoleEntity getUserRoleByEnumName(UserRoleEnum userRoleEnum);

    UserRoleEntity saveRole(UserRoleEntity userRoleEntity);
}
