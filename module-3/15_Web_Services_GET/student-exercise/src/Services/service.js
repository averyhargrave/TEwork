import axios from 'axios';

const http = axios.create({          // instantiate an axios object 
    baseURL: "http://localhost:3000" // to reference this server 
    });
 
export default {
    getTopics() {
        return http.get('/topics');
    },
    get(id) {
      return http.get(`/topics/${id}`)
    }
}