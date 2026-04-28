package br.com.zenon.zenonfrauddetector.interfaces;

import br.com.zenon.zenonfrauddetector.application.GetTransactionsService;
import br.com.zenon.zenonfrauddetector.domain.transaction.Customer;
import br.com.zenon.zenonfrauddetector.domain.transaction.Transaction;
import br.com.zenon.zenonfrauddetector.domain.transaction.TransactionType;
import br.com.zenon.zenonfrauddetector.infrastructure.file.TransactionIngestor;
import java.math.BigDecimal;
import java.util.Optional;

public class TransactionMain {

  public static void main(String[] args) {
    final Transaction transaction1 = new Transaction(
        1, TransactionType.PAYMENT, new BigDecimal("9839.64"),
        new Customer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")),
        new Customer("M1979787155", BigDecimal.ZERO, BigDecimal.ZERO),
        true, true
    );
    System.out.println(transaction1);
    final Transaction transaction2 = new Transaction(
        743, TransactionType.CASH_OUT, new BigDecimal("850002.52"),
        new Customer("C1280323807", new BigDecimal("850002.52"), BigDecimal.ZERO),
        new Customer("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63")),
        false, true
    );
    GetTransactionsService getTransactionsService = new GetTransactionsService(
        new TransactionIngestor());
    System.out.println(transaction2);
    System.out.println("-------------------------------------------------------");
    System.out.println("Getting the first 10 transactions");
    getTransactionsService.getTransactions("data/data_log.csv").subList(0, 10)
        .forEach(System.out::println);
    System.out.println("-------------------------------------------------------");
    System.out.println("Validating transactions");
    getTransactionsService
        .getTransactions("data/paysim_with_bad_data.csv")
        .forEach(System.out::println);

  }
}
