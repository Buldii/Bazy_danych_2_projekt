package com.game.service;

import com.game.model.Trade;
import com.game.model.Village;
import com.game.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private VillageService villageService;

    @Autowired
    private EventLogService eventLogService;

    public Trade createTrade(String fromPlayerId, String toPlayerId, String resourceType, Integer amount) {
        Trade trade = new Trade(fromPlayerId, toPlayerId, resourceType, amount);
        Trade savedTrade = tradeRepository.save(trade);

        String message = String.format("Nowa oferta handlowa od gracza %s do gracza %s", fromPlayerId, toPlayerId);
        String details = String.format("Typ surowca: %s, ilość: %d, status: %s", 
                                     resourceType, amount, savedTrade.getStatus());
        eventLogService.addTradeLog(message, details);

        return savedTrade;
    }

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public List<Trade> getTradesByPlayerId(String playerId) {
        List<Trade> sentTrades = tradeRepository.findByFromPlayerId(playerId);
        List<Trade> receivedTrades = tradeRepository.findByToPlayerId(playerId);
        sentTrades.addAll(receivedTrades);
        return sentTrades;
    }

    public Trade acceptTrade(String tradeId) {
        Trade trade = tradeRepository.findById(tradeId).orElse(null);
        if (trade != null && "PENDING".equals(trade.getStatus())) {
            List<Village> fromVillages = villageService.getVillagesByPlayerId(trade.getFromPlayerId());
            List<Village> toVillages = villageService.getVillagesByPlayerId(trade.getToPlayerId());

            if (!fromVillages.isEmpty() && !toVillages.isEmpty()) {
                Village fromVillage = fromVillages.get(0);
                Village toVillage = toVillages.get(0);

                switch (trade.getResourceType()) {
                    case "wood":
                        villageService.updateVillageResources(fromVillage.getId(), -trade.getAmount(), null, null);
                        villageService.updateVillageResources(toVillage.getId(), trade.getAmount(), null, null);
                        break;
                    case "stone":
                        villageService.updateVillageResources(fromVillage.getId(), null, -trade.getAmount(), null);
                        villageService.updateVillageResources(toVillage.getId(), null, trade.getAmount(), null);
                        break;
                    case "food":
                        villageService.updateVillageResources(fromVillage.getId(), null, null, -trade.getAmount());
                        villageService.updateVillageResources(toVillage.getId(), null, null, trade.getAmount());
                        break;
                }

                trade.setStatus("COMPLETED");
                Trade completedTrade = tradeRepository.save(trade);

                String message = String.format("Transakcja zakończona między graczami %s i %s", 
                                             trade.getFromPlayerId(), trade.getToPlayerId());
                String details = String.format("Typ surowca: %s, ilość: %d, status: %s", 
                                             trade.getResourceType(), trade.getAmount(), "COMPLETED");
                eventLogService.addTradeLog(message, details);

                return completedTrade;
            }
        }
        return trade;
    }

    public Trade rejectTrade(String tradeId) {
        Trade trade = tradeRepository.findById(tradeId).orElse(null);
        if (trade != null && "PENDING".equals(trade.getStatus())) {
            trade.setStatus("CANCELLED");
            Trade rejectedTrade = tradeRepository.save(trade);

            String message = String.format("Transakcja odrzucona między graczami %s i %s", 
                                         trade.getFromPlayerId(), trade.getToPlayerId());
            String details = String.format("Typ surowca: %s, ilość: %d, status: %s", 
                                         trade.getResourceType(), trade.getAmount(), "CANCELLED");
            eventLogService.addTradeLog(message, details);

            return rejectedTrade;
        }
        return trade;
    }
}