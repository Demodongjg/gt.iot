package gt.iot.wlw.flink.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class WLwMsg {

    //主题
    private String theme;

    private String wlwMsg;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Timestamp time;

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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
