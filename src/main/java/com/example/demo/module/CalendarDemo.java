package com.example.demo.module;

import java.util.Calendar;
import java.util.UUID;

public class CalendarDemo {


        public static void main(String[] args) {
            String requestId = UUID.randomUUID().toString().replace("-", "");
            System.out.println(requestId);
        }

}
