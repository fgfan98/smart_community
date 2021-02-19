package com.gigsider.po;

public class Mytest {

    private Integer id;
    private String string;

    @Override
    public String toString() {
        return "Mytest{" +
                "id=" + id +
                ", string='" + string + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
