package com.perinfinity.volunteering.opportunity.service;

import com.perinfinity.volunteering.opportunity.exception.ResourceNotFoundException;
import com.perinfinity.volunteering.opportunity.model.Opportunity;
import com.perinfinity.volunteering.opportunity.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OpportunityService implements IOpportunityService{

    @Autowired
    private OpportunityRepository opportunityRepository;

    public Opportunity createOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    public Page<Opportunity> getAllOpportunities(Pageable pageable) {
        return opportunityRepository.findAll(pageable);
    }

    @Override
    public Page<Opportunity> getByTitleContainingIgnoreCase(String title, Pageable pageable) {
        return opportunityRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Optional<Opportunity> getOpportunityById(String id) {
        return opportunityRepository.findById(id);
    }

    public Opportunity updateOpportunity(String id, Opportunity opportunityDetails) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found for this id :: " + id));

        opportunity.setTitle(opportunityDetails.getTitle());
        opportunity.setDescription(opportunityDetails.getDescription());
        opportunity.setLocation(opportunityDetails.getLocation());
        opportunity.setStartDate(opportunityDetails.getStartDate());
        opportunity.setEndDate(opportunityDetails.getEndDate());
        opportunity.setRequirements(opportunityDetails.getRequirements());
        opportunity.setOrgId(opportunityDetails.getOrgId());
        opportunity.setUpdatedAt(LocalDateTime.now());

        return opportunityRepository.save(opportunity);
    }

    public void deleteOpportunity(String id) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found for this id :: " + id));
        opportunityRepository.delete(opportunity);
    }
}

