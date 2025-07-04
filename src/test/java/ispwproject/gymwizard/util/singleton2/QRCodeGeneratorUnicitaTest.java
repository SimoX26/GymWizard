package ispwproject.gymwizard.util.singleton2;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class QRCodeGeneratorUnicitaTest {

    @Test
    void testCodiciUniciENelFormatoCorretto() {
        QRCodeGenerator generator = QRCodeGenerator.getInstance();
        Set<String> codici = new HashSet<>();
        Pattern formato = Pattern.compile("^[A-Z]{4}-\\d{4}$");

        int NUMERO_TENTATIVI = 1000;

        for (int i = 0; i < NUMERO_TENTATIVI; i++) {
            String codice = generator.generaCodiceAccesso("utente" + i);

            // Verifica che il formato sia corretto
            assertTrue(formato.matcher(codice).matches(), "Formato non valido: " + codice);

            // Verifica che il codice sia unico
            assertFalse(codici.contains(codice), "Codice duplicato trovato: " + codice);

            codici.add(codice);
        }

        // Verifica finale: tutti i codici devono essere unici
        assertEquals(NUMERO_TENTATIVI, codici.size(), "Non tutti i codici sono unici");
    }
}
