package br.com.zenon.zenonfrauddetector.domain.transaction;

import java.math.BigDecimal;

public record Transaction(int step, TransactionType type, BigDecimal amount, Customer customerOrig, Customer customerDest, boolean isFraud, boolean isFlaggedFraud) {

}
