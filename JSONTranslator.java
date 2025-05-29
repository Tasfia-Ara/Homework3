package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                var name = jsonArray.getJSONObject(i);
                String country = name.getString("country");
                var languages = name.getJSONObject("languages");

                Map<String, String> langMap = new HashMap<>();
                for (String langCode : languages.keySet()) {
                    langMap.put(langCode, languages.getString(langCode));
                }
                countryTranslations.put(country, langMap);
            }

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        Map<String, String> langMap = countryTranslations.get(country);
        if (langMap == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(langMap.keySet());
    }

    @Override
    public List<String> getCountries() {
        return new ArrayList<>(countryTranslations.keySet());
    }

    @Override
    public String translate(String country, String language) {
        Map<String, String> langMap = countryTranslations.get(country);
        if (langMap != null) {
            return langMap.get(language);
        }
        return null;
    }
}
