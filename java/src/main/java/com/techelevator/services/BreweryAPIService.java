package com.techelevator.services;

import com.techelevator.model.Brewery;
import com.techelevator.model.BreweryDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class BreweryAPIService {

    private static final String API_URL = "https://api.openbrewerydb.org/breweries?by_state=";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Brewery> getMissouriBrewery() {
        BreweryDTO[] fullDTO = restTemplate.getForObject(API_URL + "missouri", BreweryDTO[].class);
        List<Brewery> missouriBreweries = new ArrayList<>();
        if (fullDTO !=  null){
            for (int i = 0; i < fullDTO.length; i++) {
                missouriBreweries.add(breweryMapper(fullDTO[i]));
            }
        }
        fullDTO = restTemplate.getForObject(API_URL + "arkansas", BreweryDTO[].class);
        if (fullDTO !=  null){
            for (int i = 0; i < fullDTO.length; i++) {
                missouriBreweries.add(breweryMapper(fullDTO[i]));
            }
        }

        return missouriBreweries;
    }



    private Brewery breweryMapper(BreweryDTO brewery) {
        Brewery newBrewery = new Brewery();
        newBrewery.setBreweryCity(brewery.getCity());
        newBrewery.setBreweryName(brewery.getName());
        newBrewery.setBreweryState(brewery.getState());
        newBrewery.setBreweryStreet(brewery.getStreet());
        newBrewery.setBreweryType(brewery.getBrewery_type());
        newBrewery.setPhoneNumber(brewery.getPhone());
        newBrewery.setPostalCode(brewery.getPostal_code());
        newBrewery.setWebsiteUrl(brewery.getWebsite_url());
        newBrewery.setPhoneNumber("");
        newBrewery.setBreweryStatus("approved");
        return newBrewery;
    }
}
