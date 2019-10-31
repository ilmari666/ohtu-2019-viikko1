package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaaLiikaa() {
      varasto.lisaaVarastoon(varasto.getTilavuus()+1);
      assertEquals(varasto.getSaldo(), varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void otaLiikaaPalauttaaSenMitaSaa() {
      varasto.lisaaVarastoon(8);
      double saatu = varasto.otaVarastosta(9);
      assertEquals(saatu, 8, vertailuTarkkuus);
    }
    
    @Test
    public void lisaaNegatiivinenEiMuutaSaldoa() {
      varasto.lisaaVarastoon(-1);
      double saldo = varasto.getSaldo();
      varasto.lisaaVarastoon(0);
      double saldo2 = varasto.getSaldo();
      assertEquals(saldo, saldo2, vertailuTarkkuus);
    }

    @Test
    public void otaNegatiivinenEiMuutaSaldoa() {
      varasto.lisaaVarastoon(8);
      double saldo = varasto.getSaldo();
      varasto.otaVarastosta(-1);
      double saldo2 = varasto.getSaldo();
      assertEquals(saldo, saldo2, vertailuTarkkuus);
    }
    
    @Test
    public void mahtuvuusOikein(){
        assertEquals(varasto.getTilavuus()-varasto.getSaldo(), varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiNegatiivistaVarastoTilaa(){
        varasto=new Varasto(-1);
        assertEquals(varasto.getTilavuus(),0.0,vertailuTarkkuus);
    }
/*
        public Varasto(double tilavuus, double alkuSaldo) { // kuormitetaan
        if (tilavuus > 0.0) {
            this.tilavuus = tilavuus;
        } else // virheellinen, nollataan
        {
            this.tilavuus = 0.0;  // => käyttökelvoton varasto
        }
        if (alkuSaldo < 0.0) {
            this.saldo = 0.0;
        } else if (alkuSaldo <= tilavuus) // mahtuu
        {
            this.saldo = alkuSaldo;
        } else {
            this.saldo = tilavuus;  // täyteen ja ylimäärä hukkaan!
        }
    }

    */
    
    @Test
    public void kuormitettuOikeinOikeanKokoinen(){
     varasto=new Varasto(2,1);
      assertEquals(varasto.getTilavuus(), 2.0, vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuOikeaSaldo(){
      varasto=new Varasto(2,1);
      assertEquals(varasto.getSaldo(), 1.0, vertailuTarkkuus);
    }

    @Test
    public void kuormitettuVirheSaldo(){
      varasto=new Varasto(2,-1);
      assertEquals(varasto.getSaldo(), 0.0, vertailuTarkkuus);
    }

    @Test
    public void kuormitettuNegatiivinenTilaLuoTyhjanVaraston(){
      varasto=new Varasto(-1,8);
      assertEquals(varasto.getSaldo(), 0.0, vertailuTarkkuus);
    }

    @Test
    public void oikeaStringFormaatti(){
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }
    
}