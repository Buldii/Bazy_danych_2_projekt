package com.game.repository;

import com.game.model.Village;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VillageRepository extends MongoRepository<Village, String> {
    List<Village> findByPlayerId(String playerId);
}