package com.hnzz.androiddemo.bean;

import java.util.List;

public class QianbaoBean {


    /**
     * code : 0
     * msg : 获取成功
     * data : [{"id":85470,"plus_minus":"-","money":"0.00","description":"扣除一张免洗券","add_time":"2019-08-07 09:38:15","intro":"扣除一张免洗券"},{"id":73959,"plus_minus":"-","money":"0.00","description":"扣除一张免费洗车券","add_time":"2019-07-22 18:00:36","intro":"扣除一张免费洗车券"},{"id":67800,"plus_minus":"-","money":"0.00","description":"扣除一张免洗券","add_time":"2019-07-15 18:02:52","intro":"扣除一张免洗券"},{"id":60102,"plus_minus":"-","money":"0.00","description":"扣除一张免洗券","add_time":"2019-07-05 19:34:43","intro":"扣除一张免洗券"},{"id":56334,"plus_minus":"-","money":"0.00","description":"洗车扣费余额支付","add_time":"2019-06-29 19:51:03","intro":"洗车扣费余额支付"},{"id":56329,"plus_minus":"-","money":"0.00","description":"洗车扣费余额支付","add_time":"2019-06-29 19:43:57","intro":"洗车扣费余额支付"},{"id":55435,"plus_minus":"+","money":"0.05","description":"余额充值","add_time":"2019-06-27 18:21:24","intro":"余额充值"},{"id":55433,"plus_minus":"-","money":"0.15","description":"测试","add_time":"2019-06-27 18:20:33","intro":"测试"},{"id":55432,"plus_minus":"+","money":"0.10","description":"余额充值","add_time":"2019-06-27 18:18:38","intro":"余额充值"},{"id":55431,"plus_minus":"+","money":"0.05","description":"余额充值","add_time":"2019-06-27 18:18:14","intro":"余额充值"}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 85470
         * plus_minus : -
         * money : 0.00
         * description : 扣除一张免洗券
         * add_time : 2019-08-07 09:38:15
         * intro : 扣除一张免洗券
         */

        private int id;
        private String plus_minus;
        private String money;
        private String description;
        private String add_time;
        private String intro;

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

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }
    }
}
