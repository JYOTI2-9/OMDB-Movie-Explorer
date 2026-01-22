import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { ArrowLeft, Calendar, Clock, Star, Film } from 'lucide-react';
import toast from 'react-hot-toast';
import { getMovieDetails } from '../services/movieService';

const MovieDetailPage = () => {
  const { imdbId } = useParams();
  const navigate = useNavigate();
  const [movie, setMovie] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchMovieDetails();
  }, [imdbId]);

  const fetchMovieDetails = async () => {
    setLoading(true);
    try {
      const data = await getMovieDetails(imdbId);
      setMovie(data);
      toast.success('Movie details loaded');
    } catch (error) {
      toast.error(error.response?.data?.message || 'Failed to load movie details');
      navigate('/');
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <div className="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-primary"></div>
          <p className="mt-4 text-gray-600">Loading movie details...</p>
        </div>
      </div>
    );
  }

  if (!movie) return null;

  const ratings = movie.ratings || movie.Ratings || [];

  return (
    <div className="min-h-screen bg-gray-50 py-8 px-4">
      <div className="max-w-6xl mx-auto">
        <button
          onClick={() => navigate('/')}
          className="flex items-center space-x-2 text-primary hover:text-primary-hover mb-6"
        >
          <ArrowLeft className="w-5 h-5 cursor-pointer" />
          <span className="text-semibold cursor-pointer">Back to search</span>
        </button>

        <div className="bg-white rounded-lg shadow-lg shadow-primary/20 border border-gray-100 overflow-hidden">
          <div className="md:flex">
            <div className="md:w-1/3 bg-gray-200">
              {movie.Poster && movie.Poster !== 'N/A' ? (
                <img
                  src={movie.Poster}
                  alt={movie.Title}
                  className="w-full h-full object-cover"
                />
              ) : (
                <div className="w-full h-96 flex items-center justify-center">
                  <Film className="w-24 h-24 text-gray-400" />
                </div>
              )}
            </div>

            <div className="md:w-2/3 p-8">
              <h1 className="text-3xl font-bold text-gray-900 mb-2">{movie.Title}</h1>
              <p className="text-gray-600 mb-6">{movie.Genre}</p>

              <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mb-6">
                <div className="flex items-center space-x-2">
                  <Calendar className="w-5 h-5 text-primary" />
                  <div>
                    <p className="text-xs text-gray-500">Year</p>
                    <p className="font-semibold text-gray-900">{movie.Year}</p>
                  </div>
                </div>

                <div className="flex items-center space-x-2">
                  <Clock className="w-5 h-5 text-primary" />
                  <div>
                    <p className="text-xs text-gray-500">Runtime</p>
                    <p className="font-semibold text-gray-900">{movie.Runtime}</p>
                  </div>
                </div>

                <div className="flex items-center space-x-2">
                  <Star className="w-5 h-5 text-primary" />
                  <div>
                    <p className="text-xs text-gray-500">IMDB Rating</p>
                    <p className="font-semibold text-gray-900">{movie.imdbRating}</p>
                  </div>
                </div>

                <div className="flex items-center space-x-2">
                  <Film className="w-5 h-5 text-primary" />
                  <div>
                    <p className="text-xs text-gray-500">Type</p>
                    <p className="font-semibold text-gray-900">{movie.Type}</p>
                  </div>
                </div>
              </div>

              <div className="space-y-4">
                <div>
                  <h3 className="text-sm font-semibold text-gray-500 uppercase mb-1">Plot</h3>
                  <p className="text-gray-700">{movie.Plot}</p>
                </div>

                <div>
                  <h3 className="text-sm font-semibold text-gray-500 uppercase mb-1">Director</h3>
                  <p className="text-gray-700">{movie.Director}</p>
                </div>

                <div>
                  <h3 className="text-sm font-semibold text-gray-500 uppercase mb-1">Actors</h3>
                  <p className="text-gray-700">{movie.Actors}</p>
                </div>

                {ratings.length > 0 && (
                    <div>
                      <h3 className="text-sm font-semibold text-gray-500 uppercase mb-2">Ratings</h3>
                      <div className="flex flex-wrap gap-3">
                        {ratings.map((rating, index) => (
                          <div key={index} className="bg-gray-100 px-3 py-2 rounded">
                            <p className="text-xs text-gray-600">{rating.Source}</p>
                            <p className="font-semibold text-gray-900">{rating.Value}</p>
                          </div>
                        ))}
                      </div>
                    </div>
                  )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MovieDetailPage;