package reports;

import stocks.LocalStocks;
import stocks.Snapshot;
import stocks.Stock;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

public class AverageReport implements Observer {

    private String filename;

    public AverageReport(String filename) {
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
        String output = "";
        output += String.format("%27s",snapshot.getDate()) + ", Average price: $";
        double sum = 0;
        for (Stock stock : snapshot.getStocks()) {
            sum += stock.getCurrentPrice();
        }
        double average = sum / snapshot.getStocks().size();

        // Round the average to two decimal places
        DecimalFormat df = new DecimalFormat("00.00"); // Create a new DecimalFormat with two decimal places
        df.setRoundingMode(RoundingMode.HALF_UP);

        output += df.format(average);

        FileHandler.writeLineToFile(filename, output);
    }
}
