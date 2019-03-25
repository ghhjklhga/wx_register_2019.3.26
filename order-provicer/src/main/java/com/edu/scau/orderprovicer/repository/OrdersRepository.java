package com.edu.scau.orderprovicer.repository;

import com.edu.scau.commom.enums.OrderEnum;
import com.edu.scau.commom.pojo.Doctor;
import com.edu.scau.commom.pojo.OrderDetail;
import com.edu.scau.commom.vo.OrderMasterVO;
import org.apache.ibatis.annotations.*;

import javax.persistence.criteria.Order;
import java.util.List;

@Mapper
public interface OrdersRepository {
    //创建订单
    @Insert("INSERT INTO order_detail(o_id,u_id,n_id,u_openid,doc_id,dp_id,o_visitdate," +
                "o_visittime,o_ordertime,o_price,h_id,o_bookway,o_state,o_type,o_cancelreason) " +
            "VALUES(#{o.id},#{o.userid},#{o.numberid},#{o.openid},#{o.doctorid}," +
                "#{o.departmentid},#{o.visitdate},#{o.visittime},#{o.ordertime},#{o.price},#{o.hospitalid}," +
                "#{o.bookway},#{o.state},#{o.type},#{o.cancelreason})")
    Integer insertOrderDetail(@Param("o") OrderDetail orderDetail);

    //查看订单列表，所有
    @Select("SELECT o_id as id,u_id as userid,u_openid as openid,o_visitdate as visitdate," +
                "doc_id as doctorid,dp_id as departmentid,o_ordertime as ordertime,n_id as numberid," +
                "o_visittime as visittime,o_price as price,h_id as hospitalid," +
                "o_bookway as bookway,o_state as state," +
                "o_type as type,o_cancelreason as cancelreason " +
            "FROM order_detail " +
            "WHERE u_openid = #{openid}")
    List<OrderDetail> selectOrderDetailListByOpenid(@Param("openid") String openid);

    //查看订单列表，按状态
    @Select("SELECT o_id as id,u_id as userid,u_openid as openid,o_visitdate as visitdate," +
                "doc_id as doctorid,dp_id as departmentid,o_ordertime as ordertime,n_id as numberid," +
                "o_visittime as visittime,o_price as price,h_id as hospitalid," +
                "o_bookway as bookway,o_state as state," +
                "o_type as type,o_cancelreason as cancelreason " +
            "FROM order_detail " +
            "WHERE u_openid = #{openid} AND o_state = #{orderState} " +
            "ORDER BY o_visitdate DESC")
    List<OrderDetail> selectOrderDetailListByOpenidAndOstate(@Param("openid") String openid,@Param("orderState") Integer orderState);

    //查看订单详情
    @Select("SELECT o_id as id,u_id as userid,u_openid as openid,o_visitdate as visitdate," +
                "doc_id as doctorid,dp_id as departmentid,o_ordertime as ordertime,n_id as numberid," +
                "o_visittime as visittime,o_price as price,h_id as hospitalid," +
                "o_bookway as bookway,o_state as state," +
                "o_type as type,o_cancelreason as cancelreason " +
            "FROM order_detail " +
            "WHERE o_id = #{orderid} AND u_openid = #{openid}")
    OrderDetail selectOrderDetailByOrderidAndOpenid(@Param("orderid") String orderid, @Param("openid") String openid);

    //付款订单/0等待支付/1已支付/2已取消
    @Update("UPDATE order_detail " +
            "SET o_state = 1 " +
            "WHERE o_id = #{orderid} AND u_openid = #{openid}")
    Integer payOrderDetail(@Param("orderid") String orderid,@Param("openid")String openid);

    //取消订单
    @Update("UPDATE order_detail " +
            "SET o_state = 2 " +
            "WHERE o_id = #{orderid} AND u_openid = #{openid}")
    Integer cancelOrderDetail(@Param("orderid") String orderid,@Param("openid")String openid);

    //获取医生订单，按时间
    @Select("SELECT o_id as id,u_id as userid,u_openid as openid,o_visitdate as visitdate," +
                "doc_id as doctorid,dp_id as departmentid,o_ordertime as ordertime,n_id as numberid," +
                "o_visittime as visittime,o_price as price,h_id as hospitalid," +
                "o_bookway as bookway,o_state as state," +
                "o_type as type,o_cancelreason as cancelreason " +
            "FROM order_detail " +
            "WHERE doc_id=#{did} AND o_state=#{state} AND o_visitdate=#{visitdate} AND o_visittime<=#{visittime}")
    List<OrderDetail> selectOrderListByDidAndTimeAndState(
            @Param("did")Integer did,
            @Param("visitdate")Integer visitdate,
            @Param("visittime")Integer visittime,
            @Param("state")Integer state
    );

    //获取医生订单，按时间
    @Select("SELECT o_id as id,u_id as userid,u_openid as openid,o_visitdate as visitdate," +
            "doc_id as doctorid,dp_id as departmentid,o_ordertime as ordertime,n_id as numberid," +
            "o_visittime as visittime,o_price as price,h_id as hospitalid," +
            "o_bookway as bookway,o_state as state," +
            "o_type as type,o_cancelreason as cancelreason " +
            "FROM order_detail " +
            "WHERE doc_id=#{did} AND o_visitdate=#{visitdate} " +
            "ORDER BY o_visitdate DESC")
    List<OrderDetail> selectOrderListByDidAndDate(
            @Param("did")Integer did,
            @Param("visitdate")Integer visitdate
    );

    //获取医生所有订单
    @Select("SELECT o_id as id,u_id as userid,u_openid as openid,o_visitdate as visitdate," +
                "doc_id as doctorid,dp_id as departmentid,o_ordertime as ordertime,n_id as numberid," +
                "o_visittime as visittime,o_price as price,h_id as hospitalid," +
                "o_bookway as bookway,o_state as state," +
                "o_type as type,o_cancelreason as cancelreason " +
            "FROM order_detail " +
            "WHERE doc_id=#{did} " +
            "ORDER BY o_visitdate DESC")
    List<OrderDetail> selectOrderListByDid( @Param("did")Integer did);

//    取消某时间订单
    @Update("UPDATE order_detail " +
            "SET o_state=4 " +
            "WHERE doc_id=#{did} AND o_state=#{state} AND o_visitdate=#{visitdate}")
    Integer cancelOrderByDidAndTime(
            @Param("did")Integer did,
            @Param("visitdate")Integer visitdate,
            @Param("visittime")Integer visittime,
            @Param("state")Integer state

    );

    // 医生改变订单状态
    @Update("UPDATE order_detail " +
            "SET o_state=#{newState} " +
            "WHERE o_id=#{orderId} AND o_state=#{currentState}")
    Integer updateOrderState(@Param("orderId") String orderId,
                             @Param("currentState")Integer currentState,
                             @Param("newState")Integer newState);
}
