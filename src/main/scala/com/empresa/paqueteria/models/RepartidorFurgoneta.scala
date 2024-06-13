package com.empresa.paqueteria.models

import com.empresa.paqueteria.services.NotificacionService

// La clase RepartidorFurgoneta extiende la clase abstracta Repartidor
class RepartidorFurgoneta(codigosPostalesPermitidos: Set[String]) extends Repartidor(codigosPostalesPermitidos) {

  // Define el peso máximo permitido para los paquetes asignados a un repartidor de furgoneta
  override protected val pesoMaximoPermitido: Double = 1000.0

  // Sobrescribe la función asignarPaquete de la clase Repartidor
  override def asignarPaquete(paquete: Paquete): Unit = {
    // Verifica si el código postal del paquete está permitido
    if (codigosPostalesPermitidos.contains(paquete.codigoPostal)) {
      // Llama a la función asignarPaquete de la clase base para intentar asignar el paquete
      super.asignarPaquete(paquete)
      // Imprime un mensaje de éxito
      println(s"Paquete ${paquete.id} asignado a la ruta de Lucas (furgoneta)")
    } else {
      // Imprime un mensaje de error si el código postal no está permitido
      println(s"Error: Código postal ${paquete.codigoPostal} no permitido para Lucas")
    }
  }

  // Sobrescribe la función entregarPaquete de la clase Repartidor
  override def entregarPaquete(paquete: Paquete, firmaODni: String): Unit = {
    // Crea una copia del paquete con el estado de entrega actualizado
    val paqueteEntregado = paquete.copy(estadoEntrega = true)
    // Imprime un mensaje indicando que Lucas entregó el paquete
    println(s"Paquete ${paqueteEntregado.id} entregado por Lucas en furgoneta. Firma/DNI: $firmaODni.")
    // Registra la fecha y hora de la entrega
    val fechaHoraEntrega = java.time.LocalDateTime.now()
    println(s"Entrega registrada el ${fechaHoraEntrega} para el paquete ${paqueteEntregado.id}.")
    // Llama al servicio de notificación para enviar una notificación de entrega
    NotificacionService.enviarNotificacion(paqueteEntregado)
  }

  // Sobrescribe la función obtenerPaquetesAsignados de la clase Repartidor
  override def obtenerPaquetesAsignados: List[Paquete] = super.obtenerPaquetesAsignados
}