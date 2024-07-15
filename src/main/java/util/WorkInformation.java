package util;

import Converter.ConverterValuteCurrencyExchange;
import DataBase.Database;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dto.ValCurs;
import repository.CurrencyExchangeRepository;
import repository.CurrencyExchangeRepositorySqlitelmpi;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkInformation {
    public void start(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Database database = new Database();
        database.getConnection();
        try {
            getResponse(dateFormat.format(date));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        deserializeFromXML("src\\main\\resources\\XML_fan.xml");
        database.closeConnection();
    }
    public void deserializeFromXML(String fileName) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String readContent = new String(Files.readAllBytes(Paths.get(fileName)));
            ValCurs deserializedData = xmlMapper.readValue(readContent, ValCurs.class);
            ConverterValuteCurrencyExchange converterValuteCurrencyExchange = new ConverterValuteCurrencyExchange();

            CurrencyExchangeRepository currencyExchangeRepository = new CurrencyExchangeRepositorySqlitelmpi();
            currencyExchangeRepository.deleteAll();
            for(int i = 0; i < deserializedData.getValuteList().size(); i++){
                currencyExchangeRepository.insert(converterValuteCurrencyExchange.toDomain(deserializedData.getValuteList().get(i)));
            }


        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void getResponse(String dateNow) throws IOException{
        try {
            String url = "https://www.cbr.ru/scripts/XML_daily.asp?date_req="+dateNow;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "CP1251"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = reader.readLine()) != null){
                response.append(inputLine);
            }
            reader.close();

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src\\main\\resources\\XML_fan.xml"), "UTF-8"));
            try {
                out.write(response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                out.close();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
