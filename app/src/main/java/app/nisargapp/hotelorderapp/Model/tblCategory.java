package app.nisargapp.hotelorderapp.Model;

import com.google.gson.annotations.SerializedName;
import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;

@Table(name = "tblcategory")
public class tblCategory extends Entity {

    @SerializedName("cat_id")
    @TableField(name = "catid",datatype = DATATYPE_STRING)
    public String CATEGORY_ID="";

    @SerializedName("name")
    @TableField(name = "catname",datatype = DATATYPE_STRING)
    public String CATEGORY_NAME="";

}
