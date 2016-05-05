package abhinav.hackdev.co.amortizedanalysis;


public class DataUpdateEvent {

    private float dataValue ;
    private int dataIndex ;

    public DataUpdateEvent(float dataValue, int dataIndex) {
        this.dataValue = dataValue;
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
}
