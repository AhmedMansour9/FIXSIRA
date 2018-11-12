package com.example.ic.fixera.View;

import com.example.ic.fixera.Model.Sparts_Details;

import java.util.List;

/**
 * Created by ic on 10/7/2018.
 */

public interface Sparts_View {
    void ListSparts(List<Sparts_Details> list);
    void ErrorSparts();
}
