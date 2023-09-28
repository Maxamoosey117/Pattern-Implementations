package reports;

import stocks.LocalStocks;
import stocks.Snapshot;
import stocks.Stock;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

public class CloseToHighLowReport implements Observer {
    private String filename;

    public CloseToHighLowReport(String filename) throws IOException {
        FileHandler.createFile(filename);
        // clear file before writing to it
        FileHandler.clearFile(filename);
        this.filename = filename;
    }

    @Override
    public void update(Observable o, Object arg) {
        Snapshot snapshot = null;
        if (o instanceof LocalStocks) {
            snapshot = (Snapshot) arg;
        }
        String output = snapshot.getDate() + "\n";

        for (Stock stock : snapshot.getStocks()) {
            double high = stock.getWeek52High();
            double low = stock.getWeek52Low();
            double currentPrice = stock.getCurrentPrice();

            double highDiff = Math.abs(100 - (100 * (currentPrice / high)));
            double lowDiff = Math.abs(100 - (100 * (currentPrice / low)));
            if (highDiff <= 1 || lowDiff <= 1) {
                DecimalFormat df = new DecimalFormat("00.00");
                output += String.format("%5s", stock.getTickerSymbol()) + ": $" + df.format(stock.getCurrentPrice()) + ", High: $" + df.format(stock.getWeek52High()) + ", Low: $" + df.format(stock.getWeek52Low()) + "\n";
            }
        }

        FileHandler.writeLineToFile(filename, output);
    }
}
