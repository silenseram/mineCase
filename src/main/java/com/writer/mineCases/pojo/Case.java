package com.writer.mineCases.pojo;


public class Case {

    private String name;
    private int price;
    private String texture;

    public Case(String name, int price, String texture) {
        this.name = name;
        this.price = price;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getTexture() {
        return texture;
    }
}



