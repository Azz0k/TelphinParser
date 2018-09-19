package telphin_parser;

public class Manager {
    private long amountOutgoingCalls=0;
    private String name;
    private String number;
    private long extensionId=0;
    private String MTSNumber;
    private long amountMTSOutgoingCalls=0;
    public Manager(String name, String number,long extensionId){
        this.name=name;
        this.number=number;
        this.extensionId=extensionId;
    }

    public long getAmountOutgoingCalls() {
        return amountOutgoingCalls;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public long getExtensionId() {
        return extensionId;
    }


    public void adAmountOutgoingCalls(long amountOutgoingCalls){
        this.amountOutgoingCalls+=amountOutgoingCalls;
    }


}
