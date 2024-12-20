package com.perinfinity.volunteering.opportunity.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "categories")
public class Category {
    @Id
    private String id;

    private String name;
    public Category(String name) {
        this.name = name;
    }
}
/**
 * How to create categories?
 * Option 1: Manual creation of categories by organization
 * Option 2: Automatic creation of categories
 * Option 3: Initialization of categories with default values
 *
 * 0. Rajouter une liste de categories par defaut en BD.
 * 1. Au chargement du site, renvoyer la liste des categories(creer un services - /categories)
 * 2. Il faut rajouter le choix des categories dans le formulaire de creation d'une opportunité
 */