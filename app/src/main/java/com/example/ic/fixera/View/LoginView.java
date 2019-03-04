package com.example.ic.fixera.View;

/**
 * Created by HP on 04/09/2018.
 */

public interface LoginView {

    void openMain(String usertoken);
    void OpenRole( String usertoken);
    void showError(String error);
    void Invalidemail(String password);
}
