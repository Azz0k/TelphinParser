package telphin_parser;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ListCallHistoryGet {
    @SerializedName("call_history")
    private List<CallHistory> callHistory= new LinkedList<>();
    @SerializedName("order")
    private String order;
    @SerializedName("page")
    private long page;
    @SerializedName("per_page")
    private long per_page;

    public ListCallHistoryGet(){

    }
}
