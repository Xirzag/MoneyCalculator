package MoneyCalculator.Application.Swing;

import MoneyCalculator.Model.Money;
import MoneyCalculator.View.Ui.MoneyDisplay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {

    private final DecimalFormat format = new DecimalFormat("0.00");

    public SwingMoneyDisplay() {
        this.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        this.setText("0.00");
    }

    @Override
    public void show(Money money) {
        this.setText(printAmountOf(money) + " " + printSymbolOf(money));
    }

    private String printSymbolOf(Money money) {
        return money.getCurrency().getSymbol();
    }

    public String printAmountOf(Money money) {
        return format.format(money.getAmount());
    }
}
