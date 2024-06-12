package com.empresa.paqueteria.models

class RepartidorCoche(codigosPostalesPermitidos: Set[String]) extends Repartidor(codigosPostalesPermitidos) {
  override def asignarPaquete(paquete: Paquete): Unit = {
    if (codigosPostalesPermitidos.contains(paquete.codigoPostal)) {
      super.asignarPaquete(paquete)
      println(s"Paquete ${paquete.id} asignado a la ruta de Ana (coche)")
    } else {
      println(s"Error: Código postal ${paquete.codigoPostal} no permitido para Ana")
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