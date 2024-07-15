package Converter;

import dto.Valute;
import model.CurrencyExchange;

import java.time.LocalDate;

public class ConverterValuteCurrencyExchange implements Converter<CurrencyExchange, Valute> {

    @Override
    public CurrencyExchange toDomain(Valute valute) {
        return new CurrencyExchange(-1,
                valute.getValue(),
                valute.getNominal(),
                valute.getName(),
                valute.getCharCode(),
                LocalDate.now());
    }

    @Override
    public Valute toDto(CurrencyExchange currencyExchange) {
        return new Valute(
                "",
                0,
                currencyExchange.getCurrencyCode(),
                currencyExchange.getNominal(),
                currencyExchange.getCurrencyName(),
                currencyExchange.getValue()
        );
    }
}
