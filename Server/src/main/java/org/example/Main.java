package org.example;

import com.google.gson.Gson;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Person p = new Person("Bob", 90);
        Gson dd = new Gson();

        System.out.println(p);

        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}