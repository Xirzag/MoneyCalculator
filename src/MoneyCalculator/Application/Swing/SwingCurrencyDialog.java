package MoneyCalculator.Application.Swing;

import MoneyCalculator.Model.PersistenceReaderError;
import MoneyCalculator.Model.Currency;
import MoneyCalculator.View.Persistence.CurrencySetReader;
import MoneyCalculator.View.Ui.CurrencyDialog;

import javax.swing.*;

public class SwingCurrencyDialog extends JComboBox implements CurrencyDialog {

    public SwingCurrencyDialog(CurrencySetReader currencyLoader) throws PersistenceReaderError {
        getAllCurrenciesFrom(currencyLoader);

    }

    protected class CurrencyItem {

        private final Currency currency;

        public CurrencyItem(Currency currency) {
            this.currency = currency;
        }

        public Currency getCurrency() {
            return currency;
        }

        @Override
        public String toString() {
            return currency.getSymbol() + " " + currency.getName();
        }
    }

    private void getAllCurrenciesFrom(CurrencySetReader currencyLoader) throws PersistenceReaderError {
        currencyLoader.read().stream().forEach(
                currency -> this.addItem(new CurrencyItem(currency))
        );
    }

    @Override
    public Currency get() {
        return ((CurrencyItem) this.getSelectedItem()).getCurrency();
    }
}
