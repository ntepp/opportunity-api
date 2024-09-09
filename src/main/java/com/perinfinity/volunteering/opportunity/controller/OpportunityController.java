package com.perinfinity.volunteering.opportunity.controller;

import com.perinfinity.volunteering.opportunity.dto.OpportunityResponse;
import com.perinfinity.volunteering.opportunity.exception.ResourceNotFoundException;
import com.perinfinity.volunteering.opportunity.model.Opportunity;
import com.perinfinity.volunteering.opportunity.service.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/opportunities")
public class OpportunityController {

    @Autowired
    private IOpportunityService opportunityService;

    @PostMapping
    public Opportunity createOpportunity(@RequestBody Opportunity opportunity) {
        opportunity.setCreatedAt(LocalDateTime.now());
        return opportunityService.createOpportunity(opportunity);
    }

    @GetMapping
    public OpportunityResponse getAllOpportunities(@RequestParam(required = false) String title,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Opportunity> opportunitiesPage;
        if(Objects.isNull(title)) {
            opportunitiesPage = opportunityService.getAllOpportunities(pageable);
        } else {
            opportunitiesPage = opportunityService.getByTitleContainingIgnoreCase(title, pageable);
        }

        return OpportunityResponse
                .builder()
                .opportunities(opportunitiesPage.getContent())
                .currentPage(opportunitiesPage.getNumber())
                .totalItems(opportunitiesPage.getTotalElements())
                .totalPages(opportunitiesPage.getTotalPages())
                .itemsPerPage(opportunitiesPage.getSize())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Opportunity> getOpportunityById(@PathVariable String id) {
        Opportunity opportunity = opportunityService.getOpportunityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Opportunity not found for this id :: " + id));
        return ResponseEntity.ok().body(opportunity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Opportunity> updateOpportunity(@PathVariable String id, @RequestBody Opportunity opportunityDetails) {
        Opportunity updatedOpportunity = opportunityService.updateOpportunity(id, opportunityDetails);
        return ResponseEntity.ok(updatedOpportunity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOpportunity(@PathVariable String id) {
        opportunityService.deleteOpportunity(id);
        return ResponseEntity.noContent().build();
    }
}
