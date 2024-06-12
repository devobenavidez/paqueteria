package com.empresa.paqueteria.models

class RepartidorCoche(codigosPostalesPermitidos: Set[String]) extends Repartidor(codigosPostalesPermitidos) {
  override protected val pesoMaximoPermitido: Double = 500.0

  override def asignarPaquete(paquete: Paquete): Unit = {
    val asignado = super.asignarPaqueteAux(paquete)
    if (asignado) {
      println(s"Paquete ${paquete.id} asignado a la ruta de Ana (coche)")
    } else {
      println(s"Error: No se pudo asignar el paquete ${paquete.id} a la ruta de Ana (coche)")
    }
  }

  override def entregarPaquete(paquete: Paquete): Unit = {
    val paqueteEntregado = paquete.copy(estadoEntrega = true)
    println(s"Paquete ${paqueteEntregado.id} entregado por Ana en coche.")
    val fechaHoraEntrega = java.time.LocalDateTime.now()
    println(s"Entrega registrada el ${fechaHoraEntrega} para el paquete ${paqueteEntregado.id}.")
  }

  override def obtenerPaquetesAsignados: List[Paquete] = super.obtenerPaquetesAsignados
}