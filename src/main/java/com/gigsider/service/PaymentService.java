package com.gigsider.service;

import com.gigsider.po.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentService {

    //添加账单
    public boolean addPayment(Payment payment);

    //删除账单
    public boolean delPayment(int id);

    //更新账单
    public boolean upPayment(Payment payment);

    //获取所有账单
    public List<Payment> getAllPayment();

    //分页查找
    public List<Payment> getPaymentPage(int page, int limit);

    //模糊搜索
    public List<Payment> getPaymentLike(String data);

    //模糊搜索分页
    public List<Payment> paymentLikePage(String like, int page, int limit);

    //通过 id 查询
    public List<Payment> getPaymentById(int id);

    //通过 user 查询
    public List<Payment> getPaymentByUser(String user);

    // user 分页查询
    public List<Payment> paymentUserPage(String user, int page, int limit);

}
