package com.perinfinity.volunteering.opportunity.service;

import com.perinfinity.volunteering.opportunity.model.Category;
import com.perinfinity.volunteering.opportunity.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryInitializer implements ICategoryService{

    private final CategoryRepository categoryRepository;


    private final String categories;

    public CategoryInitializer(CategoryRepository categoryRepository, @Value("${app.categories}") String categories) {
        this.categoryRepository = categoryRepository;
        this.categories = categories;
    }

    @PostConstruct
    public void initializeCategories() {
        List<Category> defaultCategories = Arrays.stream(categories.split(","))
                .map(name -> new Category(name.replaceAll("_", " ")))
                .toList();

        // Vérification et insertion des catégories manquantes
        for (Category category : defaultCategories) {
            if (!categoryRepository.existsByName(category.getName())) {
                categoryRepository.save(category);
            }
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }
}