package MoneyCalculator.Application.Mock;

import MoneyCalculator.Model.Currency;
import MoneyCalculator.View.Ui.CurrencyDialog;

public class MockCurrencyDialog implements CurrencyDialog {

    @Override
    public Currency get() {
        return new Currency("EUR","Euro","€");
    }
    
}
