package assignments.assignment1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class NotaGeneratorTest {
    @Test
    public void testGenerateId1() {
        assertEquals("DEK-082212345678-75", NotaGenerator.generateId("Dek Depe", "082212345678"));
    }

}
