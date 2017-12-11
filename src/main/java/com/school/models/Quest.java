package com.school.models;

public class Quest {

    private String title;
    private String info;
    private Integer prize;
    private String category;
    private Integer id;

    public Quest(String title, String info, Integer prize, String questCategory){

        this.title = title;
        this.info = info;
        this.prize = prize;
        this.category = questCategory;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public Integer getPrize() {
        return prize;
    }

    public String getInfo() {
        return info;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {

        return id;

    }
}
