package tcc_zanza.no_ip.biz.thirtyminutes.VO;

/**
 * Created by VINICIUS on 1/23/2016.
 */
public class PINCode {


    private String code = null;
    private Long id;

    public PINCode() {}

    public PINCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
