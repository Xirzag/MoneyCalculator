package MoneyCalculator.Control;

import MoneyCalculator.Application.SqliteDatabase.ExchangeRateDatabaseReader;
import MoneyCalculator.Model.Currency;
import MoneyCalculator.Model.ExchangeRate;
import MoneyCalculator.Model.Money;
import MoneyCalculator.Model.PersistenceReaderError;
import MoneyCalculator.View.Process.Exchanger;
import MoneyCalculator.View.Ui.ApplicationDialog;

import java.util.Date;

public class ExchangeCommand implements Command {


    private ApplicationDialog application;
    private ExchangeRateDatabaseReader exchangeRateReader;

    public ExchangeCommand(ApplicationDialog applicationDialog, ExchangeRateDatabaseReader exchangeRateReader) {

        this.application = applicationDialog;
        this.exchangeRateReader = exchangeRateReader;
    }

    @Override
    public void execute() {
        Money money = application.getMoneyDialog().get();
        Currency currency = application.getCurrencyDialog().get();
        try {
            exchange(money, currency);
        } catch (PersistenceReaderError persistenceReaderError) {
            persistenceReaderError.printStackTrace();
        }
    }

    private void exchange(Money money, Currency currency) throws PersistenceReaderError {
        ExchangeRate exchangeRate = exchangeRateReader.get(new Date(), money.getCurrency(), currency);
        Money result = new Exchanger().exchange(money, exchangeRate);
        application.getMoneyDisplay().show(result);
    }

}
