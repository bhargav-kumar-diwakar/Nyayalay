package com.nyayalay.repository;

import com.nyayalay.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party,Long> {
    List<Party> findByCourtCaseId(Long caseId);
    List<Party> findByRole(String role);
}
