package MoneyCalculator.Control;

import MoneyCalculator.Application.Mock.MockExchangeRateReader;
import MoneyCalculator.Model.Currency;
import MoneyCalculator.Model.ExchangeRate;
import MoneyCalculator.Model.Money;
import MoneyCalculator.View.Process.Exchanger;
import MoneyCalculator.View.Ui.ApplicationDialog;

import java.util.Date;

public class ExchangeCommand implements Command {


    private ApplicationDialog application;

    public ExchangeCommand(ApplicationDialog applicationDialog) {

        this.application = applicationDialog;
    }

    @Override
    public void execute() {
        Money money = application.getMoneyDialog().get();
        Currency currency = application.getCurrencyDialog().get();
        ExchangeRate exchangeRate = new MockExchangeRateReader().get(new Date(),money.getCurrency(), currency);
        Money result = new Exchanger().exchange(money, exchangeRate);
        application.getMoneyDisplay().show(result);
    }
    
}
