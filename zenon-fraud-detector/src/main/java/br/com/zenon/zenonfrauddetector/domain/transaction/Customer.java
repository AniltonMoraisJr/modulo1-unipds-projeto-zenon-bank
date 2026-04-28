package br.com.zenon.zenonfrauddetector.domain.transaction;

import java.math.BigDecimal;
import org.jspecify.annotations.NonNull;

public record Customer(@NonNull String name, BigDecimal oldBalance, BigDecimal newBalance) {
  public Customer {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("name should not be empty");
    }
    if (oldBalance.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("oldBalance should be positive: " + oldBalance);
    }
    if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("newBalance should be positive: " + newBalance);
    }
  }

}
