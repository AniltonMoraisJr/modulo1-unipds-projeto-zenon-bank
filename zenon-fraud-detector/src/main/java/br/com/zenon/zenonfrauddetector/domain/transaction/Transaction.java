package br.com.zenon.zenonfrauddetector.domain.transaction;

import java.math.BigDecimal;
import java.util.Optional;
import org.jspecify.annotations.NonNull;

public record Transaction(int step,
                          TransactionType type,
                          @NonNull BigDecimal amount,
                          @NonNull Customer customerOrig,
                          @NonNull Customer customerDest, boolean isFraud, boolean isFlaggedFraud) {
  public Transaction {
    if (step <= 0) {
      throw new IllegalArgumentException("step should be positive: " + step);
    }
    if(Optional.ofNullable(type).isEmpty()){
      throw new IllegalArgumentException("Invalid transaction type");
    }
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Amount must be greater than zero");
    }
  }

}
