package com.perinfinity.volunteering.opportunity.service;

import com.perinfinity.volunteering.opportunity.model.Category;
import com.perinfinity.volunteering.opportunity.model.Opportunity;
import com.perinfinity.volunteering.opportunity.model.Skill;
import com.perinfinity.volunteering.opportunity.repository.OpportunityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest

class OpportunityServiceTest {

    @Autowired
    OpportunityService opportunityService;

    @MockBean
    OpportunityRepository opportunityRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOpportunity_ShouldReturnSavedOpportunity() {
        Category category1 = new Category();
        category1.setName("category1");
        Category category2 = new Category();
        category2.setName("category2");
        List<Category> categories = List.of(category1, category2);

        // Create two Skill objects
        Skill skill1 = new Skill();
        skill1.setName("Java");
        skill1.setLevel("Advanced");

        Skill skill2 = new Skill();
        skill2.setName("Spring Boot");
        skill2.setLevel("Intermediate");

        // Add them to a list
        List<Skill> skills = List.of(skill1, skill2);
        Opportunity opportunity = Opportunity.builder()
                .createdAt(LocalDateTime.now())
                .title("New Title")
                .description("test description")
                .categories(categories)
                .skillsRequired(skills)
                .orgId(1)
                .startDate(LocalDate.of(2024,12,10))
                .endDate(LocalDate.of(2024,12,10))
                .location("Yaounde")
                .id("aaaz")
                .build();


        when(opportunityRepository.save(opportunity)).thenReturn(opportunity);

        Opportunity savedOpportunity = opportunityService.createOpportunity(opportunity);

        assertNotNull(savedOpportunity);
        assertEquals("New Title", savedOpportunity.getTitle());
        assertEquals(opportunity, savedOpportunity);
    }

    @Test
    void createOpportunity() {
        Opportunity newOpportunities = Opportunity.builder()
                .createdAt(LocalDateTime.now())
                .title("test")
                .description("test description")
                .orgId(1)
                .startDate(LocalDate.of(2024,12,10))
                .endDate(LocalDate.of(2024,12,10))
                .location("Yaounde")
                .build();

        Opportunity opportunity = opportunityService.createOpportunity(newOpportunities);
        assertNotNull(opportunity);
    }

    @Test
    void getAllOpportunities() {


        List<Opportunity> expectedOpportunities = new ArrayList<>();
        // add 5 opportunities
        for (int i = 0; i < 10; i++) {
            Opportunity newOpportunities = Opportunity.builder()
                .createdAt(LocalDateTime.now())
                .title("test -"+i)
                .description("test description")
                .orgId(i)
                .startDate(LocalDate.of(2024,12,10))
                .endDate(LocalDate.of(2024,12,10))
                .location("Yaounde")
                .build();
            expectedOpportunities.add(newOpportunities);
        }


        opportunityRepository.saveAll(expectedOpportunities);
        Pageable page1 = PageRequest.of(0, 5);
        Page<Opportunity> opportunityPage1 =  opportunityService.getAllOpportunities(page1);
        Page<Opportunity> opportunityPage2 =  opportunityService.getAllOpportunities(page1.next());
        System.out.println(opportunityPage1.getContent());
        System.out.println(opportunityPage2.getContent());
        assertNotNull(opportunityPage1);
        assertNotNull(opportunityPage2);
    }

    @Test
    void getOpportunityById() {
    }

    @Test
    void updateOpportunity() {
    }

    @Test
    void deleteOpportunity() {
    }
}