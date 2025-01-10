package com.weichai.ranqi.service;

import com.weichai.ranqi.entity.SafetyLedger;
import com.weichai.ranqi.repository.SafetyLedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SafetyLedgerService {
    @Autowired
    private SafetyLedgerRepository repository;

    /**
     * 保存或更新隐患记录
     *
     * @param ledger 隐患记录
     * @return 保存后的实体
     */
    public SafetyLedger saveLedger(SafetyLedger ledger) {
        return repository.save(ledger);
    }

    /**
     * 获取所有隐患记录
     *
     * @return 隐患记录列表
     */
    public List<SafetyLedger> getAllLedgers() {
        return repository.findAll();
    }

    /**
     * 根据关键字模糊查询隐患记录
     *
     * @param keyword 查询关键字
     * @return 符合条件的隐患记录列表
     */
    public List<SafetyLedger> searchLedgers(String keyword) {
        return repository.findByKeyword(keyword);
    }

    /**
     * 根据 ID 删除隐患记录
     *
     * @param id 隐患记录 ID
     * @return 是否成功删除
     */
    public boolean deleteLedgerById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * 根据 ID 获取单条隐患记录
     *
     * @param id 隐患记录 ID
     * @return 隐患记录（Optional）
     */
    public Optional<SafetyLedger> getLedgerById(Long id) {
        return repository.findById(id);
    }
}
