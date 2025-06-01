import { VehicleService } from "./api/vehicleService.js";
import { VehicleComponent } from "./components/vehicleComponent.js";

const appContainer = document.getElementById("app");
const inputSearch = document.getElementById("search");
const btnSearch   = document.getElementById("btnSearch");

// ──────────────────────────────────────────────────────────────
// Render helpers
function limpiarResultados() {
  appContainer.innerHTML = "";
}

function pintarVehiculos(lista) {
  limpiarResultados();
  lista.forEach(v => {
    const comp = new VehicleComponent(v);
    appContainer.appendChild(comp.render());
  });
  if (lista.length === 0) appContainer.textContent = "Sin resultados";
}

// ──────────────────────────────────────────────────────────────
// Carga inicial
window.addEventListener("load", async () => {
  const vehiculos = await VehicleService.listarVehiculos();
  pintarVehiculos(vehiculos);
});

// ──────────────────────────────────────────────────────────────
// Búsqueda
async function buscar() {
  const filtro = inputSearch.value.trim();
  if (filtro === "") {
    const vehiculos = await VehicleService.listarVehiculos();
    pintarVehiculos(vehiculos);
  } else {
    const vehiculos = await VehicleService.filtrarVehiculos(filtro);
    pintarVehiculos(vehiculos);
  }
}

btnSearch.addEventListener("click", buscar);
inputSearch.addEventListener("keyup", e => {
  if (e.key === "Enter") buscar();
});
