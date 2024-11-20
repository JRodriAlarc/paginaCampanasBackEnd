package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controller;


import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.Comment;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.Product;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class CommentController {

    @Autowired
    private CommentRepository commentRepo;

    @GetMapping("/all")
    public ResponseEntity<?> obtenerComentario() {
        try {
            List<Comment> products = commentRepo.findAll();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
