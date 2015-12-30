package MoneyCalculator.View.Process;

import MoneyCalculator.Model.ExchangeRate;
import MoneyCalculator.Model.Money;

public class Exchanger {
    public Money exchange(Money money, ExchangeRate exchangeRate) {
        return new Money(money.getAmount() * exchangeRate.getRate(), exchangeRate.getTo());
    }
    
}
