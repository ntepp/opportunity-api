package com.perinfinity.volunteering.opportunity.controller;

import com.perinfinity.volunteering.opportunity.model.Category;
import com.perinfinity.volunteering.opportunity.service.CategoryInitializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static com.perinfinity.volunteering.opportunity.utils.URIUtils.entityWithLocation;

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

    @PostMapping
    public ResponseEntity<Void> createCategory(Category category) {
        Category newCategory = categoryInitializer.createCategory(category);
        URI uri = entityWithLocation(newCategory.getId());
        return ResponseEntity.created(uri).build();
    }
}
