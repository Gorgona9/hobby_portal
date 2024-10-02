package com.hobby.portal.service;

import com.hobby.portal.model.entities.Category;
import com.hobby.portal.model.entities.enums.CategoryNameEnum;

import java.util.List;

public interface CategoryService {
    Category findByName(CategoryNameEnum category);

    List<Category> initCategories();
}
