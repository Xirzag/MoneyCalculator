package MoneyCalculator.Application.Swing;

import MoneyCalculator.Application.Mock.MockCurrencySetLoader;
import MoneyCalculator.Control.Command;
import MoneyCalculator.Control.ExchangeCommand;
import MoneyCalculator.View.Persistence.CurrencySetLoader;
import MoneyCalculator.View.Ui.ApplicationDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SwingApplication extends JFrame implements ApplicationDialog {


    private CurrencySetLoader currencyLoader;
    private final Map<String, Command> commands = new HashMap<>();
    private SwingMoneyDisplay moneyDisplay;
    private SwingMoneyDialog moneyDialog;
    private SwingCurrencyDialog currencyDialog;

    public static void main(String[] args) {
        new SwingApplication();
    }

    public SwingApplication() {
        super();
        if(!loadCurrencySet())
            exitWithFailure("Error, couldn't connect to the database");

        createCommands();
        deployUi();
    }

    private void exitWithFailure(Object errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        this.dispose();
        System.exit(1);
    }

    private boolean loadCurrencySet() {
        try {

            //currencyLoader = new CurrencySetDatabaseLoader("none");
            currencyLoader = new MockCurrencySetLoader();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void createCommands() {
        commands.put("exchange", new ExchangeCommand(this));
    }

    private void deployUi() {
        this.setTitle("Money Calculator");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.createInterface();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }

    private void createInterface() {
        addMoneyDialog();
        addCurrencyDialog();
        addMoneyDisplay();
    }

    private void addMoneyDisplay() {
        this.moneyDisplay = new SwingMoneyDisplay();
        this.add(this.moneyDisplay, BorderLayout.CENTER);
    }

    private void addCurrencyDialog() {
        this.currencyDialog = new SwingCurrencyDialog(currencyLoader);
        this.currencyDialog.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        selectSecondCurrencyInCurrencyDialog();
        this.currencyDialog.addItemListener(e -> execute("exchange"));

        this.add(this.currencyDialog, BorderLayout.EAST);
    }

    private void selectSecondCurrencyInCurrencyDialog() {
        if(this.currencyDialog.getItemCount() > 1)
            this.currencyDialog.setSelectedIndex(1);
    }

    private void addMoneyDialog() {
        this.moneyDialog = new SwingMoneyDialog(currencyLoader);
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
