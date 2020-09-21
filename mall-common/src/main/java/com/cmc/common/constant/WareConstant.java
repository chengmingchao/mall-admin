package com.cmc.common.constant;

/**
 * @author chengmingchao
 * @version 1.0
 * @date 2020/9/21 10:00 下午
 */
public class WareConstant {

    public enum PurchaseStatus {
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        RECEIVED(2, "已领取"),
        FINISHED(3, "已完成"),
        HASERROR(4, "有异常");
        private int code;
        private String msg;

        PurchaseStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public enum PurchaseDetailStatus {
        CREATED(0, "新建"),
        ASSIGNED(1, "已分配"),
        BUYING(2, "采购中"),
        FINISHED(3, "已完成"),
        HASERROR(4, "采购失败");
        private int code;
        private String msg;

        PurchaseDetailStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
