package telphin_parser;

import com.google.gson.annotations.SerializedName;

public class CdrResponse{

    @SerializedName("application")
    private String application;
    @SerializedName("call_uuid")
    private String callUuid;
    @SerializedName("client_owner_id")
    private long clientOwnerId;
    @SerializedName("dest_domain")
    private String destDomain;
    @SerializedName("dest_number")
    private String destNumber;
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
    @SerializedName("record_file_size")
    private long recordFileSize;
    @SerializedName("record_uuid")
    private String recordUuid;
    @SerializedName("result")
    private String result;
    @SerializedName("source_domain")
    private String sourceDomain;
    @SerializedName("source_number")
    private String sourceNumber;
    @SerializedName("start_time_gmt")
    private String startTimeGmt;
    @SerializedName("to_domain")
    private String toDomain;
    @SerializedName("to_username")
    private String toUsername;
    public CdrResponse(){
            }

    public long getExtensionId() {
        return extensionId;
    }
}