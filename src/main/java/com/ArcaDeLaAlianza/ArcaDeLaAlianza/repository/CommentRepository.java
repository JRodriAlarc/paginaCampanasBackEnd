package com.ArcaDeLaAlianza.ArcaDeLaAlianza.repository;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,Integer>{
    
}
