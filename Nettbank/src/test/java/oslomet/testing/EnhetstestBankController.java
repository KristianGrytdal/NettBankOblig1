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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

        //arrange
        List<Transaksjon> transaksjoner = new ArrayList<>();
        Transaksjon enTransaksjon = new Transaksjon(1, "12423423", 100.00, "11.12.2023", "hello", "avventer", "98765432");
        transaksjoner.add(enTransaksjon);

        Konto forventetKonto = new Konto("12330495", "0987654321", 2.35, "?", "NOK", transaksjoner);

        when(sjekk.loggetInn()).thenReturn("934856947356");
        when(repository.hentTransaksjoner(any(), any(), any())).thenReturn(forventetKonto);

        //act
        Konto faktiskKonto = bankController.hentTransaksjoner("kontoNr", "fraDato", "tilDato");


        //assert
        assertNotNull(faktiskKonto);
        assertEquals(forventetKonto, faktiskKonto);

    }

    @Test
    public void hentTransaksjoner_IkkeLoggetnn() {

        //arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Konto kontoResultat = bankController.hentTransaksjoner("kontonr", "43532", "343534");

        //assert
        assertNull(kontoResultat);
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

        when(repository.hentSaldi(anyString())).thenReturn(Saldi);

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

        when(repository.registrerBetaling(Trans1)).thenReturn("OK");

        // act
        String resultat = bankController.registrerBetaling(Trans1);

        // assert
        assertEquals("OK", resultat);
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
    public void hentbetaling(){
        List<Transaksjon> Regbet = new ArrayList<>();
        Transaksjon Trans1 = new Transaksjon(1, "029382173943", 456.32, "02.06.1998", "Hei", "?", "74837263781");
        Transaksjon Trans2 = new Transaksjon(2, "029783239489", 4200.99, "30.02.2012", "Hallo", "???", "82398239823");
        Regbet.add(Trans1);
        Regbet.add(Trans2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        //mulig denne må endres til then return transaksjon?
        when(repository.hentBetalinger((anyString()))).thenReturn(Regbet);

        // må putte resulatet i en list
        List<Transaksjon> resultat = bankController.hentBetalinger();
        // må sammenligne med en list

        assertEquals(Regbet, resultat);
    }

    @Test
    public void ikkelogget_hentbetaling(){

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Transaksjon> entrans = bankController.hentBetalinger();

        // assert
        assertNull(entrans);
    }


    // ......................... utforBetaling ..................... //
    @Test
    public void utforbetaling_loggetinn(){

        List<Transaksjon> Regbet = new ArrayList<>();
        Transaksjon Trans1 = new Transaksjon(1, "029382173943", 456.32, "02.06.1998", "Hei", "?", "74837263781");
        Transaksjon Trans2 = new Transaksjon(2, "029783239489", 4200.99, "30.02.2012", "Hallo", "???", "82398239823");
        Regbet.add(Trans1);
        Regbet.add(Trans2);

        when(sjekk.loggetInn()).thenReturn("029382173943");

        when(repository.utforBetaling(1)).thenReturn("OK");

        when(repository.hentBetalinger(anyString())).thenReturn(Regbet);

        List<Transaksjon> resultat = bankController.utforBetaling(1);

        assertEquals(Regbet,resultat);
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

        //arrange
        Kunde enKunde = new Kunde("320942", "Julie", "Andreus", "Gøteborggata 19", "0566", "Oslo", "97434826", "hejhej");

        when(sjekk.loggetInn()).thenReturn("4309834");
        when(repository.endreKundeInfo(any())).thenReturn(enKunde.toString());

        //act
        String resultat = bankController.endre(enKunde);

        //assert
        assertEquals(enKunde.toString(), resultat);

    }

    @Test
    public void endreKundeInfo_IkkeLoggetInn(){

        Kunde enKunde = new Kunde("320942", "Julie", "Andreus", "Gøteborggata 19", "0566", "Oslo", "97434826", "hejhej");

        //arrange
        when(sjekk.loggetInn()).thenReturn(null);


        //act
        String resultat = bankController.endre(enKunde);

        //assert
        assertNull(resultat);

    }

}

