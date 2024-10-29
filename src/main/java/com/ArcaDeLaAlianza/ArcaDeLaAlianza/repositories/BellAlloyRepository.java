package com.ArcaDeLaAlianza.ArcaDeLaAlianza.repositories;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.models.BellAlloy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BellAlloyRepository extends MongoRepository<BellAlloy, String> {
    BellAlloy findByType(String type);
}
