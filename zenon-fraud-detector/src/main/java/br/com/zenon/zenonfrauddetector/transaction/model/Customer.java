package br.com.zenon.zenonfrauddetector.transaction.model;

import java.math.BigDecimal;

public record Customer(String name, BigDecimal oldBalance, BigDecimal newBalance) {

}
