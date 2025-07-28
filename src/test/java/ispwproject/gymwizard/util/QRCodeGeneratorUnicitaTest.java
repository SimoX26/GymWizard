package ispwproject.gymwizard.util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class QRCodeGeneratorUnicitaTest {

    @Test
    void testCodiciUniciENelFormatoCorretto() {
        QRCodeGenerator generator = new QRCodeGenerator();
        Set<String> codici = new HashSet<>();
        Pattern formato = Pattern.compile("^[A-Z]{4}-\\d{4}$");

        int numeroTentativi = 1000;

        for (int i = 0; i < numeroTentativi; i++) {
            String codice = generator.generaCodiceAccesso();

            // Verifica che il formato sia corretto
            assertTrue(formato.matcher(codice).matches(), "Formato non valido: " + codice);

            // Verifica che il codice sia unico
            assertFalse(codici.contains(codice), "Codice duplicato trovato: " + codice);

            codici.add(codice);
        }

        // Verifica finale: tutti i codici devono essere unici
        assertEquals(numeroTentativi, codici.size(), "Non tutti i codici sono unici");
    }
}
