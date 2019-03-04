package com.example.ic.fixera.View;

import com.example.ic.fixera.Model.Cart;

import java.util.List;

/**
 * Created by Ahmed on 14/10/2018.
 */

public interface ShowCart_View {
    void ShowCart(List<Cart> list);
    void ShowTotalprice(String price);
    void Error();
    void NoProduct();
}
