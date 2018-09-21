package telphin_parser;

public class Numbers {
    private long amountOutgoingCalls=0;
    private String number;
    public void adAmountOutgoingCalls(long amountOutgoingCalls){
        this.amountOutgoingCalls+=amountOutgoingCalls;
    }
    public String getNumber() {
        return number;
    }
    public long getAmountOutgoingCalls() {
        return amountOutgoingCalls;
    }
    public Numbers(String number){
        this.number=number;
    }
}
