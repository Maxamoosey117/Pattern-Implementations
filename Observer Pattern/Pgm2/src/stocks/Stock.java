package stocks;

/**
 * Stock with the following fields:
 * Company
 * Ticker Symbol
 * Current Price
 * $ Change
 * % Change
 * YTD % Chg
 * 52-Week High
 * 52-Week Low
 * P/E Ratio
 */
public class Stock {
    private String company;
    private String tickerSymbol;
    private double currentPrice;
    private double change;
    private double percentChange;
    private double ytdPercentChange;
    private double week52High;
    private double week52Low;
    private double peRatio;


    public Stock(String company, String tickerSymbol, double currentPrice, double change, double percentChange, double ytdPercentChange, double week52High, double week52Low, double peRatio) {
        this.company = company;
        this.tickerSymbol = tickerSymbol;
        this.currentPrice = currentPrice;
        this.change = change;
        this.percentChange = percentChange;
        this.ytdPercentChange = ytdPercentChange;
        this.week52High = week52High;
        this.week52Low = week52Low;
        this.peRatio = peRatio;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

    public double getYtdPercentChange() {
        return ytdPercentChange;
    }

    public void setYtdPercentChange(double ytdPercentChange) {
        this.ytdPercentChange = ytdPercentChange;
    }

    public double getWeek52High() {
        return week52High;
    }

    public void setWeek52High(double week52High) {
        this.week52High = week52High;
    }

    public double getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(double week52Low) {
        this.week52Low = week52Low;
    }

    public double getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(double peRatio) {
        this.peRatio = peRatio;
    }

    public String toString() {
        String data = "";
        data += company + ": " + tickerSymbol + " " + currentPrice + " " + change + " " + percentChange + " " + ytdPercentChange + " " + week52High + " " + week52Low + " " + peRatio;
        return data;
    }
}
