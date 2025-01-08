package com.weichai.ranqi.service;

import com.weichai.ranqi.entity.Ledger;

import java.util.List;

public interface LedgerService {
    void addLedger(Ledger ledger);

    Ledger getLedgerById(Long id);

    List<Ledger> getAllLedgers();

    int updateLedger(Ledger ledger);

    int deleteLedgerById(Long id);
}
