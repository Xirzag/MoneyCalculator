package MoneyCalculator.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CurrencySet {

    private final Set<Currency> set;

    public CurrencySet() {
        set = new HashSet<>();
    }

    public void add(Currency currency) {
        set.add(currency);
    }

    public Currency[] toArray() {
        return set.toArray(new Currency[set.size()]);
    }

    public Stream<Currency> stream() {
        return set.stream();
    }
}
