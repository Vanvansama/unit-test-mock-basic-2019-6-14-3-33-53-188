package cashregister;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class CashRegisterTest {
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStream));
    }

    private String systemOut() {
        return outputStream.toString();
    }

    @Test
    public void should_print_the_real_purchase_when_call_process() {
        Printer printer = new Printer();
        CashRegister cashRegister = new CashRegister(printer);
        Item item = new Item("Coco", 20.0);
        Item[] items = new Item[]{item};
        Purchase purchase = new Purchase(items);
        cashRegister.process(purchase);
        Assertions.assertEquals("Coco\t20.0\n",systemOut());
    }

    @Test
    public void should_print_the_stub_purchase_when_call_process() {
        //given
        Printer printer = new Printer();
        CashRegister cashRegister = new CashRegister(printer);
        Purchase purchase = mock(Purchase.class);
        //when
        when(purchase.asString()).thenReturn("Coco\t20\n");
        cashRegister.process(purchase);
        //then
        Assertions.assertEquals("Coco\t20\n",systemOut());
    }

    @Test
    public void should_verify_with_process_call_with_mockito() {
        //given
        Printer printer = new Printer();
        CashRegister cashRegister = new CashRegister(printer);
        Purchase purchase = mock(Purchase.class);
        //when
        when(purchase.asString()).thenReturn("Coco\t20\n");
        cashRegister.process(purchase);
        //then
        verify(purchase).asString();
    }

}
