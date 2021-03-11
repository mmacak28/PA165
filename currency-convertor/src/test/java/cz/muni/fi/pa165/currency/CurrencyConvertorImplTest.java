package cz.muni.fi.pa165.currency;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class CurrencyConvertorImplTest {

    private static final Currency EUR = Currency.getInstance("EUR");
    private static final Currency CZK = Currency.getInstance("CZK");

    @Mock
    ExchangeRateTable exchangeRateTableMock;
    CurrencyConvertor currencyConvertor;

    @Before
    public void init() throws ExternalServiceFailureException{
        MockitoAnnotations.initMocks(this);
        currencyConvertor = new CurrencyConvertorImpl(exchangeRateTableMock);
        assertNotNull(exchangeRateTableMock);
        assertNotNull(currencyConvertor);
        when(exchangeRateTableMock.getExchangeRate(EUR,CZK)).thenReturn(new BigDecimal(25));
    }

    @Test
    public void testConvert() throws ExternalServiceFailureException{
        assertEquals(currencyConvertor.convert(EUR,CZK,new BigDecimal(100)), new BigDecimal(2500));
    }

    @Test
    public void testConvertWithNullSourceCurrency() {
        assertEquals(currencyConvertor.convert(null,CZK, new BigDecimal(100)), null);
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
        assertEquals(currencyConvertor.convert(EUR, null, new BigDecimal(100)), null);
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        assertEquals(currencyConvertor.convert(EUR, CZK, null), null);
    }

    @Test
    public void testConvertWithUnknownCurrency() {
        fail("Test is not implemented yet.");
    }

    @Test
    public void testConvertWithExternalServiceFailure() {

        fail("Test is not implemented yet.");
    }

}
