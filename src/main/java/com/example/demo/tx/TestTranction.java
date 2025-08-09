package com.example.demo.tx;

import com.example.demo.mapper.OrderDetailMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.module.Order;
import com.example.demo.module.OrderDetail;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class TestTranction {


    @Autowired
    public OrderMapper orderMapper;

    @Autowired
    public OrderDetailMapper orderDetailMapper;
    /**
     * 结论：1.A方法加了事务注解，再调用同一个类的B方法，会回滚;
     *      2.目标对象没有加事务的方法testCreate直接调用当前类的事务方法，事务不生效，发生异常时不会回滚;
     *      事务的状态：活跃状态Active ->Partially Committed (部分提交状态)->Committed (已提交状态)->Failed (失败状态,
     *      在事务执行过程中，如果遇到任何错误（比如违反约束、断电等），导致事务无法继续正常执行，就进入了这个状态。此时事务不能再被提交)
     *      ->Aborted / Rolled Back (已中止 / 已回滚状态)
     *spring 的TransactionStatus 对象主要包含以下关键信息：
     * isNewTransaction(): 是否是一个新的事务？
     * 这是理解传播机制的关键。
     * 如果返回true，意味着当前方法是这个事务的“所有者”，它负责最终的提交或回滚。这通常对应于REQUIRED（当没有外部事务时）或REQUIRES_NEW传播机制。
     * 如果返回false，意味着当前方法只是加入了一个已经存在的外部事务。它不能自己决定提交，只能“投票”决定是否要回滚。
     * isRollbackOnly(): 是否被标记为只能回滚？
     * 一个事务可以被程序显式地标记为“只能回滚”（通过调用status.setRollbackOnly()）。
     * 即使内部方法没有抛出异常，只要它将事务标记为rollback-only，那么当最外层的事务结束时，它也必须回滚。这提供了一种更灵活的事务控制方式。
     * isCompleted(): 事务是否已经完成？
     * 当事务被提交或回滚后，这个状态会变为true。
     */

    public void testCreate(){
        this.creatOrder();
    }


    @Transactional(rollbackFor = Exception.class)
    public void creatOrder() {
        Order order = new Order();
        Integer orderId = 222;
        order.setId(orderId);
        order.setCustomerName("tom");
        order.setOrderDate(LocalDate.now());
        orderMapper.insert(order);
        this.MethodB(orderId);
    }
    public void MethodB(Integer orderId){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setProductName(null);
        orderDetail.setPrice(new BigDecimal("10"));
        orderDetail.setQuantity(1);
        orderDetailMapper.insert(orderDetail);
    }


}
