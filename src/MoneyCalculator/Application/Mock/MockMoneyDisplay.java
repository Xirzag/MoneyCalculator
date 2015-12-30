package MoneyCalculator.Application.Mock;

import MoneyCalculator.Model.Money;
import MoneyCalculator.View.Ui.MoneyDisplay;

public class MockMoneyDisplay implements MoneyDisplay {

    @Override
    public void show(Money money) {
        System.out.println(money.getAmount() + " " + money.getCurrency().getSymbol());
    }
    
}
