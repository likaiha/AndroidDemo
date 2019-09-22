package com.hnzz.androiddemo.bean;

/**
 * Created by Administrator on 2018/4/12.
 */

public class MyQianbaoRecordInfo {

    /**
     * id : 111
     * plus_minus : +
     * money : 200.00
     * description : 余额充值
     * add_time : 2018-08-16 17:34:19
     */

    private int id;
    private String plus_minus;
    private String money;
    private String description;
    private String add_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlus_minus() {
        return plus_minus;
    }

    public void setPlus_minus(String plus_minus) {
        this.plus_minus = plus_minus;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
