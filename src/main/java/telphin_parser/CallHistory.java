package telphin_parser;

import com.google.gson.annotations.SerializedName;

import java.awt.image.CropImageFilter;
import java.util.LinkedList;
import java.util.List;

public class CallHistory{
    @SerializedName("bridged_domain")
    private String bridgedDomain;
    @SerializedName("bridged_duration")
    private long bridgedDuration;
    @SerializedName("bridged_time_gmt")
    private String bridgedTimeGmt;
    @SerializedName("bridged_username")
    private String bridgedUsername;
    @SerializedName("call_uuid")
    private String callUuid;
    @SerializedName("cdr")
    private List<CdrResponse> cdr =new LinkedList<CdrResponse>();
    @SerializedName("client_owner_id")
    private long clientOwnerId;
    @SerializedName("did_domain")
    private String didDomain;
    @SerializedName("did_number")
    private String didNumber;
    @SerializedName("duration")
    private long duration;
    @SerializedName("ext_number_reg")
    private String extNumberReg;
    @SerializedName("extension_group_owner_id")
    private long extensionGroupOwnerId;
    @SerializedName("extension_id")
    private long extensionId;
    @SerializedName("extension_name")
    private String extensionName;
    @SerializedName("extension_type")
    private String extensionType;
    @SerializedName("flow")
    private String flow;
    @SerializedName("from_domain")
    private String fromDomain;
    @SerializedName("from_screen_name")
    private String fromScreenName;
    @SerializedName("from_username")
    private String fromUsername;
    @SerializedName("hangup_cause")
    private String hangupCause;
    @SerializedName("hangup_disposition")
    private String hangupDisposition;
    @SerializedName("hangup_time_gmt")
    private String hangupTimeGmt;
    @SerializedName("init_time_gmt")
    private String initTimeGmt;
    @SerializedName("result")
    private String result;
    @SerializedName("start_time_gmt")
    private String startTimeGmt;
    @SerializedName("to_domain")
    private String toDomain;
    @SerializedName("to_username")
    private String toUsername;
	public CallHistory(){
		}
}