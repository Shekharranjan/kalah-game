package com.backbase.kalah.repository;

import com.backbase.kalah.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * This Interface is used to persist Game Data to MongoDB
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

@Repository
public interface KalahGameRepository extends MongoRepository<Game, String> {
}
