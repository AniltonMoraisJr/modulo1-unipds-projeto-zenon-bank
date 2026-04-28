package br.com.zenon.zenonfrauddetector.infrastructure.file;

import br.com.zenon.zenonfrauddetector.domain.transaction.Customer;
import br.com.zenon.zenonfrauddetector.domain.transaction.Transaction;
import br.com.zenon.zenonfrauddetector.domain.transaction.TransactionType;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public class TransactionIngestor {

  private static final int HEADER_LINE = 0;
  private static final int MAX_LINES = 50000;
  private static final String DELIMITER = ",";

  public List<Transaction> ingestTransactions(@NonNull String urlPath) {
    List<Transaction> transactions = List.of();
    Path path = Paths.get(urlPath);
    long startMillis = System.currentTimeMillis();
    try {
      List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
      transactions = lines.stream()
          .skip((long) HEADER_LINE + 1)
          .limit(Math.min(lines.size(), MAX_LINES))
          .map(this::parseLine)
          .filter(Objects::nonNull).toList();
      System.out.println(
          "Time to ingest transactions: " + (System.currentTimeMillis() - startMillis) + ". Size: "
              + Math.min(lines.size(), MAX_LINES));
      return transactions;
    } catch (IOException e) {
      throw new IllegalStateException("Could not read file", e);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  private Transaction parseLine(String line) {
    String[] values = line.split(DELIMITER);
    try {
      return new Transaction(
          Integer.parseInt(values[0]),
          TransactionType.valueOf(values[1]),
          new BigDecimal(values[2]),
          new Customer(values[3], new BigDecimal(values[4]), new BigDecimal(values[5])),
          new Customer(values[6], new BigDecimal(values[7]), new BigDecimal(values[8])),
          !values[9].equals("1"),
          !values[10].equals("1")
      );
    } catch (IllegalArgumentException e) {
      System.err.println("Erro: " + line + " | " + e.getMessage());
      return null;
    }
  }

}
