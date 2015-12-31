package MoneyCalculator.Application.Swing;

import MoneyCalculator.Model.Currency;
import MoneyCalculator.Model.Money;
import MoneyCalculator.View.Persistence.CurrencySetLoader;
import MoneyCalculator.View.Ui.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {


    private SwingCurrencyDialog currencyDialog;
    private JTextField textField;
    private ActionListener onChangeEvent;

    public SwingMoneyDialog(CurrencySetLoader currencyLoader) {
        deployUi(currencyLoader);
    }

    private void deployUi(CurrencySetLoader currencyLoader) {
        addTextField();
        AddCurrencyMenu(currencyLoader);
    }

    private void AddCurrencyMenu(CurrencySetLoader currencyLoader) {
        this.currencyDialog = new SwingCurrencyDialog(currencyLoader);
        this.currencyDialog.addItemListener( e -> onChangeEvent.actionPerformed(null));
        this.add(currencyDialog,  BorderLayout.EAST);
    }

    private void addTextField() {
        this.textField = new JTextField();
        this.textField.setColumns(10);
        this.textField.setText("0.00");
        this.textField.addKeyListener(createChangeTextFieldListener());
        this.add(textField);
    }

    private KeyListener createChangeTextFieldListener() {
        return new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                onChangeEvent.actionPerformed(null);
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

        };
    }

    @Override
    public Money get() {
        return new Money(getMoneyAmount(), getCurrency());
    }

    private Currency getCurrency() {
        return this.currencyDialog.get();
    }

    private double getMoneyAmount() {
        try{
            return Double.parseDouble(this.textField.getText());
        }catch (Exception e) {
            return 0;
        }
    }

    public void addChangeListener(ActionListener event) {
        this.onChangeEvent = event;
    }
}
