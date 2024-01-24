package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKundeController {

    @InjectMocks
    // denne skal testes
    private AdminKundeController adminKundeController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    // ......................... HentAlle ..................... //
    @Test
    public void hentAlle_loggetInn(){
        // arrange
        List<Kunde> Kunde = new ArrayList<>();
        Kunde kunde1 = new Kunde("84957293837","Hans","Zimmerman","Skogata 12","2100","Skarnes","273829483","Hei");
        Kunde kunde2 = new Kunde("72837462872","Per","Sleip","Oslogata 12","0110","Oslo","837436523","Hei2");
        Kunde.add(kunde1);
        Kunde.add(kunde2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentAlleKunder()).thenReturn(Kunde);

        // act
        List<Kunde> resultat = repository.hentAlleKunder();

        // assert
        assertEquals(Kunde, resultat);
    }

}