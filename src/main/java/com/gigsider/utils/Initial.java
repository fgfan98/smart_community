package com.gigsider.utils;

import com.gigsider.po.*;
import com.gigsider.service.*;
import com.gigsider.service.serviceImpl.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class Initial implements InitializingBean {

    @Autowired
    private DataService dataService;

    @Override
    public void afterPropertiesSet() throws Exception {

        RunnableTarget.month = Integer.parseInt(dataService.getData("pay_month").getValue());

        //创建线程工作体对象
        RunnableTarget tt = new RunnableTarget();

        //创建线程体的线程对象
        Thread t = new Thread(tt);

        //启动线程
        t.start();
    }
}

class RunnableTarget implements Runnable{	//本身不是线程，是线程的工作体

    static public int month;
    private UserService userService = SpringContextHolder.getBean(UserServiceImpl.class);
    private DataService dataService = SpringContextHolder.getBean(DataServiceImpl.class);
    private ParkingService parkingService = SpringContextHolder.getBean(ParkingServiceImpl.class);
    private HouseService houseService = SpringContextHolder.getBean(HouseServiceImpl.class);
    private PaymentService paymentService = SpringContextHolder.getBean(PaymentServiceImpl.class);

    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        int current_month = -1;
        while (true) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            current_month = calendar.get(Calendar.MONTH) + 1;
            String time = calendar.get(Calendar.YEAR) + "年" + current_month + "月" + calendar.get(Calendar.DATE) + "日 " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
            if (current_month != month) {
                List<User> users = userService.getAllUser();
                List<Parking> parkings = parkingService.getRentParking();
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    if (user.getHouse() != null && user.getHouse().length() != 0) {
                        double propertyFee = Double.valueOf(dataService.getData("property_fee").getValue());
                        House house = houseService.getHouseByHouseId(user.getHouse()).get(0);
                        Payment payment = new Payment();
                        payment.setUser(user.getUser_name());
                        payment.setItem("物业费");
                        payment.setItem_id(user.getHouse());
                        payment.setCost(propertyFee*Integer.parseInt(house.getArea()));
                        payment.setTime(time);
                        paymentService.addPayment(payment);
                        System.out.println("新增账单：物业费，" + user.getUser_name() + "，" + user.getHouse());
                    }
                }
                for (int i = 0; i < parkings.size(); i++) {
                    Parking parking = parkings.get(i);
                    User user = userService.getUserByLicenseNum(parking.getLicense_num()).get(0);
                    Payment payment = new Payment();
                    payment.setUser(user.getUser_name());
                    payment.setItem("车位租金");
                    payment.setItem_id(parking.getParking_id());
                    payment.setCost(parking.getRent());
                    payment.setTime(time);
                    paymentService.addPayment(payment);
                    System.out.println("新增账单：车位租金，" + user.getUser_name() + "，" + parking.getParking_id());
                }

                Data data = new Data();
                data.setKey("pay_month");
                data.setValue(current_month+"");
                dataService.upData(data);
                month = current_month;
            }
        }
    }

}