// Arquivo: frontend/src/services/api.ts
import axios from "axios";

// Cria uma instância do axios com uma configuração base
const api = axios.create({
  // A URL base da sua API backend.
  // O frontend rodará em uma porta (ex: 5173) e o backend em outra (8080).
  baseURL: "http://localhost:8080",
});


// será executado antes de cada requisição
api.interceptors.request.use(
  (config) => { 

    const token = localStorage.getItem('vollmed_token');

    if(token){
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config
  },
  (error) => {
    return Promise.reject(error);
  }
)


export default api;