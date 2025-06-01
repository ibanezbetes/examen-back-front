import { MovieService } from "./api/movieService.js";
import { MovieComponent } from "./components/movieComponent.js";

const app      = document.getElementById("app");
const filtros  = document.getElementById("filtros");

function pintarLista(peliculas){
  app.innerHTML = "";
  peliculas.forEach(p => {
    const comp = new MovieComponent(p);
    app.appendChild(comp.render());
  });
}

async function cargarPeliculas(){
  const lista = await MovieService.listarPeliculas();
  pintarLista(lista);
}

async function cargarGeneros(){
  const generos = await MovieService.listarGeneros();
  filtros.innerHTML = "";
  // Botón Todos
  const btnTodos = document.createElement("button");
  btnTodos.textContent = "Todos";
  btnTodos.addEventListener("click", cargarPeliculas);
  filtros.appendChild(btnTodos);

  generos.forEach(gen => {
    const btn = document.createElement("button");
    btn.textContent = gen;
    btn.addEventListener("click", () => cargarPorGenero(gen));
    filtros.appendChild(btn);
  });
}

async function cargarPorGenero(gen){
  const lista = await MovieService.listarPorGenero(gen);
  pintarLista(lista);
}

window.addEventListener("load", () => {
  cargarPeliculas();
  cargarGeneros();
});