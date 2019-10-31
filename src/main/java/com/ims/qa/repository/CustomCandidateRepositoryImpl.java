package com.ims.qa.repository;

import com.ims.qa.dto.CandidateFilterDTO;
import com.ims.qa.model.Candidate;
import lombok.var;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomCandidateRepositoryImpl implements CustomCandidateRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Candidate> filterCandidates(CandidateFilterDTO candidateFilterDTO){

        var sql = "SELECT c from Candidate c where c.active = true ";
        if (candidateFilterDTO.getLocations() != null) {
            sql = sql + "AND c.location in (:locations)";
        }

        if(candidateFilterDTO.getLevels() != null) {
            sql = sql + "AND c.level in (:levels)";
        }
//
        if(candidateFilterDTO.getCandidateStatuses() != null) {
            sql = sql + "AND c.candidateStatus in (:candidateStatuses)";
        }

        Query query = entityManager.createQuery(sql);
        query.setParameter("locations", candidateFilterDTO.getLocations());
        query.setParameter("levels", candidateFilterDTO.getLevels());
        query.setParameter("candidateStatuses", candidateFilterDTO.getCandidateStatuses());

        Pageable pageWithSize = PageRequest.of(candidateFilterDTO.getPage(), candidateFilterDTO.getSize(), Sort.by("startDate").descending());
        int pageNumber = pageWithSize.getPageNumber();
        int pageSize = pageWithSize.getPageSize();
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }
}
