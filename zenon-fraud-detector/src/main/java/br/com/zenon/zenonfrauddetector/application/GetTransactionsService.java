package br.com.zenon.zenonfrauddetector.application;

import br.com.zenon.zenonfrauddetector.domain.transaction.Transaction;
import br.com.zenon.zenonfrauddetector.infrastructure.file.TransactionIngestor;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetTransactionsService {

  private final TransactionIngestor transactionIngestor;

  public GetTransactionsService(TransactionIngestor transactionIngestor) {
    this.transactionIngestor = transactionIngestor;
  }

  public List<Transaction> getTransactions(String urlPath) {
    return transactionIngestor.ingestTransactions(urlPath);
  }
}
