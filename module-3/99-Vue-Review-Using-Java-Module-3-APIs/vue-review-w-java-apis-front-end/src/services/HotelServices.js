import axios from 'axios';

const hotelAPI = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  getHotels() {
    return hotelAPI.get('/hotels');
  }
}
