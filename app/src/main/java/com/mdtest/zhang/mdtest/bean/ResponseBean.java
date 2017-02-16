package com.mdtest.zhang.mdtest.bean;

/**
 * Created by zhangxx on 2017/2/13.
 * MDTest --> com.mdtest.zhang.mdtest.bean
 */

public class ResponseBean<T> {

    public String apiId;
    public StatusBean status;
    public T bizobj;
    public String page_info;

    public static class StatusBean {

        public String err_code;
        public String err_msg;
        public String max_updateTime;

        @Override
        public String toString() {
            return "StatusBean{" +
                    "err_code='" + err_code + '\'' +
                    ", err_msg='" + err_msg + '\'' +
                    ", max_updateTime='" + max_updateTime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "apiId='" + apiId + '\'' +
                ", status=" + status +
                ", bizobj=" + bizobj +
                ", page_info='" + page_info + '\'' +
                '}';
    }

    public boolean loginIsInvalid() {
        return "9999999".equals(status.err_code);
    }
}
