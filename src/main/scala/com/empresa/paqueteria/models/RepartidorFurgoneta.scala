package com.empresa.paqueteria.models

class RepartidorFurgoneta(codigosPostalesPermitidos: Set[String]) extends Repartidor(codigosPostalesPermitidos) {
  override def asignarPaquete(paquete: Paquete): Unit = {
    if (codigosPostalesPermitidos.contains(paquete.codigoPostal)) {
      super.asignarPaquete(paquete)
      println(s"Paquete ${paquete.id} asignado a la ruta de Lucas (furgoneta)")
    } else {
      println(s"Error: Código postal ${paquete.codigoPostal} no permitido para Lucas")
    }
  }

  override def entregarPaquete(paquete: Paquete): Unit = {
    val paqueteEntregado = paquete.copy(estadoEntrega = true)
    println(s"Paquete ${paqueteEntregado.id} entregado por Lucas en furgoneta.")
    val fechaHoraEntrega = java.time.LocalDateTime.now()
    println(s"Entrega registrada el ${fechaHoraEntrega} para el paquete ${paqueteEntregado.id}.")
  }

  override def obtenerPaquetesAsignados: List[Paquete] = super.obtenerPaquetesAsignados
}