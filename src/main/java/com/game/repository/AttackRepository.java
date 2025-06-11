package com.game.repository;

import com.game.model.Attack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttackRepository extends MongoRepository<Attack, String> {
    List<Attack> findByAttackerPlayerId(String attackerId);
    List<Attack> findByDefenderPlayerId(String defenderId);
    List<Attack> findByAttackerVillageId(String villageId);
    List<Attack> findByDefenderVillageId(String villageId);
}