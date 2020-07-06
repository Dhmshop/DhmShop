package dhm.com.dhmshop.entity;

public class GetAbout {
    /**
     * code : 1
     * message : 成功
     * data : &lt;p&gt;商城正式上线了！！！！&lt;/p&gt;
     */

    private int code;
    private String message;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
