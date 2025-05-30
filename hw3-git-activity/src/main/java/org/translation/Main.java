package org.translation;

import java.util.*;
import java.util.Collections;

/**
 * Main class for this program.
 * Complete the code according to the "to do" notes.<br/>
 * The system will:<br/>
 * - prompt the user to pick a country name from a list<br/>
 * - prompt the user to pick the language they want it translated to from a list<br/>
 * - output the translation<br/>
 * - at any time, the user can type quit to quit the program<br/>
 */
public class Main {

    /**
     * This is the main entry point of our Translation System!<br/>
     * A class implementing the Translator interface is created and passed into a call to runProgram.
     * @param args not used by the program
     */
    public static void main(String[] args) {
        Translator translator = new JSONTranslator();
        //Translator translator = new InLabByHandTranslator();
        runProgram(translator);
    }

    /**
     * This is the method which we will use to test your overall program, since
     * it allows us to pass in whatever translator object that we want!
     * See the class Javadoc for a summary of what the program will do.
     * @param translator the Translator implementation to use in the program
     */
    public static void runProgram(Translator translator) {
        while (true) {
            String country = promptForCountry(translator);
            CountryCodeConverter converter = new CountryCodeConverter();
            String code = converter.fromCountry(country);
            String q = "quit";

            if (q.equals(country)) {
                break;
            }
            String language = promptForLanguage(translator, code.toLowerCase()); // fix later if needed
            if (q.equals(language)) {
                break;
            }
            LanguageCodeConverter langConverter = new LanguageCodeConverter();
            String countryCode = converter.fromCountry(country).toLowerCase();
            String langCode = langConverter.fromLanguage(language).toLowerCase();
            System.out.println(country + " in " + language + " is " + translator.translate(countryCode, langCode));
            System.out.println("Press enter to continue or quit to exit.");
            Scanner s = new Scanner(System.in);
            String textTyped = s.nextLine();

            if ("quit".equals(textTyped)) {
                break;
            }
        }
    }
    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForCountry(Translator translator) {
        List<String> countries = translator.getCountries();
        CountryCodeConverter converter = new CountryCodeConverter();
        List<String> countryNames = new ArrayList<>();
        for (String countryCode : countries){
            countryNames.add(converter.fromCountryCode(countryCode));
        }
//        countryNames.sort(Comparator.naturalOrder());
//        Collections.sort(countryNames);
        countryNames.sort(null);
        for (String countryName : countryNames) {
            System.out.println(countryName);
        }
        System.out.println("select a country from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();

    }
    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForLanguage(Translator translator, String country) {
        List<String> languages = translator.getCountryLanguages(country);
        languages.sort(null);
        for (String language : languages) {
            LanguageCodeConverter langConverter = new LanguageCodeConverter();
            System.out.println(langConverter.fromLanguageCode(language));
        }
//        System.out.println(translator.getCountryLanguages(country));

        System.out.println("select a language from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
