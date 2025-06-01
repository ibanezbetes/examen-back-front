import { VehicleRepository } from "../repository/vehicleRepository.js";

export class VehicleService {
  static listarVehiculos() {
    return VehicleRepository.getAll();
  }

  static filtrarVehiculos(cadena) {
    return VehicleRepository.getByFilter(cadena);
  }
}
