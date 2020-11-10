package app.nisargapp.hotelorderapp.Adapter;

import app.nisargapp.hotelorderapp.Model.tblCart;

public interface OnCartItemManagement {

    void OnAdd(tblCart cart);
    void OnMinus(tblCart cart);
    void OnDelete(tblCart cart);
}
