package app.nisargapp.hotelorderapp.Model;

import java.io.Serializable;

public class tblToppings implements Serializable {

    public String Name="0";
    public int Price=0;
    public int isSelected=0;

    public tblToppings(String name, int price) {
        Name = name;
        Price = price;
    }
}
