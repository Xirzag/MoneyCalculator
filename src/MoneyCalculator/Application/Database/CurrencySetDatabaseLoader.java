package MoneyCalculator.Application.Database;

import MoneyCalculator.Model.CurrencySet;
import MoneyCalculator.View.Persistence.CurrencySetLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CurrencySetDatabaseLoader implements CurrencySetLoader{
    public CurrencySetDatabaseLoader(String name) throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        Connection c = DriverManager.getConnection("jdbc:sqlite:" + name);

    }

    public CurrencySet load() {
        return null;
    }
}
