package MoneyCalculator.Application.Swing;

import MoneyCalculator.Model.Money;
import MoneyCalculator.View.Ui.MoneyDisplay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SwingMoneyDisplay extends JLabel implements MoneyDisplay {

    public SwingMoneyDisplay() {
        this.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
        this.setText("0.00");
    }

    @Override
    public void show(Money money) {
        this.setText(money.getAmount() + " " + money.getCurrency().getSymbol());
    }
}
