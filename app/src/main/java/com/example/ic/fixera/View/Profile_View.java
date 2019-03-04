package com.example.ic.fixera.View;

import com.example.ic.fixera.Model.Profilee;

import java.util.List;

/**
 * Created by Ahmed on 15/10/2018.
 */

public interface Profile_View {

    void getProfile(String user,String Email,String userphoto,String phone,String carmodel,String caryear);
    void Error();
}
