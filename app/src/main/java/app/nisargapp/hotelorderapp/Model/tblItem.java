package app.nisargapp.hotelorderapp.Model;

import com.google.gson.annotations.SerializedName;
import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;

import java.io.Serializable;

@Table(name = "tblitem")
public class tblItem extends Entity implements Serializable {


    @SerializedName("item_id")
    @TableField(name = "item_id",datatype = DATATYPE_STRING)
    public String ITEM_ID="";

    @SerializedName("item_name")
    @TableField(name = "item_name",datatype = DATATYPE_STRING)
    public String ITEM_NAME="";

    @SerializedName("price")
    @TableField(name = "item_price",datatype = DATATYPE_STRING)
    public String ITEM_PRICE="";

    @SerializedName("image")
    @TableField(name = "item_image",datatype = DATATYPE_STRING)
    public String ITEM_IMAGE="";

    @SerializedName("size")
    @TableField(name = "item_size",datatype = DATATYPE_STRING)
    public String ITEM_SIZE="";

    @SerializedName("description")
    @TableField(name = "item_description",datatype = DATATYPE_STRING)
    public String ITEM_DESCRIPTION="";

    @SerializedName("category_id")
    @TableField(name = "item_category",datatype = DATATYPE_STRING)
    public String ITEM_CATEGORY="";

}
