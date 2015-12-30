package MoneyCalculator.Application.Swing;

import MoneyCalculator.Application.Mock.MockCurrencySetLoader;
import MoneyCalculator.Control.Command;
import MoneyCalculator.Control.ExchangeCommand;
import MoneyCalculator.View.Persistence.CurrencySetLoader;
import MoneyCalculator.View.Ui.ApplicationDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class SwingApplication extends JFrame implements ApplicationDialog {


    private final CurrencySetLoader currencyLoader;
    private final Map<String, Command> commands = new HashMap<>();
    private SwingMoneyDisplay moneyDisplay;
    private SwingMoneyDialog moneyDialog;
    private SwingCurrencyDialog currencyDialog;

    public static void main(String[] args) {
        new SwingApplication();
    }

    public SwingApplication() {
        super();
        currencyLoader = new MockCurrencySetLoader();
        createCommands();
        deployUi();
    }

    private void createCommands() {
        commands.put("exchange", new ExchangeCommand(this));
    }

    private void deployUi() {
        this.setTitle("Money Calculator");
        this.setSize(450, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
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
        addExchangeButton();
    }

    private void addMoneyDisplay() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(5,5,5,5);
        this.moneyDisplay = new SwingMoneyDisplay();
        this.add(this.moneyDisplay, c);
    }

    private void addCurrencyDialog() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(5,5,5,5);
        this.currencyDialog = new SwingCurrencyDialog(currencyLoader);
        this.add(this.currencyDialog, c);
    }

    private void addMoneyDialog() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        this.moneyDialog = new SwingMoneyDialog(currencyLoader);
        this.add(this.moneyDialog, c);
    }

    private void addExchangeButton() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(15,15,15,15);
        JButton button = new JButton("Calculate");
        button.addActionListener(execute("exchange"));
        this.add(button, c);
    }

    private ActionListener execute(String command) {
        return e -> commands.get(command).execute();
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
