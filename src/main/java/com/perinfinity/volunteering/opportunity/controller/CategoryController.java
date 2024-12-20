package com.perinfinity.volunteering.opportunity.controller;

import com.perinfinity.volunteering.opportunity.model.Category;
import com.perinfinity.volunteering.opportunity.service.CategoryInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    CategoryInitializer categoryInitializer;

    public CategoryController(CategoryInitializer categoryInitializer) {
        this.categoryInitializer = categoryInitializer;
    }

    @GetMapping
    public List<Category> getCategories () {
        return categoryInitializer.getAllCategories();
    }
}
