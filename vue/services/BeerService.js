import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:9000",
});

export default {
  // ***** BEER METHODS *****

  //return list of beers
  getAllBeers() {
    return http.get("/beers");
  },

  //returns a beers by brewery ID
  getBeersByBreweryID(breweryID) {
    return http.get(`/beers/${breweryID}`);
  },

  //creates new beer (ROLE >> Admin/Brewer)
  createBeer(beer, breweryID) {
    return http.post(`/beers/${breweryID}`, beer, 
    {
      headers:{Authorization: 'Bearer ' + localStorage.getItem('token')}
    });
  },

  //updates an existing beer based on beer ID (ROLE >> Admin/Brewer)
  updateBeer(beerID, beer) {
    return http.put(`/beers/${beerID}`, beer);
  },

  //deletes an existing beer based on beer ID (ROLE >> Admin/Brewer)
  deleteBeer(beerID) {
    return http.delete(`/beers/${beerID}`);
  },

  // ***** BEERLIST METHODS *****

  //add beer to beer list based on brewery ID (ROLE >> Admin/Brewer)
  addToBeerList(breweryID) {
    return http.post(`/beerlist/${breweryID}`);
  },

  //delete beer from beer list based on brewery ID (ROLE >> Admin/Brewer)
  deleteFromBeerList(breweryID) {
    return http.post(`/beerlist/${breweryID}`);
  },





};
