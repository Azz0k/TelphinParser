package telphin_parser;

import java.awt.image.CropImageFilter;
import java.util.LinkedList;
import java.util.List;

public class CallHistory{
    private String bridgedDomain;
    private long bridgedDuration;
    private String bridgedTimeGmt;
    private String bridgedUsername;
    private String callUuid;
    private List<CdrResponse> cdr =new LinkedList<CdrResponse>();
    private long clientOwnerId;
    private String didDomain;
    private String didNumber;
    private long duration;
    private String extNumberReg;
    private long extensionGroupOwnerId;
    private long extensionId;
    private String extensionName;
    private String extensionType;
    private String flow;
    private String fromDomain;
    private String fromScreenName;
    private String fromUsername;
    private String hangupCause;
    private String hangupDisposition;
    private String hangupTimeGmt;
    private String initTimeGmt;
    private String result;
    private String startTimeGmt;
    private String toDomain;
    private String toUsername;
	public CallHistory(){
		}
}