package com.weichai.ranqi.mapper;

import com.weichai.ranqi.entity.Ledger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LedgerMapper {
    void insertLedger(Ledger ledger);
    Ledger getLedgerById(Long id);
    List<Ledger> getAllLedgers();
    int updateLedger(Ledger ledger);
    int deleteLedgerById(Long id);

    Ledger selectById(Long id);
}
