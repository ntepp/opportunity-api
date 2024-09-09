package com.perinfinity.volunteering.opportunity.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
@Document(collection = "opportunities")
public class Opportunity {

    @Id
    private String id;

    private String title;

    private String description;

    private String location;

    private String town;

    private LocalDate startDate;
    private LocalDate endDate;

    private String requirements;

    private Integer orgId;

    private LocalDateTime createdAt;

    private List<Skill> skillsRequired;

    private List<Category> categories;

    private LocalDateTime updatedAt;

    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
