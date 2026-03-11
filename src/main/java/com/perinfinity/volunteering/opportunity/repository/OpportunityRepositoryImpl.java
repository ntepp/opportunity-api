package com.perinfinity.volunteering.opportunity.repository;

import com.perinfinity.volunteering.opportunity.model.Opportunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OpportunityRepositoryImpl implements OpportunityRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<Opportunity> search(String title, String category, String town, LocalDate startDate, Pageable pageable) {
        List<Criteria> criteriaList = new ArrayList<>();

        if (title != null && !title.isBlank()) {
            criteriaList.add(Criteria.where("title").regex(".*" + java.util.regex.Pattern.quote(title) + ".*", "i"));
        }
        if (town != null && !town.isBlank()) {
            criteriaList.add(Criteria.where("town").regex(".*" + java.util.regex.Pattern.quote(town) + ".*", "i"));
        }
        if (startDate != null) {
            criteriaList.add(Criteria.where("startDate").gte(startDate));
        }
        if (category != null && !category.isBlank()) {
            criteriaList.add(Criteria.where("categories.name").is(category));
        }

        Query query = new Query();
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        long total = mongoTemplate.count(query, Opportunity.class);
        query.with(pageable);

        List<Opportunity> results = mongoTemplate.find(query, Opportunity.class);
        return new PageImpl<>(results, pageable, total);
    }
}


