package com.weichai.ranqi.repository;

import com.weichai.ranqi.entity.SafetyLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SafetyLedgerRepository extends JpaRepository<SafetyLedger, Long> {
    /**
     * 根据关键字模糊查询
     *
     * @param keyword 查询关键字
     * @return 符合条件的隐患记录列表
     */
    @Query("SELECT l FROM SafetyLedger l WHERE " +
            "l.inspectionType LIKE %:keyword% OR " +
            "l.issueDescription LIKE %:keyword% OR " +
            "l.department LIKE %:keyword% OR " +
            "l.personInCharge LIKE %:keyword%")
    List<SafetyLedger> findByKeyword(String keyword);
}
