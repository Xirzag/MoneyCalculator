package MoneyCalculator.Application.Swing;

import MoneyCalculator.Model.Currency;
import MoneyCalculator.Model.Money;
import MoneyCalculator.View.Persistence.CurrencySetLoader;
import MoneyCalculator.View.Ui.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {


    private SwingCurrencyDialog currencyDialog;
    //private JFormattedTextField textField;
    private JTextField textField;

    public SwingMoneyDialog(CurrencySetLoader currencyLoader) {
        deployUi(currencyLoader);
    }

    private void deployUi(CurrencySetLoader currencyLoader) {
        addTextField();
        AddCurrencyMenu(currencyLoader);
    }

    private void AddCurrencyMenu(CurrencySetLoader currencyLoader) {
        this.currencyDialog = new SwingCurrencyDialog(currencyLoader);
        this.add(currencyDialog,  BorderLayout.EAST);
    }

    private void addTextField() {
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
        //this.textField = new JFormattedTextField(moneyFormat);
        this.textField = new JTextField();
        this.textField.setColumns(10);
        this.textField.setText("0");
        this.add(textField, BorderLayout.CENTER);
    }

    @Override
    public Money get() {
        return new Money(getMoneyAmount(), getCurrency());
    }

    private Currency getCurrency() {
        return this.currencyDialog.get();
    }

    private double getMoneyAmount() {
        String moneyAmount = this.textField.getText();
        return Double.parseDouble(moneyAmount);
    }
}
