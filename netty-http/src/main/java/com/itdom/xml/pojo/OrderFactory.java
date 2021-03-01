package com.itdom.xml.pojo;

public class OrderFactory {


    public static Order create(long orderID){

        Order order = new Order();
        order.setOrderNumber(orderID);
        order.setTotal(9999.999f);
        Address address = new Address();
        address.setCity("深圳市");
        address.setCountry("中国");
        address.setPostCode("518000");
        order.setBillTo(address);
        Customer customer = new Customer();
        customer.setCustomerNumber(orderID);
        customer.setFirstName("chen");
        customer.setLastName("dom");
        order.setCustomer(customer);
        order.setShipping(Shipping.INTERNATIONAL_MAIL);
        order.setShipTo(address);
        return order;
    }
}
