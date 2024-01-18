package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
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
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    // ......................... HentTransaksjoner ..................... //
    @Test
    public void hentTransaksjoner_loggetInn() {

    }

    @Test
    public void hentTransaksjoner_IkkeLoggetnn() {

    }

    // ......................... HentKonti ..................... //
    @Test
    public void hentKonti_LoggetInn()  {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn()  {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    // ......................... HentSaldi ..................... //
    @Test
    public void hentSaldi_loggetInn(){
        // arrange
        List<Konto> Saldi = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        Saldi.add(konto1);
        Saldi.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(Saldi);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertEquals(Saldi, resultat);
    }

    @Test
    public void hentSaldi_IkkeLoggetInn(){
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertNull(resultat);
    }

    // ......................... RegistrerBetaling ..................... //
    @Test
    public void registrerBetaling_loggetInn(){
        // arrange
        List<Transaksjon> Regbet = new ArrayList<>();
        Transaksjon Trans1 = new Transaksjon(1, "029382173943", 456.32, "02.06.1998", "Hei", "?", "74837263781");
        Transaksjon Trans2 = new Transaksjon(2, "029783239489", 4200.99, "30.02.2012", "Hallo", "???", "82398239823");
        Regbet.add(Trans1);
        Regbet.add(Trans2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.registrerBetaling((any(Transaksjon.class)))).thenReturn("OK");

        // act
        List<Transaksjon> resultat = bankController.utforBetaling(1);

        // assert
        assertEquals(Trans1, resultat);
    }

    @Test
    public void registrerBetaling_IkkeLoggetInn(){
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Transaksjon> resultat = bankController.utforBetaling(1);

        // assert
        assertNull(resultat);
    }

    // ......................... HentBetalinger ..................... //
    @Test
    public void hentBetalinger_loggetInn(){

    }

    @Test
    public void hentBetalinger_IkkeLoggetInn(){

    }

    // ......................... utforBetaling ..................... //
    @Test
    public void utforBetaling_loggetInn(){

    }

    @Test
    public void utforBetaling_IkkeLoggetInn(){

    }

    // ......................... HentKundeInfo ..................... //
    @Test
    public void hentKundeInfo_loggetInn() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }

    // ......................... EndreKundeInfo ..................... //
    @Test
    public void endreKundeInfo_LoggetInn(){

    }

    @Test
    public void endreKundeInfo_IkkeLoggetInn(){

    }
}

