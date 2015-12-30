package MoneyCalculator.Application.Swing;

import MoneyCalculator.Application.Mock.MockCurrencySetLoader;
import MoneyCalculator.Control.Command;
import MoneyCalculator.Control.ExchangeCommand;
import MoneyCalculator.View.Persistence.CurrencySetLoader;
import MoneyCalculator.View.Ui.ApplicationDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        addExchangeButton();
    }

    private void addMoneyDisplay() {
        this.moneyDisplay = new SwingMoneyDisplay();
        this.add(this.moneyDisplay, BorderLayout.CENTER);
    }

    private void addCurrencyDialog() {
        this.currencyDialog = new SwingCurrencyDialog(currencyLoader);
        this.add(this.currencyDialog, BorderLayout.EAST);
    }

    private void addMoneyDialog() {
        this.moneyDialog = new SwingMoneyDialog(currencyLoader);
        this.add(this.moneyDialog, BorderLayout.NORTH);
    }

    private void addExchangeButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(execute("exchange"));
        JPanel panel = new JPanel();
        panel.add(button);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(panel, BorderLayout.SOUTH);
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
