package com.empresa.paqueteria.services

import com.empresa.paqueteria.models._

object AsignacionService {
  def asignarPaquetes(paquetes: List[Paquete], repartidorCoche: Repartidor, repartidorFurgoneta: Repartidor): Unit = {
    val (pesados, ligeros) = SegmentacionService.segmentarPaquetes(paquetes)
    pesados.foreach(repartidorFurgoneta.asignarPaquete)
    ligeros.foreach(repartidorCoche.asignarPaquete)
  }
}