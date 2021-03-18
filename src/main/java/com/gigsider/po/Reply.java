package com.gigsider.po;

public class Reply {

    private int id;
    private int comuniti_id;
    private String content;
    private String reply_id;
    private String reply_time;
    private int official;

    //表外字段
    private Admin admin;
    private User user;

    public int getOfficial() {
        return official;
    }

    public void setOfficial(int official) {
        this.official = official;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComuniti_id() {
        return comuniti_id;
    }

    public void setComuniti_id(int comuniti_id) {
        this.comuniti_id = comuniti_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }

    public String getReply_time() {
        return reply_time;
    }

    public void setReply_time(String reply_time) {
        this.reply_time = reply_time;
    }
}
