package MoneyCalculator.View.Persistence;

import MoneyCalculator.Model.Currency;
import MoneyCalculator.Model.ExchangeRate;

import java.util.Date;

public interface ExchangeRateReader {
    ExchangeRate get(Date date, Currency from, Currency to);
}
