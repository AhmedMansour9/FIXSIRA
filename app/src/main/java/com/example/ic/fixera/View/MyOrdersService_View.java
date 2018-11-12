package com.example.ic.fixera.View;

import com.example.ic.fixera.Model.MyOrders_Service;

import java.util.List;

/**
 * Created by Ahmed on 15/10/2018.
 */

public interface MyOrdersService_View {
    void ShowMyOrdersService(List<MyOrders_Service> list);
     void Error();
}
