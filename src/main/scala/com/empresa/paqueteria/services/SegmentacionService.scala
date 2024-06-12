package com.empresa.paqueteria.services

import com.empresa.paqueteria.models.Paquete

object SegmentacionService {
  def segmentarPaquetes(paquetes: List[Paquete]): (List[Paquete], List[Paquete]) = {
    paquetes.partition(_.peso > 2)
  }
}