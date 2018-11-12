package com.example.ic.fixera.View;

import com.example.ic.fixera.Model.Info;
import com.example.ic.fixera.Model.OrdersProducts;

import java.util.List;

/**
 * Created by Ahmed on 17/10/2018.
 */

public interface ProductsById_View {
    void GetDetailsOrder(Info list);
    void  GetProducts(List<OrdersProducts> list);
    void  Error();
}
