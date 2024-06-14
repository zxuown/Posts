package com.example.lesson08;

public class Slide {
    private String header;
    private String paragraph;
    private int color;

    public Slide(String header, String paragraph, int color) {
        this.header = header;
        this.paragraph = paragraph;
        this.color = color;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
