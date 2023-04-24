package com.example.demo.module;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Point implements Serializable {
    private static final long serialVersionUID = 1L;
    int x;
    int y;
}
