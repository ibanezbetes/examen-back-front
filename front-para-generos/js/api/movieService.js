import { MovieRepository } from "../repository/movieRepository.js";

export class MovieService {
  static listarPeliculas(){
    return MovieRepository.getMovies();
  }
  static listarGeneros(){
    return MovieRepository.getGenres();
  }
  static listarPorGenero(g){
    return MovieRepository.getByGenre(g);
  }
}