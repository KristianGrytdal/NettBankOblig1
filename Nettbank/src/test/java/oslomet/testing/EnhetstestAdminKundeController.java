package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.GetMapping;
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
import static org.mockito.ArgumentMatchers.*;
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


    @Test
    public void endre_ikkeLoggetInn(){
        // arrange
        List<Kunde> Kunde = new ArrayList<>();
        Kunde kunde1 = new Kunde("84957293837","Hans","Zimmerman","Skogata 12","2100","Skarnes","273829483","Hei");
        Kunde kunde2 = new Kunde("72837462872","Per","Sleip","Oslogata 12","0110","Oslo","837436523","Hei2");
        Kunde.add(kunde1);
        Kunde.add(kunde2);
        when(sjekk.loggetInn()).thenReturn("Ikke logget inn");

        // act
        String resultat = adminKundeController.endre(kunde1);

        // assert
        assertNull(resultat);

    }
    @Test
    public void endre_loggetInn(){

        //arrange
        Kunde enKunde = new Kunde("403975", "Tia", "Mahadia", "Falck Yttersplass 5", "1234", "Oslo", "97463526", "heloooo");

        when(sjekk.loggetInn()).thenReturn(String.valueOf(enKunde));
        when(repository.endreKundeInfo(any())).thenReturn(enKunde.toString());

        //act
        String resultat = adminKundeController.endre(enKunde);

        //assert
        assertEquals(enKunde.toString(), resultat);
    }
    @Test
    public void hentAlle_ikkeLoggetInn(){
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertNull(resultat);

    }

    // ......................... Lagre ..................... //
    @Test
    public void lagreKunde_loggetInn(){
        // arrange
        List<Kunde> Kunde = new ArrayList<>();
        Kunde kunde1 = new Kunde("84957293837","Hans","Zimmerman","Skogata 12","2100","Skarnes","273829483","Hei");
        Kunde kunde2 = new Kunde("72837462872","Per","Sleip","Oslogata 12","0110","Oslo","837436523","Hei2");
        Kunde.add(kunde1);
        Kunde.add(kunde2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.registrerKunde((any(Kunde.class)))).thenReturn("OK");

        // act
        String resultat = adminKundeController.lagreKunde(kunde1);

        // assert
        assertEquals(resultat, "OK");
    }
    @Test
    public void lagreKunde_IkkeLoggetInn(){
        // arrange
        Kunde kunde1 = new Kunde("84957293837","Hans","Zimmerman","Skogata 12","2100","Skarnes","273829483","Hei");
        when(sjekk.loggetInn()).thenReturn("Ikke logget inn");

        // act
        String resultat = adminKundeController.lagreKunde(kunde1);

        // assert
        assertNull(resultat);

    }
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
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertEquals(Kunde, resultat);
    }

    @Test
    public void slett_loggetInn(){
        List<Kunde> Kunde = new ArrayList<>();
        Kunde kunde1 = new Kunde("84957293837","Hans","Zimmerman","Skogata 12","2100","Skarnes","273829483","Hei");
        Kunde kunde2 = new Kunde("72837462872","Per","Sleip","Oslogata 12","0110","Oslo","837436523","Hei2");
        Kunde.add(kunde1);
        Kunde.add(kunde2);

        when(sjekk.loggetInn()).thenReturn("2345678986");

        when(repository.slettKunde(anyString())).thenReturn("OK");

        String resultat = adminKundeController.slett("84957293837");

        assertEquals("OK", resultat);
    }

    @Test
    public void Slett_ikkeloggetinn(){

        when(sjekk.loggetInn()).thenReturn("Ikke logget inn");

        // act
        String resultat = adminKundeController.slett("8938595");

        // assert
        assertNull(resultat);

    }


}