package member.model.dto;

import java.sql.Date;

public class OrderDto {
//    테이블ID	TBL_ORDER	테이블명	주문조회
//    ORDER_NO	주문번호	INT
//    ORDER_TIME	주문시간	INT
//    USER_ID	회원아이디	VARCHAR
//    ITEM_NAME	음식이름	VARCHAR
//    CNT	개수	INT
//    TOTAL_PRICE	총가격	INT
//    SERVING	서빙완료여부	INT
    private int order_no;
    private Date order_time;
    private String user_id;
    private String item_name;
    private int cnt;
    private int total_price;
    private String serving;

    public OrderDto() {}

    public int getOrder_no() {
        return order_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "order_no=" + order_no +
                ", order_time=" + order_time +
                ", user_id='" + user_id + '\'' +
                ", item_name='" + item_name + '\'' +
                ", cnt=" + cnt +
                ", total_price=" + total_price +
                ", serving='" + serving + '\'' +
                '}';
    }
}
