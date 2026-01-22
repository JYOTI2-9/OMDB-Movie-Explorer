import { useState } from 'react';
import { Film } from 'lucide-react';
import toast from 'react-hot-toast';
import SearchBar from '../components/SearchBar';
import MovieCard from '../components/MovieCard';
import { searchMovies } from '../services/movieService';

const HomePage = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(false);

  const handleSearch = async () => {
    if (!searchTerm.trim()) {
      toast.error('Please enter a movie name');
      return;
    }

    setLoading(true);
    try {
      const data = await searchMovies(searchTerm);
      
      if (data.Search && data.Search.length > 0) {
        setMovies(data.Search);
        toast.success(`Found ${data.totalResults} results`);
      } else {
        setMovies([]);
        toast.error('No movies found');
      }
    } catch (error) {
      toast.error(error.response?.data?.message || 'Failed to search movies');
      setMovies([]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 py-8 px-4">
      <div className="max-w-7xl mx-auto">
        <div className="text-center mb-8">
          <div className="flex items-center justify-center space-x-3 mb-4">
            <Film className="w-10 h-10 text-primary" />
            <h1 className="text-4xl font-bold text-gray-900">OMDB Movie Explorer</h1>
          </div>
          <p className="text-gray-600">Search for your favorite movies and series</p>
        </div>

        <div className="mb-8">
          <SearchBar
            searchTerm={searchTerm}
            setSearchTerm={setSearchTerm}
            onSearch={handleSearch}
          />
        </div>

        {loading && (
          <div className="text-center py-12">
            <div className="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-primary"></div>
            <p className="mt-4 text-gray-600">Searching...</p>
          </div>
        )}

        {!loading && movies.length > 0 && (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
            {movies.map((movie) => (
              <MovieCard key={movie.imdbID} movie={movie} />
            ))}
          </div>
        )}

        {!loading && movies.length === 0 && searchTerm && (
          <div className="text-center py-12">
            <Film className="w-16 h-16 text-gray-400 mx-auto mb-4" />
            <p className="text-gray-600">No results found. Try another search.</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default HomePage;