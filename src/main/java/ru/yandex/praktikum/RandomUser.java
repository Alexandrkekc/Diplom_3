package ru.yandex.praktikum;

import java.util.Random;

public class RandomUser {
    private String email = "email" + new Random().nextInt(10000) + "@yandex.ru";
    private String password = "123456" + new Random().nextInt(10000);
    private String name = "user" + new Random().nextInt(10000);

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}