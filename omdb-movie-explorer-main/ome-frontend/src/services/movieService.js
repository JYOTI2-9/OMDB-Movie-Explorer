import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/movies';

export const searchMovies = async (title) => {
  const response = await axios.get(`${API_BASE_URL}/search`, {
    params: { title }
  });
  return response.data;
};

export const getMovieDetails = async (imdbId) => {
  const response = await axios.get(`${API_BASE_URL}/${imdbId}`);
  return response.data;
};

