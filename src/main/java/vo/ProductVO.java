package vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class ProductVO {


    private String name;
    private String sid;
    private String sn;
    private int size;
    private String phonenum;
    private Date regdate;


}
