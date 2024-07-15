package GUI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import repository.CurrencyExchangeRepositorySqlitelmpi;
import util.CSVFileWriter;
import util.LocalDateSerializer;
import util.WorkInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class GuiCurrencyExchange extends JFrame {
    private WorkInformation workInformation =new WorkInformation();
    private JMenuBar fileSettings1 = new JMenuBar();
    private JMenu fileSettings2 = new JMenu("File");
    private JMenuItem newMenu1 = new JMenuItem("Import");
    private JMenu newMenu2 = new JMenu("Export");
    private JMenuItem newMenu3 = new JMenuItem("Csv File");
    private JMenuItem newMenu4 = new JMenuItem("JSON File");
    private JLabel jlabel = new JLabel();
    private JLabel jlabel2 = new JLabel();
    private JPanel panel = new JPanel(null);
    private JPanel panel2 = new JPanel(null);
    private Font font = new Font("Verdana", Font.PLAIN, 11);
    public GuiCurrencyExchange(){
        super("Currency_exchange");
        this.setBounds(200,100, 1000, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        fileSettings2.setFont(font);
        newMenu1.setFont(font);
        fileSettings2.add(newMenu1);
        fileSettings1.add(fileSettings2);
        newMenu2.setFont(font);
        fileSettings2.add(newMenu2);
        newMenu3.setFont(font);
        newMenu2.add(newMenu3);
        newMenu4.setFont(font);
        newMenu2.add(newMenu4);

        Image image = Toolkit.getDefaultToolkit().createImage("src\\main\\resources\\1658388961_8-rubley.gif");
        ImageIcon imageIcon = new ImageIcon(image);
        imageIcon.setImageObserver(jlabel);
        jlabel.setIcon(imageIcon);
        jlabel.setBounds(200, 50, 600, 500);
        this.setVisible(true);

        panel.add(jlabel);
        this.add(panel);
        jlabel2.setVisible(false);

        this.setJMenuBar(fileSettings1);
        this.setPreferredSize(new Dimension(1000, 800));
        this.pack();
    }
    public void doing(){
        newMenu1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                workInformation.start();
                panel.setVisible(false);
                Image image2 = Toolkit.getDefaultToolkit().createImage("src\\main\\resources\\1658388961_8-rubley#2.gif");
                ImageIcon imageIcon2 = new ImageIcon(image2);
                imageIcon2.setImageObserver(jlabel2);
                jlabel2.setIcon(imageIcon2);
                jlabel2.setBounds(800, 565, 180, 180);
                jlabel2.setVisible(true);
                add(jlabel2);
                importInfo();
            }
        });
        newMenu4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                recordJson();
                JOptionPane.showMessageDialog(null, "Json file Created");
            }
        });
        newMenu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CurrencyExchangeRepositorySqlitelmpi currencyExchangeRepositorySqlitelmpi = new CurrencyExchangeRepositorySqlitelmpi();
                    CSVFileWriter csvFileWriter = new CSVFileWriter("src\\main\\resources\\currncy_exchange.csv", " ");
                    csvFileWriter.writeStudent(currencyExchangeRepositorySqlitelmpi);
                    csvFileWriter.close();
                    JOptionPane.showMessageDialog(null, "Csv file Created");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
    public void importInfo(){
        CurrencyExchangeRepositorySqlitelmpi currencyExchangeRepositorySqlitelmpi = new CurrencyExchangeRepositorySqlitelmpi();
        String column[] = {"ID", "VALUE", "NOMINAL", "CURRENCY_NAME", "CURRENCY_CODE", "DATE"};
        String[][] data = new String[currencyExchangeRepositorySqlitelmpi.findAll().size()][6];
        JTable table1 = new JTable(data, column);
         for (int i = 0; i < currencyExchangeRepositorySqlitelmpi.findAll().size(); i++){
             data[i][0] = currencyExchangeRepositorySqlitelmpi.findAll().get(i).getId().toString();
             data[i][1] = currencyExchangeRepositorySqlitelmpi.findAll().get(i).getValue().toString();
             data[i][2] = currencyExchangeRepositorySqlitelmpi.findAll().get(i).getNominal().toString();
             data[i][3] = currencyExchangeRepositorySqlitelmpi.findAll().get(i).getCurrencyName();
             data[i][4] = currencyExchangeRepositorySqlitelmpi.findAll().get(i).getCurrencyCode();
             data[i][5] = currencyExchangeRepositorySqlitelmpi.findAll().get(i).getDate().toString();
         }

        this.add(table1);
        table1.setBounds(130,200,200,300);
        JScrollPane sp=new JScrollPane(table1);
        this.add(sp);
        this.setSize(300,400);
        this.setPreferredSize(new Dimension(1000, 800));
        this.pack();
    }
    public static void recordJson(){
        CurrencyExchangeRepositorySqlitelmpi currencyExchangeRepositorySqlitelmpi = new CurrencyExchangeRepositorySqlitelmpi();
        String path = "src\\main\\resources\\currencyExchange.json";
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            Gson gson = gsonBuilder.setPrettyPrinting().create();
            String jsonString = gson.toJson(currencyExchangeRepositorySqlitelmpi.findAll());
            out.write(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
