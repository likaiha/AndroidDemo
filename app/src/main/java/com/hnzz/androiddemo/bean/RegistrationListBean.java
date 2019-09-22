package com.hnzz.androiddemo.bean;

import java.util.List;

public class RegistrationListBean {

    /**
     * code : 0000
     * data : [{"allnum":3,"area":"漯河市","bcydwlinkman":"荆金香","bcydwlinkphone":"18839506661","bkblbhms":"变化","bsampleclinical":"","category":"种畜场","email":"yhqdwykzx@163.com","fid":"53c34e2b-573e-4f3d-a2f3-9029bbd2800e","inspectionunit":"海洋奶牛养殖场","registerman":"郭洛生","remarks":"","samplepostion":"漯舞路与京广铁路交叉口","sampletime":"2019-06-10","temporarily":"0"},{"allnum":3,"area":"郑州市","bcydwlinkman":"杨文华","bcydwlinkphone":"18903850635","bkblbhms":"变化","bsampleclinical":"","category":"种畜场","email":"zhanghongkai546814@163.com","fid":"b6cc2a0a-eb9e-419f-bcda-a15a868e37af","inspectionunit":"河南省鼎元种牛育种有限公司","registerman":"郭洛生","remarks":"","samplepostion":"郑州市中牟县雁鸣湖镇穆山村","sampletime":"2019-06-10","temporarily":"0"},{"allnum":3,"area":"三门峡市","bcydwlinkman":"韩光晖","bcydwlinkphone":"18614918091","bkblbhms":"变化","bsampleclinical":"","category":"种畜场","email":"lsxykzx@163.com","fid":"d52ed597-c7bf-4621-81a8-5b9198baf9b4","inspectionunit":"卢氏三阳畜牧有限公司","registerman":"郭洛生","remarks":"","samplepostion":"卢氏县畜牧局","sampletime":"2019-06-10","temporarily":"0"}]
     */

    private String code;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * allnum : 3
         * area : 漯河市
         * bcydwlinkman : 荆金香
         * bcydwlinkphone : 18839506661
         * bkblbhms : 变化
         * bsampleclinical :
         * category : 种畜场
         * email : yhqdwykzx@163.com
         * fid : 53c34e2b-573e-4f3d-a2f3-9029bbd2800e
         * inspectionunit : 海洋奶牛养殖场
         * registerman : 郭洛生
         * remarks :
         * samplepostion : 漯舞路与京广铁路交叉口
         * sampletime : 2019-06-10
         * temporarily : 0
         */

        private String allnum;
        private String area;
        private String bcydwlinkman;
        private String bcydwlinkphone;
        private String bkblbhms;
        private String bsampleclinical;
        private String category;
        private String email;
        private String fid;
        private String inspectionunit;
        private String registerman;
        private String remarks;
        private String samplepostion;
        private String sampletime;
        private String temporarily;

        public String getAllnum() {
            return allnum;
        }

        public void setAllnum(String allnum) {
            this.allnum = allnum;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getBcydwlinkman() {
            return bcydwlinkman;
        }

        public void setBcydwlinkman(String bcydwlinkman) {
            this.bcydwlinkman = bcydwlinkman;
        }

        public String getBcydwlinkphone() {
            return bcydwlinkphone;
        }

        public void setBcydwlinkphone(String bcydwlinkphone) {
            this.bcydwlinkphone = bcydwlinkphone;
        }

        public String getBkblbhms() {
            return bkblbhms;
        }

        public void setBkblbhms(String bkblbhms) {
            this.bkblbhms = bkblbhms;
        }

        public String getBsampleclinical() {
            return bsampleclinical;
        }

        public void setBsampleclinical(String bsampleclinical) {
            this.bsampleclinical = bsampleclinical;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getInspectionunit() {
            return inspectionunit;
        }

        public void setInspectionunit(String inspectionunit) {
            this.inspectionunit = inspectionunit;
        }

        public String getRegisterman() {
            return registerman;
        }

        public void setRegisterman(String registerman) {
            this.registerman = registerman;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getSamplepostion() {
            return samplepostion;
        }

        public void setSamplepostion(String samplepostion) {
            this.samplepostion = samplepostion;
        }

        public String getSampletime() {
            return sampletime;
        }

        public void setSampletime(String sampletime) {
            this.sampletime = sampletime;
        }

        public String getTemporarily() {
            return temporarily;
        }

        public void setTemporarily(String temporarily) {
            this.temporarily = temporarily;
        }
    }
}
