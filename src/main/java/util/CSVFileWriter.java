package util;

import repository.CurrencyExchangeRepositorySqlitelmpi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVFileWriter {
    private final BufferedWriter writer;
    private final String delimiter;

    public CSVFileWriter(String file, String delimiter) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(file));
        this.delimiter = delimiter;
    }

    public void writeStudent(CurrencyExchangeRepositorySqlitelmpi currencyExchangeRepositorySqlitelmpi) throws IOException {
        final StringBuilder s = new StringBuilder();
        for (int i = 0; i < currencyExchangeRepositorySqlitelmpi.findAll().size(); i++){
            s.delete(0, s.length());
            s.append(currencyExchangeRepositorySqlitelmpi.findAll().get(i).getId());
            s.append(delimiter);
            s.append(currencyExchangeRepositorySqlitelmpi.findAll().get(i).getValue());
            s.append(delimiter);
            s.append(currencyExchangeRepositorySqlitelmpi.findAll().get(i).getNominal());
            s.append(delimiter);
            s.append(currencyExchangeRepositorySqlitelmpi.findAll().get(i).getCurrencyName());
            s.append(delimiter);
            s.append(currencyExchangeRepositorySqlitelmpi.findAll().get(i).getCurrencyCode());
            s.append(delimiter);
            s.append(currencyExchangeRepositorySqlitelmpi.findAll().get(i).getDate());
            s.append(delimiter);
            s.append("\n");

            final String line = s.toString();
            writer.write(line);
        }

    }

    public void close() throws IOException {
        writer.close();
    }
}
