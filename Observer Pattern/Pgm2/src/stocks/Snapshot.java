package stocks;

import java.util.ArrayList;
import java.util.List;

public class Snapshot {
    private List<Stock> stocks;
    private String date;

    public Snapshot(String date) {
        stocks = new ArrayList<Stock>();
        this.date = date;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public String getDate() {
        return date;
    }

    public void print() {
        System.out.println("Snapshot for " + date);
        for (Stock stock : stocks) {
            System.out.println(stock.toString());
        }
    }

}
