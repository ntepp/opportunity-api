package com.perinfinity.volunteering.opportunity.service;

import com.perinfinity.volunteering.opportunity.model.Opportunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.time.LocalDate;

public interface IOpportunityService {
    Opportunity createOpportunity(Opportunity opportunity);
    Page<Opportunity> getAllOpportunities(Pageable pageable);
    Optional<Opportunity> getOpportunityById(String id);
    Opportunity updateOpportunity(String id, Opportunity opportunityDetails);
    Page<Opportunity> getByTitleContainingIgnoreCase(String title, Pageable pageable);
    void deleteOpportunity(String id);
    Page<Opportunity> search(String title, String category, String town, LocalDate startDate, Pageable pageable);

    Page<Opportunity> searchByOrgId(String orgId, Pageable pageable);
}
