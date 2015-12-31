package MoneyCalculator.Application.Swing;

import MoneyCalculator.Application.SqliteDatabase.CurrencySetDatabaseReader;
import MoneyCalculator.Application.SqliteDatabase.ExchangeRateDatabaseReader;
import MoneyCalculator.Model.PersistenceReaderError;
import MoneyCalculator.Control.Command;
import MoneyCalculator.Control.ExchangeCommand;
import MoneyCalculator.View.Persistence.CurrencySetReader;
import MoneyCalculator.View.Ui.ApplicationDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingApplication extends JFrame implements ApplicationDialog {


    private CurrencySetReader currencyReader;
    private final Map<String, Command> commands = new HashMap<>();
    private SwingMoneyDisplay moneyDisplay;
    private SwingMoneyDialog moneyDialog;
    private SwingCurrencyDialog currencyDialog;
    private ExchangeRateDatabaseReader exchangeRateReader;

    public static void main(String[] args) {
        new SwingApplication();
    }

    public SwingApplication() {
        super();
        loadPersistence();
        createCommands();
        deployUi();
    }

    private void exitWithFailure(Object errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        this.dispose();
        System.exit(1);
    }

    private void loadPersistence() {
        currencyReader = new CurrencySetDatabaseReader("resources/exchange.db");
        exchangeRateReader = new ExchangeRateDatabaseReader("resources/exchange.db");
    }

    private void createCommands() {
        commands.put("exchange", new ExchangeCommand(this, exchangeRateReader));
    }

    private void deployUi() {
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.createInterface();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }

    private void createInterface() {
        try {
            addMoneyDialog();
            addCurrencyDialog();
            addMoneyDisplay();
        } catch (PersistenceReaderError readerError) {
            exitWithFailure(readerError.getMessage());
        }
    }

    private void addMoneyDisplay() {
        this.moneyDisplay = new SwingMoneyDisplay();
        this.add(this.moneyDisplay, BorderLayout.CENTER);
    }

    private void addCurrencyDialog() throws PersistenceReaderError {
        this.currencyDialog = new SwingCurrencyDialog(currencyReader);
        this.currencyDialog.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        selectSecondCurrencyInCurrencyDialog();
        this.currencyDialog.addItemListener(e -> execute("exchange"));

        this.add(this.currencyDialog, BorderLayout.EAST);
    }

    private void selectSecondCurrencyInCurrencyDialog() {
        if (this.currencyDialog.getItemCount() > 1)
            this.currencyDialog.setSelectedIndex(1);
    }

    private void addMoneyDialog() throws PersistenceReaderError {
        this.moneyDialog = new SwingMoneyDialog(currencyReader);
        this.moneyDialog.addChangeListener(e -> execute("exchange"));

        this.add(this.moneyDialog, BorderLayout.NORTH);
    }

    private void execute(String command) {
        commands.get(command).execute();
    }


    @Override
    public SwingMoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    @Override
    public SwingMoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    @Override
    public SwingCurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

}
