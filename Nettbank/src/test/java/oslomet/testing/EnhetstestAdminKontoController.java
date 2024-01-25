package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
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
public class EnhetstestAdminKontoController {

    @InjectMocks
    // denne skal testes
    private AdminKontoController adminKontoController;

    @Mock
    // denne skal Mock'es
    private AdminRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    // ......................... HentAlle ..................... //
    @Test
    public void hentAlleKonti_loggetInn(){
        // arrange
        List<Konto> Konto = new ArrayList<>();
        Konto konto1 = new Konto("83948773849","7384938320",23.32, "Spare","NOK",null);
        Konto konto2 = new Konto("72837462872","7384901234", 45.54,"Fest","Swe",null);
        Konto.add(konto1);
        Konto.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentAlleKonti()).thenReturn(Konto);

        // act
        List<Konto> resultat = adminKontoController.hentAlleKonti();

        // assert
        assertEquals(Konto, resultat);

    }
    @Test
    public void hentAlleKonti_ikkeLoggetInn(){
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = adminKontoController.hentAlleKonti();

        // assert
        assertNull(resultat);

    }

    // ......................... Lagre ..................... //
    @Test
    public void registrerKonto_loggetInn(){
        // arrange
        List<Konto> Konto = new ArrayList<>();
        Konto konto1 = new Konto("83948773849","7384938320",23.32, "Spare","NOK",null);
        Konto konto2 = new Konto("72837462872","7384901234", 45.54,"Fest","Swe",null);
        Konto konto3 = new Konto("72837456872","7384923434", 45.57,"Festing","Dnk",null);
        Konto.add(konto1);
        Konto.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.registrerKonto((any(Konto.class)))).thenReturn("OK");

        // act
        String resultat = adminKontoController.registrerKonto(konto3);

        // assert
        assertEquals(resultat, "OK");
    }

    @Test
    public void registerKonto_IkkeLoggetInn(){
        // arrange
        List<Konto> Konto = new ArrayList<>();
        Konto konto1 = new Konto("83948773849","7384938320",23.32, "Spare","NOK",null);
        Konto konto2 = new Konto("72837462872","7384901234", 45.54,"Fest","Swe",null);
        Konto konto3 = new Konto("72837456872","7384923434", 45.57,"Festing","Dnk",null);
        Konto.add(konto1);
        Konto.add(konto2);
        when(sjekk.loggetInn()).thenReturn("Ikke innlogget");

        // act
        String resultat = adminKontoController.registrerKonto(konto3);

        // assert
        assertNull(resultat);

    }

    // ......................... HentAlle ..................... //
    @Test
    public void endreKonto_loggetInn(){
        // arrange
        List<Konto> Konto = new ArrayList<>();
        Konto konto1 = new Konto("83948773849","7384938320",23.32, "Spare","NOK",null);
        Konto konto2 = new Konto("72837462872","7384901234", 45.54,"Fest","Swe",null);
        Konto konto3 = new Konto("72837456872","7384923434", 45.57,"Festing","Dnk",null);
        Konto.add(konto1);
        Konto.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.endreKonto(any(Konto.class))).thenReturn("OK");

        // act
        String resultat = adminKontoController.endreKonto(konto1);

        // assert
        assertEquals("OK", resultat);

    }
    @Test
    public void endreKonto_ikkeLoggetInn(){
        // arrange
        List<Konto> Konto = new ArrayList<>();
        Konto konto1 = new Konto("83948773849","7384938320",23.32, "Spare","NOK",null);
        Konto konto2 = new Konto("72837462872","7384901234", 45.54,"Fest","Swe",null);
        Konto konto3 = new Konto("72837456872","7384923434", 45.57,"Festing","Dnk",null);
        Konto.add(konto1);
        Konto.add(konto2);
        when(sjekk.loggetInn()).thenReturn("ikke inlogget");

        // act
        String resultat = adminKontoController.endreKonto(konto1);

        // assert
        assertNull(resultat);

    }

    // ......................... SlettKonto ..................... //
    @Test
    public void slettKonto_loggetInn(){
        // arrange
        List<Konto> Konto = new ArrayList<>();
        Konto konto1 = new Konto("83948773849","7384938320",23.32, "Spare","NOK",null);
        Konto konto2 = new Konto("72837462872","7384901234", 45.54,"Fest","Swe",null);
        Konto konto3 = new Konto("72837456872","7384923434", 45.57,"Festing","Dnk",null);
        Konto.add(konto1);
        Konto.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.slettKonto(anyString())).thenReturn("OK");

        // act
        String resultat = adminKontoController.slettKonto("7384938320");

        // assert
        assertEquals("OK", resultat);

    }
    @Test
    public void slettKonto_ikkeLoggetInn(){
        // arrange
        List<Konto> Konto = new ArrayList<>();
        Konto konto1 = new Konto("83948773849","7384938320",23.32, "Spare","NOK",null);
        Konto konto2 = new Konto("72837462872","7384901234", 45.54,"Fest","Swe",null);
        Konto konto3 = new Konto("72837456872","7384923434", 45.57,"Festing","Dnk",null);
        Konto.add(konto1);
        Konto.add(konto2);
        when(sjekk.loggetInn()).thenReturn("Ikke innlogget");

        // act
        String resultat = adminKontoController.slettKonto("7384938320");

        // assert
        assertNull(resultat);

    }

}