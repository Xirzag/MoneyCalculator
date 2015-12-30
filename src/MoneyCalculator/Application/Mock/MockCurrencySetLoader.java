package MoneyCalculator.Application.Mock;

import MoneyCalculator.Model.Currency;
import MoneyCalculator.Model.CurrencySet;
import MoneyCalculator.View.Persistence.CurrencySetLoader;

public class MockCurrencySetLoader implements CurrencySetLoader {
    @Override
    public CurrencySet load() {
        CurrencySet currencySet = new CurrencySet();
        currencySet.add(new Currency("EUR", "Euro", "€"));
        currencySet.add(new Currency("DLR", "Dolar", "$"));
        return currencySet;
    }
}
