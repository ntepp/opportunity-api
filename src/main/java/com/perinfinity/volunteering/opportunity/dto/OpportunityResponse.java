package com.perinfinity.volunteering.opportunity.dto;

import com.perinfinity.volunteering.opportunity.model.Opportunity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OpportunityResponse {
    Integer currentPage;
    Integer itemsPerPage;
    Long totalItems;
    Integer totalPages;
    List<Opportunity> opportunities;
}
