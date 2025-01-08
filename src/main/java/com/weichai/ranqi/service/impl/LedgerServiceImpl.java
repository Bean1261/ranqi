package com.weichai.ranqi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weichai.ranqi.entity.Ledger;
import com.weichai.ranqi.mapper.LedgerMapper;
import com.weichai.ranqi.service.LedgerService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LedgerServiceImpl implements LedgerService {

    @Autowired
    private LedgerMapper ledgerMapper;

    // ObjectMapper 用于将 List<String> 转换为 JSON 字符串和将 JSON 字符串转换为 List<String>
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void addLedger(Ledger ledger) {
        // 将 List<String> 转换为 JSON 字符串
        try {
            if (ledger.getBeforeRectificationPhoto() != null) {
                String beforeRectificationJson = objectMapper.writeValueAsString(ledger.getBeforeRectificationPhoto());
                ledger.setBeforeRectificationPhotoJson(beforeRectificationJson); // 保存 JSON 字符串
            }
            if (ledger.getAfterRectificationPhoto() != null) {
                String afterRectificationJson = objectMapper.writeValueAsString(ledger.getAfterRectificationPhoto());
                ledger.setAfterRectificationPhotoJson(afterRectificationJson); // 保存 JSON 字符串
            }
        } catch (IOException e) {
            e.printStackTrace(); // 处理 JSON 转换异常
        }

        // 保存到数据库
        ledgerMapper.insertLedger(ledger);
    }

    @Override
    public Ledger getLedgerById(Long id) {
        Ledger ledger = ledgerMapper.selectById(id);
        // 将 JSON 字符串转换为 List<String>
        try {
            if (ledger != null) {
                if (ledger.getBeforeRectificationPhotoJson() != null) {
                    List<String> beforePhotos = objectMapper.readValue(ledger.getBeforeRectificationPhotoJson(), new TypeReference<List<String>>() {});
                    ledger.setBeforeRectificationPhoto(beforePhotos);
                }
                if (ledger.getAfterRectificationPhotoJson() != null) {
                    List<String> afterPhotos = objectMapper.readValue(ledger.getAfterRectificationPhotoJson(), new TypeReference<List<String>>() {});
                    ledger.setAfterRectificationPhoto(afterPhotos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // 处理 JSON 反序列化异常
        }
        return ledger;
    }
    @Override
    public List<Ledger> getAllLedgers() {
        return ledgerMapper.getAllLedgers();
    }

    @Override
    public int updateLedger(Ledger ledger) {
        return ledgerMapper.updateLedger(ledger);
    }

    @Override
    public int deleteLedgerById(Long id) {
        return ledgerMapper.deleteLedgerById(id);
    }
}
