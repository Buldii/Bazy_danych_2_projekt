package com.game.repository;

import com.game.model.Attack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttackRepository extends MongoRepository<Attack, String> {

    // Znajdź ataki gracza (jako atakujący)
    List<Attack> findByAttackerPlayerId(String attackerId);

    // Znajdź ataki na gracza (jako broniący)
    List<Attack> findByDefenderPlayerId(String defenderId);

    // Znajdź ataki według statusu
    List<Attack> findByStatus(String status);

    // Znajdź ataki wioski (jako atakująca)
    List<Attack> findByAttackerVillageId(String villageId);

    // Znajdź ataki na wioskę (jako broniąca)
    List<Attack> findByDefenderVillageId(String villageId);
}