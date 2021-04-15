package gt.iot.wlw.flink.entity;


import org.springframework.stereotype.Component;

@Component
public class WLwMsg {

    //主题
    private String theme;

    private String wlwMsg;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getWlwMsg() {
        return wlwMsg;
    }

    public void setWlwMsg(String wlwMsg) {
        this.wlwMsg = wlwMsg;
    }
}
