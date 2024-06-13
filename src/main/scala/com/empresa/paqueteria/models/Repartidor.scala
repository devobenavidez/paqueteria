package com.empresa.paqueteria.models

import com.empresa.paqueteria.services.NotificacionService

// La clase abstracta Repartidor define el comportamiento general de un repartidor.
abstract class Repartidor(codigosPostalesPermitidos: Set[String]) {
  // Constructor que imprime el tamaño del conjunto de códigos postales permitidos.
  println(s"Constructor de Repartidor - tamaño de codigosPostalesPermitidos: ${codigosPostalesPermitidos.size}")

  // Verificación de que no se asignen más de 4 códigos postales.
  require(codigosPostalesPermitidos.size <= 4, "No se pueden asignar más de 4 códigos postales a un repartidor")
  println("Pasó la validación de require")

  // Valor que define el peso máximo permitido para los paquetes asignados al repartidor.
  protected val pesoMaximoPermitido: Double

  // Lista mutable para almacenar los paquetes asignados.
  private var paquetesAsignados: List[Paquete] = List()

  // Función para asignar un paquete al repartidor.
  def asignarPaquete(paquete: Paquete): Unit = {
    // Llama a una función auxiliar para intentar asignar el paquete.
    val asignado = asignarPaqueteAux(paquete)
    if (asignado) {
      // Si se asigna correctamente, imprime un mensaje de éxito.
      println(s"Paquete ${paquete.id} asignado a la ruta de ${this.getClass.getSimpleName}")
    } else {
      // Si no se puede asignar, imprime un mensaje de error.
      println(s"Error: El paquete ${paquete.id} no pudo ser asignado.")
    }
  }

  // Función auxiliar protegida para realizar la lógica de asignación de paquetes.
  protected def asignarPaqueteAux(paquete: Paquete): Boolean = {
    // Imprime información sobre el paquete que se intenta asignar.
    println(s"Intentando asignar paquete ${paquete.id} con peso ${paquete.peso}")
    if (codigosPostalesPermitidos.contains(paquete.codigoPostal)) {
      // Calcula el peso total de los paquetes asignados más el nuevo paquete.
      val pesoTotal = paquetesAsignados.map(_.peso).sum + paquete.peso
      println(s"Peso total con el nuevo paquete: $pesoTotal")
      if (pesoTotal <= pesoMaximoPermitido) {
        // Si el peso total es permitido, agrega el paquete a la lista de paquetes asignados.
        paquetesAsignados = paquete :: paquetesAsignados
        return true
      } else {
        // Si el peso total excede el límite, imprime un mensaje de error.
        println(s"Error: El paquete ${paquete.id} excede el peso máximo permitido para este repartidor")
      }
    } else {
      // Si el código postal del paquete no está permitido, imprime un mensaje de error.
      println(s"Error: Código postal ${paquete.codigoPostal} no permitido para este repartidor")
    }
    // Devuelve false si no se pudo asignar el paquete.
    false
  }

  // Función para registrar la entrega de un paquete.
  def entregarPaquete(paquete: Paquete, firmaODni: String): Unit = {
    // Crea una copia del paquete con el estado de entrega actualizado.
    val paqueteEntregado = paquete.copy(estadoEntrega = true)
    // Registra la fecha y hora de la entrega.
    val fechaHoraEntrega = java.time.LocalDateTime.now()
    println(s"Paquete ${paqueteEntregado.id} entregado. Firma/DNI: $firmaODni.")
    println(s"Entrega registrada el $fechaHoraEntrega para el paquete ${paqueteEntregado.id}.")
    // Llama al servicio de notificación para enviar una notificación de entrega.
    NotificacionService.enviarNotificacion(paqueteEntregado)
  }

  // Función para obtener la lista de paquetes asignados.
  def obtenerPaquetesAsignados: List[Paquete] = paquetesAsignados
}