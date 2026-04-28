package br.com.zenon.zenonfrauddetector.application;

import br.com.zenon.zenonfrauddetector.domain.transaction.Transaction;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FraudAnalyser {

  public void analyse(List<Transaction> transactions){
    final List<Transaction> fraudulentTransactions = transactions.stream()
        .filter(Transaction::isFraud)
        .toList();
    final long totalFrauds = fraudulentTransactions.stream().count();
    System.out.println("1. Total de Fraudes: " + totalFrauds);
    System.out.println("2. Top 3 Fraudes de Maior Valor");
    fraudulentTransactions
        .stream()
        .sorted(Comparator.comparing(Transaction::amount).reversed())
        .limit(3)
        .forEach(transaction -> System.out.println(transaction.amount()));
    System.out.println("3. Clientes Suspeitos:");
    fraudulentTransactions
        .stream()
        .sorted(Comparator.comparing(Transaction::amount).reversed())
        .limit(5)
        .map(Transaction::customerOrig)
        .distinct()
        .forEach(customer -> System.out.println(customer.name()));
    final BigDecimal totalFraudulentAmount = fraudulentTransactions
        .stream()
        .map(Transaction::amount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    System.out.println("4. Prejuizo Total: " + totalFraudulentAmount);
    System.out.println("5. Fraudes por Tipo:");
    fraudulentTransactions
        .stream()
        .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()))
        .forEach((type, count) -> System.out.println(type + ": " + count));


  }
}
