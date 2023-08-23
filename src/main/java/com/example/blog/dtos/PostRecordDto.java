package com.example.blog.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record PostRecordDto(
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank String author,
        Date createdAt,
        boolean linkedinShared,
        boolean tabnewsShared
        ) {
}
