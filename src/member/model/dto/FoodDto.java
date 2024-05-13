package member.model.dto;

public class FoodDto {
//    테이블ID	TBL_ITEM	테이블명	음식
//    ITEM_CODE	음식 코드
//    ITEM_NAME	음식이름
//    ITEM_PRICE	가격
    private int item_code;
    private String item_name;
    private int item_price;

    public FoodDto() {}

    public int getItem_code() {
        return item_code;
    }

    public void setItem_code(int item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    @Override
    public String toString() {
        return "FoodDto{" +
                "item_code=" + item_code +
                ", item_name='" + item_name + '\'' +
                ", item_price=" + item_price +
                '}';
    }
}
