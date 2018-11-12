package com.example.ic.fixera.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed on 17/10/2018.
 */

public class Info {

    @SerializedName("_billing_title")
    @Expose
    private String billingTitle;
    @SerializedName("_billing_first_name")
    @Expose
    private String billingFirstName;
    @SerializedName("_billing_last_name")
    @Expose
    private String billingLastName;
    @SerializedName("_billing_company")
    @Expose
    private Object billingCompany;
    @SerializedName("_billing_email")
    @Expose
    private String billingEmail;
    @SerializedName("_billing_phone")
    @Expose
    private String billingPhone;
    @SerializedName("_billing_fax")
    @Expose
    private Object billingFax;
    @SerializedName("_billing_country")
    @Expose
    private String billingCountry;
    @SerializedName("_billing_address_1")
    @Expose
    private String billingAddress1;
    @SerializedName("_billing_address_2")
    @Expose
    private String billingAddress2;
    @SerializedName("_billing_city")
    @Expose
    private Object billingCity;
    @SerializedName("_billing_postcode")
    @Expose
    private String billingPostcode;
    @SerializedName("_shipping_title")
    @Expose
    private String shippingTitle;
    @SerializedName("_shipping_first_name")
    @Expose
    private String shippingFirstName;
    @SerializedName("_shipping_last_name")
    @Expose
    private String shippingLastName;
    @SerializedName("_shipping_company")
    @Expose
    private Object shippingCompany;
    @SerializedName("_shipping_email")
    @Expose
    private String shippingEmail;
    @SerializedName("_shipping_phone")
    @Expose
    private String shippingPhone;
    @SerializedName("_shipping_fax")
    @Expose
    private Object shippingFax;
    @SerializedName("_shipping_country")
    @Expose
    private String shippingCountry;
    @SerializedName("_shipping_address_1")
    @Expose
    private String shippingAddress1;
    @SerializedName("_shipping_address_2")
    @Expose
    private String shippingAddress2;
    @SerializedName("_shipping_city")
    @Expose
    private Object shippingCity;
    @SerializedName("_shipping_postcode")
    @Expose
    private String shippingPostcode;
    @SerializedName("_order_id")
    @Expose
    private Integer orderId;
    @SerializedName("_order_date")
    @Expose
    private String orderDate;
    @SerializedName("_order_currency")
    @Expose
    private String orderCurrency;
    @SerializedName("_customer_ip_address")
    @Expose
    private String customerIpAddress;
    @SerializedName("_customer_user_agent")
    @Expose
    private String customerUserAgent;
    @SerializedName("_member")
    @Expose
    private Member member;
    @SerializedName("_order_shipping_cost")
    @Expose
    private String orderShippingCost;
    @SerializedName("_final_order_shipping_cost")
    @Expose
    private String finalOrderShippingCost;
    @SerializedName("_order_shipping_method")
    @Expose
    private String orderShippingMethod;
    @SerializedName("_payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("_payment_method_title")
    @Expose
    private String paymentMethodTitle;
    @SerializedName("_order_tax")
    @Expose
    private String orderTax;
    @SerializedName("_final_order_tax")
    @Expose
    private String finalOrderTax;
    @SerializedName("_order_total")
    @Expose
    private String orderTotal;
    @SerializedName("_final_order_total")
    @Expose
    private String finalOrderTotal;
    @SerializedName("_order_notes")
    @Expose
    private String orderNotes;
    @SerializedName("_order_status")
    @Expose
    private String orderStatus;
    @SerializedName("_order_discount")
    @Expose
    private String orderDiscount;
    @SerializedName("_final_order_discount")
    @Expose
    private String finalOrderDiscount;
    @SerializedName("_order_coupon_code")
    @Expose
    private String orderCouponCode;
    @SerializedName("_is_order_coupon_applyed")
    @Expose
    private String isOrderCouponApplyed;
    @SerializedName("_ordered_items")
    @Expose
    private List<OrdersProducts> orderedItems = null;
    @SerializedName("_order_history")
    @Expose
    private List<Object> orderHistory = null;

    public String getBillingTitle() {
        return billingTitle;
    }

    public void setBillingTitle(String billingTitle) {
        this.billingTitle = billingTitle;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public Object getBillingCompany() {
        return billingCompany;
    }

    public void setBillingCompany(Object billingCompany) {
        this.billingCompany = billingCompany;
    }

    public String getBillingEmail() {
        return billingEmail;
    }

    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public Object getBillingFax() {
        return billingFax;
    }

    public void setBillingFax(Object billingFax) {
        this.billingFax = billingFax;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingAddress1() {
        return billingAddress1;
    }

    public void setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
    }

    public String getBillingAddress2() {
        return billingAddress2;
    }

    public void setBillingAddress2(String billingAddress2) {
        this.billingAddress2 = billingAddress2;
    }

    public Object getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(Object billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPostcode() {
        return billingPostcode;
    }

    public void setBillingPostcode(String billingPostcode) {
        this.billingPostcode = billingPostcode;
    }

    public String getShippingTitle() {
        return shippingTitle;
    }

    public void setShippingTitle(String shippingTitle) {
        this.shippingTitle = shippingTitle;
    }

    public String getShippingFirstName() {
        return shippingFirstName;
    }

    public void setShippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
    }

    public String getShippingLastName() {
        return shippingLastName;
    }

    public void setShippingLastName(String shippingLastName) {
        this.shippingLastName = shippingLastName;
    }

    public Object getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(Object shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getShippingEmail() {
        return shippingEmail;
    }

    public void setShippingEmail(String shippingEmail) {
        this.shippingEmail = shippingEmail;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public Object getShippingFax() {
        return shippingFax;
    }

    public void setShippingFax(Object shippingFax) {
        this.shippingFax = shippingFax;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public Object getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(Object shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public String getCustomerIpAddress() {
        return customerIpAddress;
    }

    public void setCustomerIpAddress(String customerIpAddress) {
        this.customerIpAddress = customerIpAddress;
    }

    public String getCustomerUserAgent() {
        return customerUserAgent;
    }

    public void setCustomerUserAgent(String customerUserAgent) {
        this.customerUserAgent = customerUserAgent;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getOrderShippingCost() {
        return orderShippingCost;
    }

    public void setOrderShippingCost(String orderShippingCost) {
        this.orderShippingCost = orderShippingCost;
    }

    public String getFinalOrderShippingCost() {
        return finalOrderShippingCost;
    }

    public void setFinalOrderShippingCost(String finalOrderShippingCost) {
        this.finalOrderShippingCost = finalOrderShippingCost;
    }

    public String getOrderShippingMethod() {
        return orderShippingMethod;
    }

    public void setOrderShippingMethod(String orderShippingMethod) {
        this.orderShippingMethod = orderShippingMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethodTitle() {
        return paymentMethodTitle;
    }

    public void setPaymentMethodTitle(String paymentMethodTitle) {
        this.paymentMethodTitle = paymentMethodTitle;
    }

    public String getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(String orderTax) {
        this.orderTax = orderTax;
    }

    public String getFinalOrderTax() {
        return finalOrderTax;
    }

    public void setFinalOrderTax(String finalOrderTax) {
        this.finalOrderTax = finalOrderTax;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getFinalOrderTotal() {
        return finalOrderTotal;
    }

    public void setFinalOrderTotal(String finalOrderTotal) {
        this.finalOrderTotal = finalOrderTotal;
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(String orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public String getFinalOrderDiscount() {
        return finalOrderDiscount;
    }

    public void setFinalOrderDiscount(String finalOrderDiscount) {
        this.finalOrderDiscount = finalOrderDiscount;
    }

    public String getOrderCouponCode() {
        return orderCouponCode;
    }

    public void setOrderCouponCode(String orderCouponCode) {
        this.orderCouponCode = orderCouponCode;
    }

    public String getIsOrderCouponApplyed() {
        return isOrderCouponApplyed;
    }

    public void setIsOrderCouponApplyed(String isOrderCouponApplyed) {
        this.isOrderCouponApplyed = isOrderCouponApplyed;
    }

    public List<OrdersProducts> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrdersProducts> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public List<Object> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Object> orderHistory) {
        this.orderHistory = orderHistory;
    }
}