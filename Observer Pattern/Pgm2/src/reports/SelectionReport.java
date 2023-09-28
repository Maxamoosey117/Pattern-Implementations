package reports;

import stocks.LocalStocks;
import stocks.Snapshot;
import stocks.Stock;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Observer;

public class SelectionReport implements Observer {

    private String filename;

    public SelectionReport(String filename) throws IOException {
        FileHandler.createFile(filename);
        // clear file before writing to it
        FileHandler.clearFile(filename);
        this.filename = filename;
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        Snapshot snapshot = null;
        if (o instanceof LocalStocks) {
            snapshot = (Snapshot) arg;
        }
        String output = snapshot.getDate() + "\n";
        for (Stock stock : snapshot.getStocks()) {
            String tickerSymbol = stock.getTickerSymbol();
            if (tickerSymbol.equals("ALL") || tickerSymbol.equals("BA") || tickerSymbol.equals("BC") ||
                    tickerSymbol.equals("GBEL") || tickerSymbol.equals("KFT") || tickerSymbol.equals("MCD") ||
                    tickerSymbol.equals("TR") || tickerSymbol.equals("WAG")) {
                DecimalFormat df = new DecimalFormat("#0.00");
                output += "Company: " + String.format("%-25s", stock.getCompany()) + " Ticker Symbol: " + String.format("%-5s",tickerSymbol) +
                        " Current Price: $" + df.format(stock.getCurrentPrice()) +
                        ", Price Change: " + df.format(stock.getChange()) +
                        ", Percent Change: " + df.format(stock.getPercentChange()) +
                        ", YTD % Chg: " + df.format(stock.getYtdPercentChange()) +
                        ", 52-Week High: $" + df.format(stock.getWeek52High()) +
                        ", 52-Week Low: $" + df.format(stock.getWeek52Low()) +
                        ", P/E Ratio: " + (stock.getPeRatio() == Double.NaN ? "-" : df.format(stock.getPeRatio())) + "\n";
            }
        }
        FileHandler.writeLineToFile(filename, output);
    }
}
