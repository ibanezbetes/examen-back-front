export class MovieComponent{
  constructor(movie){
    this.movie = movie;
  }

  render(){
    const el = document.createElement("div");
    el.classList.add("movie");
    el.innerHTML = `
      <h3>${this.movie.titulo}</h3>
      ${this.movie.imagen ? `<img src="${this.movie.imagen}" alt="${this.movie.titulo}">` : ""}
      <p>${this.movie.descripcion}</p>
      <p><strong>Género:</strong> ${this.movie.genero || "N/D"}</p>
      <p><strong>Estreno:</strong> ${new Date(this.movie.fechaEstreno || this.movie.fecha_lanzamiento).toLocaleDateString()}</p>
    `;
    return el;
  }
}