package subway.subwayalarm.utility;


public class SearchHistoryItem {
    private int bookMarkImg;
    private String stationName;
    private String trainLineInfo;

    public SearchHistoryItem(int bookMarkImg, String stationName, String trainLineInfo) {
        this.bookMarkImg = bookMarkImg;
        this.stationName = stationName;
        this.trainLineInfo = trainLineInfo;
    }

    public int getBookMarkImg() {
        return bookMarkImg;
    }

    public String getStationName() {
        return stationName;
    }

    public String getTrainLineInfo() {
        return trainLineInfo;
    }
}