package member.model.dto;

import java.io.Serializable;

public class MemberDto implements Serializable{
    private static final long serialVersionUID = 2304690199893905221L;

    private String id;
    private String pw;
    private String name;
    private String number;

    public MemberDto() {

    }

    public MemberDto(String id, String pw, String name, String number){
        super();
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.number = number;
    }

    public MemberDto(String id, String pw){
        super();
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String toString() {
        return "MemberDto{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
