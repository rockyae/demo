package com.example.demo.module;


//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class Point implements Serializable {
    private static final long serialVersionUID = 1L;
    int x;
    int y;

    public int getX() {
        return x;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
