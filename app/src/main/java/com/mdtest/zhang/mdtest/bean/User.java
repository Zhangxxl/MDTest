package com.mdtest.zhang.mdtest.bean;

/**
 * Created by zhangxx on 2017/2/13.
 * MDTest --> com.mdtest.zhang.mdtest.bean
 */

public class User {

    public String sex;
    public String nickname;
    public String token;
    public String tel;
    public int score;
    public String userid;
    public int badge;
    public String photo;
    public String intro;

    @Override
    public String toString() {
        return "User{" +
                "sex='" + sex + '\'' +
                ", nickname='" + nickname + '\'' +
                ", token='" + token + '\'' +
                ", tel='" + tel + '\'' +
                ", score=" + score +
                ", userid='" + userid + '\'' +
                ", badge=" + badge +
                ", photo='" + photo + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
