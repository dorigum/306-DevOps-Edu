import axios from "axios";

const serverURL = import.meta.env.VITE_API_SERVER_IP;

const axiosInstance = axios.create({
  baseURL: serverURL,
  timeout: 5000,
});

export default axiosInstance;
