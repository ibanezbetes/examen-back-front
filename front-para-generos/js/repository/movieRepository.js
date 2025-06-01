import { FetchUtil } from "../util/fetchUtil.js";

export class MovieRepository {
  static async getMovies() {
    const url = `${FetchUtil.BASE_URL}?ACTION=PELICULA.FIND_ALL`;
    const resp = await fetch(url);
    return resp.ok ? resp.json() : [];
  }

  static async getGenres() {
    const url = `${FetchUtil.BASE_URL}?ACTION=GENERO.FIND_ALL`;
    const resp = await fetch(url);
    return resp.ok ? resp.json() : [];
  }

  static async getByGenre(nombre) {
    const url = `${FetchUtil.BASE_URL}?ACTION=PELICULA.FILTER&FILTRO=${encodeURIComponent(nombre)}`;
    const resp = await fetch(url);
    return resp.ok ? resp.json() : [];
  }
}