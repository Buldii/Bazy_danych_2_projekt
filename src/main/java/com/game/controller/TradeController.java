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

    @GetMapping
    public List<Trade> getAllTrades() {
        return tradeService.getAllTrades();
    }

    @GetMapping("/player/{playerId}")
    public List<Trade> getTradesByPlayerId(@PathVariable String playerId) {
        return tradeService.getTradesByPlayerId(playerId);
    }

    @PostMapping
    public Trade createTrade(@RequestParam String fromPlayerId,
                           @RequestParam String toPlayerId,
                           @RequestParam String resourceType,
                           @RequestParam Integer amount) {
        return tradeService.createTrade(fromPlayerId, toPlayerId, resourceType, amount);
    }

    @PutMapping("/{id}/accept")
    public Trade acceptTrade(@PathVariable String id) {
        return tradeService.acceptTrade(id);
    }

    @PutMapping("/{id}/reject")
    public Trade rejectTrade(@PathVariable String id) {
        return tradeService.rejectTrade(id);
    }
}