package com.perinfinity.volunteering.opportunity.repository;

import com.perinfinity.volunteering.opportunity.model.Opportunity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface OpportunityRepositoryCustom {
    Page<Opportunity> search(String title, String category, String town, LocalDate startDate, Pageable pageable);
}


