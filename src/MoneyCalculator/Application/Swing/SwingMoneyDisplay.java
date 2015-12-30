package MoneyCalculator.Application.Swing;

import MoneyCalculator.Model.Money;
import MoneyCalculator.View.Ui.MoneyDisplay;

import javax.swing.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {

    public SwingMoneyDisplay() {
        this.setText("0.00");
    }

    @Override
    public void show(Money money) {
        this.setText(money.getAmount() + " " + money.getCurrency().getSymbol());
    }
}
