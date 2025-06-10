package com.game.controller;

import com.game.model.Trade;
import com.game.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trades")
public class TradeController {
    
    @Autowired
    private TradeService tradeService;
    
    // Pobierz wszystkie transakcje
    @GetMapping
    public List<Trade> getAllTrades() {
        return tradeService.getAllTrades();
    }
    
    // Pobierz transakcje gracza
    @GetMapping("/player/{playerId}")
    public List<Trade> getTradesByPlayerId(@PathVariable String playerId) {
        return tradeService.getTradesByPlayerId(playerId);
    }
    
    // Utwórz nową transakcję
    @PostMapping
    public Trade createTrade(@RequestParam String fromPlayerId,
                           @RequestParam String toPlayerId,
                           @RequestParam String resourceType,
                           @RequestParam Integer amount) {
        return tradeService.createTrade(fromPlayerId, toPlayerId, resourceType, amount);
    }
    
    // Zaakceptuj transakcję
    @PutMapping("/{id}/accept")
    public Trade acceptTrade(@PathVariable String id) {
        return tradeService.acceptTrade(id);
    }
    
    // Odrzuć transakcję
    @PutMapping("/{id}/reject")
    public Trade rejectTrade(@PathVariable String id) {
        return tradeService.rejectTrade(id);
    }
}