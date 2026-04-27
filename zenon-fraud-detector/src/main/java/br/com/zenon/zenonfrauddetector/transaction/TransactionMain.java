package br.com.zenon.zenonfrauddetector.transaction;

import br.com.zenon.zenonfrauddetector.transaction.model.Customer;
import br.com.zenon.zenonfrauddetector.transaction.model.Transaction;
import br.com.zenon.zenonfrauddetector.transaction.model.TransactionType;
import java.math.BigDecimal;

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
    System.out.println(transaction2);
  }
}
