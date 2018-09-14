package telphin_parser;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CallHistoryGet{
    private List<CallHistory> callHistory = new LinkedList<CallHistory>();
    private String order;
    private long page;
    private long perPage;
	public CallHistoryGet(){
		}
	public List<CallHistory> getHistory() {
	    return new LinkedList<CallHistory>(callHistory);
    }
    public void addHistory(CallHistory callHistory){
	    this.callHistory.add(callHistory);
    }
    public  String getOrder(){
	    return order;
    }
    public void setOrder(String order){
	    this.order=order;
    }

    public long getPage() {
        return page;
    }
    public long getPerPage(){
	    return perPage;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public void setPerPage(long perPage) {
        this.perPage = perPage;
    }
}