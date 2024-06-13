package com.empresa.paqueteria.services

import com.empresa.paqueteria.models.Paquete

// El objeto SegmentacionService proporciona métodos para segmentar paquetes
object SegmentacionService {
  // Función para segmentar una lista de paquetes en dos listas: pesados y ligeros
  def segmentarPaquetes(paquetes: List[Paquete]): (List[Paquete], List[Paquete]) = {
    // Se usa la función partition para dividir los paquetes según su peso
    // Los paquetes con peso mayor a 2 se consideran pesados, los demás ligeros
    paquetes.partition(_.peso > 2)
  }
}