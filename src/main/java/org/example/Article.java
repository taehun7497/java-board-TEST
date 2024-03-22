package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Article {
    private int id;
    private String title;
    private String body;
    private int hit;
    private String regDate;

    public void increaseHit() {
        this.hit++;
    }
}