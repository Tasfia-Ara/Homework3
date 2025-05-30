package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.JSONArray;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    private final Map<String, Map<String, String>> countryTranslations = new HashMap<>();

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */
    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                Map<String, String> translations = new HashMap<>();
                var name = jsonArray.getJSONObject(i);
//                for (int j = 2; j < jsonArray.length(); j++) {
//                    String key = jsonArray.getString(j);
//                    translations.put(jsonArray.getString(j), jsonArray.getString(j - 1));
//                }
                for (var key : name.keySet()) {
                    if (!Objects.equals(key, "id") && !Objects.equals(key, "alpha2") && !Objects.equals(key, "alpha3")){
                        translations.put(key, jsonArray.getJSONObject(i).get(key).toString());
                    }

                }
                String countryCode = jsonArray.getJSONObject(i).get("alpha3").toString();
                countryTranslations.put(countryCode, translations);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
//        CountryCodeConverter converter = new CountryCodeConverter();
//        String countryName = converter.fromCountryCode(country);
        String countryLower = country.toLowerCase();
        List<String> languages = new ArrayList<>();
        Map<String, ?> translations = countryTranslations.get(countryLower);
        if (translations != null) {
            languages.addAll(translations.keySet());
        }
        return languages;
    }

    @Override
    public List<String> getCountries() {
//        CountryCodeConverter countryCodeConverter = new CountryCodeConverter();
        List<String> countries = new ArrayList<>();
        for (String code : countryTranslations.keySet()) {
            countries.add(code);
        }
        return countries;
    }
    @Override
    public String translate(String country, String language) {
        var values = countryTranslations.get(country);
        return values.get(language);

    }
}
