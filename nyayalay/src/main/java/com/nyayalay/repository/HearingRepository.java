package com.nyayalay.repository;

import com.nyayalay.entity.Hearing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HearingRepository extends JpaRepository<Hearing,Long> {
    List<Hearing> findByCourtCaseId(Long caseId);
    List<Hearing> findByHearingDate(LocalDate date);
    List<Hearing> findByJudgeName(String judgeName);
}
