package ir.ahs441.wallet.application.service;

import ir.ahs441.wallet.application.service.dto.GetTotalTransactionsAmountQuery;
import ir.ahs441.wallet.application.service.dto.GetTotalTransactionsAmountResponse;
import ir.ahs441.wallet.domain.repository.TransactionsRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionsApplicationServiceTest {

    @Test
    public void testCalculateTotalAmountOfTransactionsForValidDate() {

        TransactionsRepository transactionsRepository = mock(TransactionsRepository.class);
        TransactionsApplicationService service = new TransactionsApplicationService(transactionsRepository);
        LocalDate date = LocalDate.of(2023, 10, 1);
        GetTotalTransactionsAmountQuery query = GetTotalTransactionsAmountQuery.builder().when(date).build();
        BigDecimal expectedAmount = new BigDecimal("1000.00");

        when(transactionsRepository.calculateTotalAmountOfTransactions(date.atStartOfDay(), date.atTime(LocalTime.MAX)))
                .thenReturn(expectedAmount);

        GetTotalTransactionsAmountResponse response = service.calculateTotalAmountOfTransactions(query);

        assertNotNull(response);
        assertEquals(expectedAmount, response.totalTransactionsAmount());
    }

}