package org.translation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LanguageCodeConverterTest {

    @Test
    public void fromLanguageCodeEN() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals("English", converter.fromLanguageCode("en"));
    }

    @Test
    public void fromLanguageCodeAllLoaded() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals(184, converter.getNumLanguages());
    }

    @Test
    public void fromLanguageCodeCa() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals("Catalan, Valencian", converter.fromLanguageCode("ca"));
    }

    @Test
    public void fromLanguageCodeDv() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals("Dhivehi, Divehi, Maldivian", converter.fromLanguageCode("dv"));
    }

    @Test
    public void fromLanguageAb() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals("ab", converter.fromLanguage("Abkhazian"));
    }

    @Test
    public void fromLanguageCa() {
        LanguageCodeConverter converter = new LanguageCodeConverter();
        assertEquals("ca", converter.fromLanguage("Valencian"));
    }

}