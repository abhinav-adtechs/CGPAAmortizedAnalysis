package abhinav.hackdev.co.amortizedanalysis;


public class DataUpdateEvent {

    private float dataValue ;
    private int dataCredits ;
    private int dataIndex ;

    public DataUpdateEvent(float dataValue, int dataCredits, int dataIndex) {
        this.dataValue = dataValue;
        this.dataCredits = dataCredits ;
        this.dataIndex = dataIndex;
    }

    public float getDataValue() {
        return dataValue;
    }

    public void setDataValue(float dataValue) {
        this.dataValue = dataValue;
    }

    public int getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(int dataIndex) {
        this.dataIndex = dataIndex;
    }

    public int getDataCredits() {
        return dataCredits;
    }

    public void setDataCredits(int dataCredits) {
        this.dataCredits = dataCredits;
    }
}
