package MoneyCalculator.Application.Mock;

import MoneyCalculator.Model.Currency;
import MoneyCalculator.Model.Money;
import MoneyCalculator.View.Ui.MoneyDialog;

public class MockMoneyDialog implements MoneyDialog {

    @Override
    public Money get() {
        return new Money(300, new Currency("USD","Dólar","$"));
    }

    
}
