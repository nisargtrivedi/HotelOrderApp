package app.nisargapp.hotelorderapp.Model;

import com.google.gson.annotations.SerializedName;
import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;

@Table(name = "tbltable")
public class tblTable extends Entity {

    @SerializedName("table_id")
    @TableField(name = "tableid",datatype = DATATYPE_STRING)
    public String TABLE_ID="";

    @SerializedName("table_no")
    @TableField(name = "tablename",datatype = DATATYPE_STRING)
    public String TABLE_NAME="";

    @SerializedName("booked_or_not")
    @TableField(name = "booked_or_not",datatype = DATATYPE_STRING)
    public String TABLE_STATUS="";

}
