package gt.iot.wlw.flink.dao;

import gt.iot.wlw.flink.entity.WLwMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface WlwMsgMapper {

     int insertWlwMsg(@Param("theme")String theme,@Param("wlwMsg")String wlwmsg);

     Map<String , Object> selectMsg();

}
