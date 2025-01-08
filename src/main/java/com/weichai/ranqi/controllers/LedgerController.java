package com.weichai.ranqi.controllers;

import com.weichai.ranqi.entity.Ledger;
import com.weichai.ranqi.service.LedgerService;
import com.weichai.ranqi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ledger")
public class LedgerController {

    @Autowired
    private LedgerService ledgerService;

    @PostMapping("/add")
    public Response addLedger(@RequestBody Ledger ledger) {
        ledgerService.addLedger(ledger);
        return Response.success("Ledger added successfully");
    }

    @GetMapping("/{id}")
    public Response getLedger(@PathVariable Long id) {
        Ledger ledger = ledgerService.getLedgerById(id);
        return ledger != null ? Response.success(ledger) : Response.error("Ledger not found");
    }

    @GetMapping("/list")
    public Response getAllLedgers() {
        List<Ledger> ledgers = ledgerService.getAllLedgers();
        return Response.success(ledgers);
    }

    @PutMapping("/update/{id}")
    public Response updateLedger(@PathVariable Long id, @RequestBody Ledger ledger) {
        ledger.setId(id);
        int result = ledgerService.updateLedger(ledger);
        return result > 0 ? Response.success("Ledger updated successfully") : Response.error("Update failed");
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteLedger(@PathVariable Long id) {
        int result = ledgerService.deleteLedgerById(id);
        return result > 0 ? Response.success("Ledger deleted successfully") : Response.error("Delete failed");
    }
}
