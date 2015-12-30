package MoneyCalculator.Application.Mock;

import MoneyCalculator.Model.Currency;
import MoneyCalculator.Model.ExchangeRate;
import MoneyCalculator.View.Persistence.ExchangeRateReader;

import java.util.Date;

public class MockExchangeRateReader implements ExchangeRateReader {

    @Override
    public ExchangeRate get(Date date, Currency from, Currency to) {
        return new ExchangeRate(from, to, 2.7);
    }
    
}
