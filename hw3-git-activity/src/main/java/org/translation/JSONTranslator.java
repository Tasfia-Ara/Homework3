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

    private final Map<String, ArrayList<String>> countryTranslations = new HashMap<>();

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
                //making changes to Isha's code
                for (String langCode : name.keySet()) {
                    if (!countryTranslations.containsKey(langCode)) {
                        countryTranslations.put(langCode, new ArrayList<>());
                    }
                    countryTranslations.get(langCode).add(jsonArray.getJSONObject(i).getString("translation"));
                            name.get(langCode));
                }
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        List<String> languageList = new ArrayList<>();
        for (String langCode : countryTranslations.keySet()) {
            if (countryTranslations.get(langCode).contains(country)) {
                languageList.add(langCode);
            }
        }
        if(!languageList.isEmpty()){
            return languageList;
        }
        else{
            return null;
        }
    }

    @Override
    public List<String> getCountries() {
        List<String> countries = new ArrayList<>();
        for (String langCode : countryTranslations.keySet()) {
            countries.addAll(countryTranslations.get(langCode));
        }
        return countries;
//        return new ArrayList<>(countryTranslations.keySet());
    }

    @Override
    public String translate(String country, String language) {
        for (String langCode : countryTranslations.keySet()) {
            for (int j = 0; j < countryTranslations.get(langCode).size(); j++) {
                if (countryTranslations.get(langCode).get(j).equals(language)) {
                    return countryTranslations.get(langCode).get(j);
                }
            }
        }
    return null;
    }
}