const db = require('../db'); // 假设已配置数据库连接

// 获取隐患列表
exports.getAllLedgers = async (req, res) => {
    try {
        const [results] = await db.query('SELECT * FROM safety_hazard_ledger');
        res.json(results);
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// 添加新隐患
exports.addLedger = async (req, res) => {
    try {
        const {
            inspectionType, checkTime, rectificationDeadline, hazardSeverity, issueCategory,
            subCategory, detailedCategory, department, hazardArea, personInCharge, subArea,
            detailedArea, issueObject, issueMode, lValue, eValue, cValue, dValue,
            beforeRectificationPhoto, issueDescription, isFirstDiscovery, teamLeader,
            deductionScore, inspector, afterRectificationPhoto, analysisAndMeasures,
            rectificationFinishTime, assessmentPerson, assessmentAmount
        } = req.body;

        const query = `
            INSERT INTO safety_hazard_ledger 
            (inspection_type, check_time, rectification_deadline, hazard_severity, issue_category, 
            sub_category, detailed_category, department, hazard_area, person_in_charge, sub_area, 
            detailed_area, issue_object, issue_mode, l_value, e_value, c_value, d_value, 
            before_rectification_photo, issue_description, is_first_discovery, team_leader, 
            deduction_score, inspector, after_rectification_photo, analysis_and_measures, 
            rectification_finish_time, assessment_person, assessment_amount)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        `;

        await db.query(query, [
            inspectionType, checkTime, rectificationDeadline, hazardSeverity, issueCategory,
            subCategory, detailedCategory, department, hazardArea, personInCharge, subArea,
            detailedArea, issueObject, issueMode, lValue, eValue, cValue, dValue,
            beforeRectificationPhoto, issueDescription, isFirstDiscovery, teamLeader,
            deductionScore, inspector, afterRectificationPhoto, analysisAndMeasures,
            rectificationFinishTime, assessmentPerson, assessmentAmount
        ]);
        res.status(201).json({ message: '隐患已添加' });
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// 编辑隐患
exports.updateLedger = async (req, res) => {
    try {
        const { id } = req.params;
        const updates = req.body;

        const query = `UPDATE safety_hazard_ledger SET ? WHERE id = ?`;
        await db.query(query, [updates, id]);
        res.json({ message: '隐患已更新' });
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};

// 删除隐患
exports.deleteLedger = async (req, res) => {
    try {
        const { id } = req.params;
        const query = `DELETE FROM safety_hazard_ledger WHERE id = ?`;
        await db.query(query, [id]);
        res.json({ message: '隐患已删除' });
    } catch (error) {
        res.status(500).json({ error: error.message });
    }
};
