package by.shumilov.idfinancelabtesttask.repository;

import by.shumilov.idfinancelabtesttask.bean.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
    CryptoCurrency findBySymbol(String symbol);
}
