const express = require('express');
const router = express.Router();
const ledgerController = require('../controllers/ledgerController');

// 获取隐患列表
router.get('/', ledgerController.getAllLedgers);

// 添加新隐患
router.post('/', ledgerController.addLedger);

// 编辑隐患
router.put('/:id', ledgerController.updateLedger);

// 删除隐患
router.delete('/:id', ledgerController.deleteLedger);

module.exports = router;
