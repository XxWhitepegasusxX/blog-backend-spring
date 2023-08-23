package com.example.blog.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="Posts")
public class PostModel extends RepresentationModel<PostModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id;
    private String title;

    @Lob
    private String content;
    private String author;

    @CreationTimestamp
    private Date createdAt;
    private boolean linkedinShared;
    private boolean tabnewsShared;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isLinkedinShared() {
        return linkedinShared;
    }

    public void setLinkedinShared(boolean linkedinShared) {
        this.linkedinShared = linkedinShared;
    }

    public boolean isTabnewsShared() {
        return tabnewsShared;
    }

    public void setTabnewsShared(boolean tabnewsShared) {
        this.tabnewsShared = tabnewsShared;
    }

}
