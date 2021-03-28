package com.gigsider.dao;

import com.gigsider.po.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentDao {

    //添加账单
    public boolean insertPayment(Payment payment);

    //删除账单
    public boolean deletePayment(int id);

    //更新账单
    public boolean updatePayment(Payment payment);

    //获取所有账单
    public List<Payment> queryAllPayment();

    //分页查找
    public List<Payment> queryPaymentPage(Map<String,Object> data);

    //模糊搜索
    public List<Payment> queryPaymentLike(String data);

    //模糊搜索分页
    public List<Payment> paymentLikePage(Map<String,Object> data);

    //通过 id 查询
    public List<Payment> queryPaymentById(int id);

    //通过 user 查询
    public List<Payment> queryPaymentByUser(String user);

    // user 分页查询
    public List<Payment> paymentUserPage(Map<String,Object> data);

}
