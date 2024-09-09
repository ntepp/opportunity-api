package com.perinfinity.volunteering.opportunity.service;

import com.perinfinity.volunteering.opportunity.model.Opportunity;
import com.perinfinity.volunteering.opportunity.repository.OpportunityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OpportunityServiceTest {

    @Autowired
    IOpportunityService opportunityService;

    @Autowired
    OpportunityRepository opportunityRepository;

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