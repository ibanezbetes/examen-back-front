export class VehicleComponent {
  constructor(vehicle) {
    this.vehicle = vehicle;
  }

  render() {
    const el = document.createElement("div");
    el.classList.add("vehicle");

    el.innerHTML = `
      <h3>${this.vehicle.matricula}</h3>
      <p><strong>Modelo:</strong> ${this.vehicle.modelo}</p>
      <p><strong>Kilómetros:</strong> ${this.vehicle.kilometros.toLocaleString()}</p>
    `;

    return el;
  }
}
