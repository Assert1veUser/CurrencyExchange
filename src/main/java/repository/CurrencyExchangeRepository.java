package repository;

import model.CurrencyExchange;

import java.util.List;

public interface CurrencyExchangeRepository {
    CurrencyExchange findById(Integer id);
    List<CurrencyExchange> findAll();
    List<CurrencyExchange> findAllByCode(String currencyCode);
    Integer delete(Integer id);
    Integer deleteAll();
    Integer insert(CurrencyExchange currency);
    Integer update(CurrencyExchange currency);
}
