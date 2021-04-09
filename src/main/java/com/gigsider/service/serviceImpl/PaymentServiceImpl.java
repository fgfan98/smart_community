package com.gigsider.service.serviceImpl;

import com.gigsider.dao.PaymentDao;
import com.gigsider.po.Payment;
import com.gigsider.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public boolean addPayment(Payment payment) {
        return paymentDao.insertPayment(payment);
    }

    @Override
    public boolean delPayment(int id) {
        return paymentDao.deletePayment(id);
    }

    @Override
    public boolean upPayment(Payment payment) {
        return paymentDao.updatePayment(payment);
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentDao.queryAllPayment();
    }

    @Override
    public List<Payment> getPaymentPage(int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return paymentDao.queryPaymentPage(data);
    }

    @Override
    public List<Payment> getPaymentLike(String data) {
        return paymentDao.queryPaymentLike(data);
    }

    @Override
    public List<Payment> paymentLikePage(String like, int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("like", like);
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return paymentDao.paymentLikePage(data);
    }

    @Override
    public List<Payment> getPaymentById(int id) {
        return paymentDao.queryPaymentById(id);
    }

    @Override
    public List<Payment> getPaymentByUser(String user) {
        return paymentDao.queryPaymentByUser(user);
    }

    @Override
    public List<Payment> paymentUserPage(String user, int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("user", user);
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return paymentDao.paymentUserPage(data);
    }

    @Override
    public boolean setPaid(int id, int paid) {
        Map<String,Object> data = new HashMap<>();
        data.put("id", id);
        data.put("paid", paid);
        return paymentDao.setPaid(data);
    }

    @Override
    public List<Payment> getUnPaid(String user, String item) {
        Map<String,Object> data = new HashMap<>();
        data.put("user", user);
        data.put("item", item);
        return paymentDao.queryUnPaid(data);
    }
}
