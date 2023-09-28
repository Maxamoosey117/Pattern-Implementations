import reports.AverageReport;
import reports.CloseToHighLowReport;
import reports.SelectionReport;
import stocks.LocalStocks;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File reportsFolder = new File("Reports");
        if (!reportsFolder.exists()) {
            reportsFolder.mkdir();
            System.out.println("Created folder: Reports");
        }

        LocalStocks localStocks = new LocalStocks();
        localStocks.addObserver(new AverageReport("Reports/AverageReport.dat"));
        localStocks.addObserver(new CloseToHighLowReport("Reports/CloseToHighLowReport.dat"));
        localStocks.addObserver(new SelectionReport("Reports/SelectionReport.dat"));
        localStocks.startTicker();
    }
}