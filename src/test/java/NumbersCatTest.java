import net.esliceu.numbers.NumbersCat;
import net.esliceu.numbers.SayBigNumberFactory;

import static org.junit.Assert.assertEquals;

public class NumbersCatTest {
    @org.junit.Test
    public void testNombresDe0a19() {
        SayBigNumberFactory instance = SayBigNumberFactory.getInstance();
        assertEquals("Zero", instance.getNumbers().say(0));
        assertEquals("Un",  instance.getNumbers().say(1));
        assertEquals("Set", instance.getNumbers().say(7));
        assertEquals("Vuit", instance.getNumbers().say(8));
        assertEquals("Dinou", instance.getNumbers().say(19));
        assertEquals("Menys quatre", instance.getNumbers().say(-4));
    }

    @org.junit.Test
    public void testNombresDecimals() {
        SayBigNumberFactory instance = SayBigNumberFactory.getInstance();
        assertEquals("Quaranta-quatre", instance.getNumbers().say(44));
        assertEquals("Cinquanta-sis", instance.getNumbers().say(56));
        assertEquals("Trenta", instance.getNumbers().say(30));
        assertEquals("Noranta-nou", instance.getNumbers().say(99));
        assertEquals("Vint", instance.getNumbers().say(20));
        assertEquals("Vint-i-set", instance.getNumbers().say(27));
        assertEquals("Menys seixanta-vuit", instance.getNumbers().say(-68));
    }

    @org.junit.Test
    public void testNombresCentenars() {
        SayBigNumberFactory instance = SayBigNumberFactory.getInstance();
        assertEquals("Cent vint-i-cinc", instance.getNumbers().say(125));
        assertEquals("Dos-cents tres", instance.getNumbers().say(203));
        assertEquals("Quatre-cents catorze", instance.getNumbers().say(414));
        assertEquals("Nou-cents noranta-nou", instance.getNumbers().say(999));
        assertEquals("Cinc-cents", instance.getNumbers().say(500));
    }

    @org.junit.Test
    public void testNombresMilers() {
        SayBigNumberFactory instance = SayBigNumberFactory.getInstance();
        assertEquals("Mil un", instance.getNumbers().say(1001));
        assertEquals("Mil", instance.getNumbers().say(1000));
        assertEquals("Dos mil", instance.getNumbers().say(2000));
        assertEquals("Tres mil quatre-cents cinquanta-sis", instance.getNumbers().say(3456));
        assertEquals("Trenta mil cinc-cents quaranta-tres", instance.getNumbers().say(30_543));
        assertEquals("Cent mil", instance.getNumbers().say(100_000));
        assertEquals("Nou-cents noranta-vuit mil vuit-cents vuitanta-nou", instance.getNumbers().say(998_889));
    }

    @org.junit.Test
    public void testMilionaris() {
        SayBigNumberFactory instance = SayBigNumberFactory.getInstance();
        assertEquals("Un milió dos-cents trenta-quatre mil cinc-cents seixanta-set",
                instance.getNumbers().say(1_234_567));
        assertEquals("Vint-i-dos milions cinc-cents quaranta-tres mil",
                instance.getNumbers().say(22_543_000));
        assertEquals("Nou-cents un milions set",
                instance.getNumbers().say(901_000_007));
        assertEquals("Nou-cents nou milions nou-cents noranta-nou mil nou-cents noranta-nou",
                instance.getNumbers().say(909_999_999));
        assertEquals("Mil milions",
                instance.getNumbers().say(1_000_000_000));
        assertEquals("Quinze mil vint milions noranta-vuit mil cinc-cents quaranta-vuit",
                instance.getNumbers().say(15_020_098_548L));
        assertEquals("Nou-cents noranta-nou mil milions cinc mil tres-cents noranta-dos",
                instance.getNumbers().say(999_000_005_392L));
    }

    @org.junit.Test
    public void testNombresBilionaris() {
        SayBigNumberFactory instance = SayBigNumberFactory.getInstance();
        assertEquals("Un bilió",
                instance.getNumbers().say(1_000_000_000_000L));
        assertEquals("Mil bilions",
                instance.getNumbers().say(1_000_000_000_000_000L));
        assertEquals("Cent seixanta-cinc mil quaranta-tres bilions dos-cents disset mil nou-cents vuitanta-set milions cinquanta mil cent sis",
                instance.getNumbers().say(165_043_217_987_050_106L));
        assertEquals("Nou mil onze bilions sis-cents cinquanta-tres milions vint mil vuit",
                instance.getNumbers().say(9_011_000_653_020_008L));
        assertEquals("Nou-cents noranta-nou mil bilions",
                instance.getNumbers().say(999_000_000_000_000_000L));
    }

    @org.junit.Test
    public void testNombresTrilionaris() {
        SayBigNumberFactory instance = SayBigNumberFactory.getInstance();
        assertEquals("Un trilió",
                instance.getNumbers().say(1_000_000_000_000_000_000L));
        assertEquals("Nou trilions dos-cents vint-i-tres mil tres-cents setanta-dos bilions trenta-sis mil vuit-cents cinquanta-quatre milions set-cents setanta-cinc mil vuit-cents set",
                instance.getNumbers().say(9_223_372_036_854_775_807L));
    }

}

