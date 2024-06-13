package com.empresa.paqueteria.services

import com.empresa.paqueteria.models._

// El objeto AsignacionService proporciona métodos para asignar paquetes a los repartidores
object AsignacionService {
  // Función para asignar una lista de paquetes a dos repartidores (coche y furgoneta)
  def asignarPaquetes(paquetes: List[Paquete], repartidorCoche: Repartidor, repartidorFurgoneta: Repartidor): Unit = {
    // Se segmentan los paquetes en pesados y ligeros utilizando el SegmentacionService
    val (pesados, ligeros) = SegmentacionService.segmentarPaquetes(paquetes)
    // Se asignan los paquetes pesados al repartidor de furgoneta
    pesados.foreach(repartidorFurgoneta.asignarPaquete)
    // Se asignan los paquetes ligeros al repartidor de coche
    ligeros.foreach(repartidorCoche.asignarPaquete)
  }
}
