import { FetchUtil } from "../util/fetchUtil.js";

export class VehicleRepository {
  // Endpoints = acciones del backend
  static async getAll() {
    const url = `${FetchUtil.BASE_URL}ACTION=VEHICULO.FIND_ALL`;
    return VehicleRepository.#fetchJson(url);
  }

  static async getByFilter(filtro) {
    const url =
      `${FetchUtil.BASE_URL}ACTION=VEHICULO.FILTER&FILTRO=` +
      encodeURIComponent(filtro);
    return VehicleRepository.#fetchJson(url);
  }

  // ――――――――――――――――――― PRIVATE ――――――――――――――――――― //
  static async #fetchJson(url) {
    try {
      const resp = await fetch(url);
      if (!resp.ok) throw new Error("Error al pedir vehículos");
      return await resp.json(); // [{ matricula, modelo, kilometros }, …]
    } catch (e) {
      console.error(e);
      return [];
    }
  }
}
