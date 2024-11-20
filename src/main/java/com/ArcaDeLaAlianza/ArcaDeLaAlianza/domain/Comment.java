package com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {
    private String id;
    private String name;
    private String comment;
    private String image;

    public Comment(String id, String name, String comment, String image) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
