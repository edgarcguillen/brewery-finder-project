import axios from "axios";

const http = axios.create({
  baseURL: "http://localhost:9000",
  
});


// axios.interceptors.request.use(
//   config => {
//     config.headers['Authorization'] = `Bearer ${localStorage.getItem('token')}`;
//         return config;
//     },
//     error => {
//         return Promise.reject(error);
//     }
// );

export default {

  //return list of breweries
  getAllBreweries() {
    return http.get("/breweries");
  },

  //returns a brewery by ID
  getBrewery(id) {
    return http.get(`/breweries/${id}`);
  },

  // ***** THIS NEEDS TO BE WIRED ON THE BACKEND *****
  // returns a brewery by userID (ROLE >> BREWER), called in brewer dashboard
  getBreweryByUserId(brewerID) {
    return http.get(`/brewer/${brewerID}`);
  },

  //creates new brewery (User >> Brewer)
  createBrewery(brewery, brewerID) {
    return http.post(`/dashboard/${brewerID}/brewery/create`, brewery,
    {
      headers:{Authorization: 'Bearer ' + localStorage.getItem('token')}
    });
  },

  //updates an existing brewery using the PUT method
  updateBrewery(brewery, brewerID, breweryID){
    return http.put(`/dashboard/${brewerID}/brewery/${breweryID}/edit`, brewery,
    {
      headers:{Authorization: 'Bearer ' + localStorage.getItem('token')}
    });
  },  

  //deletes an existing brewery based on topic ID
  deleteBrewery(id){
    return http.delete(`/breweries/${id}`);
  },
  
  //TODO 
  //adds a brewery to users favorites 
  addBreweryToFavorites(userID, breweryID){
    return http.post(`breweries/favorites/${userID}`, {userID, breweryID},
        {
          headers:{Authorization: 'Bearer ' + localStorage.getItem('token')}
        }
    );
  },

  removeBreweryFromFavorites(userID, breweryID) {
    return http.delete(`breweries/favorites/${userID}/${breweryID}`,
        {
          headers:{Authorization: 'Bearer ' + localStorage.getItem('token')}
        }
    )
  },

  //gets list of favorite breweries
  getFavorites(id){
    return http.get(`/breweries/favorites/${id}`,
    {
      headers:{Authorization: 'Bearer ' + localStorage.getItem('token')}
    });
  },

  //gets list of breweries to approve 
  getPendingBreweries(){
    return http.get("/admin");
  },

  //adds approved brewery to the DB
  approveTheBrewery(id) {
    return http.put(`/admin/${id}`, id,
    {
      headers:{Authorization: 'Bearer ' + localStorage.getItem('token')}
    });
  },
};
