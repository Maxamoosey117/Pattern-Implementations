package stocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LocalStocks extends Observable {
    private Stock currStock;
    private List<Snapshot> snapList = new ArrayList<Snapshot>();
    private File tickerFile;

    public LocalStocks() {
        try {
            tickerFile = new File("Ticker.dat");
        } catch (Exception e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void startTicker() throws FileNotFoundException {
        Snapshot currShot = null;
        Scanner myReader = new Scanner(tickerFile);
        String line = null;
        while (myReader.hasNextLine()) {
            line = myReader.nextLine();

            // Check line
            if(line.equals("")){
                // check if the blank line is at the EOF
                if(!myReader.hasNextLine()){
                    break;
                }
                // snapshot is over, update observers
                setChanged();
                notifyObservers(currShot);
                snapList.add(currShot);
                continue;
            } else if (line.contains("Last updated")) {
                //create a new snapshot
                currShot = new Snapshot(line.substring(13));
                snapList.add(currShot);
                continue;
            }
            else {
                String[] fields = line.split("\\s+"); // Split the line based on one or more spaces
                int n = fields.length;
                String company = String.join(" ", Arrays.copyOfRange(fields, 0, n - 8)); // Combine the first n-8 fields into a single string for the company name
                String tickerSymbol = fields[n - 8];
                double currentPrice = Double.parseDouble(fields[n - 7]);
                double dollarChange = Double.parseDouble(fields[n - 6]);
                double percentChange = Double.parseDouble(fields[n - 5]);
                double ytdPercentChange = Double.parseDouble(fields[n - 4]);
                double fiftyTwoWeekHigh = Double.parseDouble(fields[n - 3]);
                double fiftyTwoWeekLow = Double.parseDouble(fields[n - 2]);

                double peRatio;
                if (fields[n - 1].equals("-")) {
                    peRatio = Double.NaN; // P/E ratio is not available
                } else {
                    peRatio = Double.parseDouble(fields[n - 1]);
                }

                currStock = new Stock(company, tickerSymbol, currentPrice, dollarChange, percentChange, ytdPercentChange, fiftyTwoWeekHigh, fiftyTwoWeekLow, peRatio);
                currShot.addStock(currStock);
            }
        }
        myReader.close();
    }
}
