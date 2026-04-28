package br.com.zenon.zenonfrauddetector.domain.transaction;

import java.math.BigDecimal;

public record Customer(String name, BigDecimal oldBalance, BigDecimal newBalance) {

}
