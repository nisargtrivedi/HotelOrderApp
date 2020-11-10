package app.nisargapp.hotelorderapp.Model;

import com.google.gson.annotations.SerializedName;
import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;

@Table(name = "tblcart")
public class tblCart extends Entity {

    @TableField(name = "name",datatype = DATATYPE_STRING)
    public String NAME;

    @TableField(name = "itemid",datatype = DATATYPE_STRING)
    public String ITEMID;

    @TableField(name = "qty",datatype = DATATYPE_INTEGER)
    public int QUANTITY;

    @TableField(name = "price",datatype = DATATYPE_INTEGER)
    public int PRICE;

    @TableField(name = "size",datatype = DATATYPE_STRING)
    public String SIZE;

    @TableField(name = "img",datatype = DATATYPE_STRING)
    public String IMAGE;


    @TableField(name = "extradetails",datatype = DATATYPE_STRING)
    public String EXTRA_DETAILS;

    @TableField(name = "extraamt",datatype = DATATYPE_INTEGER)
    public int EXTRA_AMT;


}
