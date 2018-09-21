package telphin_parser;

public class Manager extends Numbers{

    private String name;

    private long extensionId=0;

    public Manager(String name, String number,long extensionId){
        super(number);
        this.name=name;
        //this.number=number;
        this.extensionId=extensionId;
    }



    public String getName() {
        return name;
    }



    public long getExtensionId() {
        return extensionId;
    }





}
