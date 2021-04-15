package gt.iot.wlw.flink.dao;

import gt.iot.wlw.flink.entity.WLwMsg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional(transactionManager = "tdengineTransactionManager")
@Mapper
public interface WlwMsgMapper {

     int insertWlwMsg(WLwMsg wLwMsg);

     Map<String , Object> selectMsg();

}
