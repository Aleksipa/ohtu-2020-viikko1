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
    Varasto tyhjaVarasto;
    Varasto toinenTyhjaVarasto;
    Varasto negatiivinenVarasto;
    Varasto liianTäysiVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        tyhjaVarasto = new Varasto(0.0, 0.0);
        negatiivinenVarasto = new Varasto(5, -1);
        toinenTyhjaVarasto = new Varasto(0);
        liianTäysiVarasto = new Varasto(5,6);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoKäyttöKelvottomanVaraston() {
        assertEquals(0, tyhjaVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoKäyttöKelvottomanVaraston2() {
        assertEquals(0, tyhjaVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoKäyttöKelvottomanVaraston3() {
        assertEquals(0, toinenTyhjaVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriYrittääLuodaLiianTäydenVaraston() {
        assertEquals(5, liianTäysiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaAlkusaldo() {
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
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
    public void lisaysEiLisaaJosVarastoonEiMahdu() {
        varasto.lisaaVarastoon(12);

        // varastoon lisätään vain sinne mahtuva määrä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiLisääVarastoonNegatiivistaMäärää() {
        varasto.lisaaVarastoon(-2);

        // varastoon lisätään vain sinne mahtuva määrä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaVainMitäVarastossaOn() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(10);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaMinenTyhjästäVarastostaPalauttaaNollan() {

        double saatuMaara = varasto.otaVarastosta(10);

        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void yritäOttaaVarastostaNegatiivinenMäärä() {

        double saatuMaara = negatiivinenVarasto.otaVarastosta(-1);

        assertEquals(0.0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void antaaOikeanSaldon() {
        varasto.lisaaVarastoon(8);
        double saldo = varasto.getSaldo();

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, saldo, vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysToimii() {
        String tostring = varasto.toString();

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals("saldo = " + 0.0 + ", vielä tilaa " + 10.0, tostring);
    }
    @Test
    public void alkuSaldoPienempiKuinNolla() {
        String tostring = varasto.toString();

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals("saldo = " + 0.0 + ", vielä tilaa " + 10.0, tostring);
    }

}