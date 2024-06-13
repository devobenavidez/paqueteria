package com.empresa.paqueteria.models

// La clase RepartidorCoche extiende la clase abstracta Repartidor
class RepartidorCoche(codigosPostalesPermitidos: Set[String]) extends Repartidor(codigosPostalesPermitidos) {

  // Define el peso máximo permitido para los paquetes asignados a un repartidor de coche
  override protected val pesoMaximoPermitido: Double = 500.0

  // Sobrescribe la función asignarPaquete de la clase Repartidor
  override def asignarPaquete(paquete: Paquete): Unit = {
    // Llama a la función auxiliar de la clase base para intentar asignar el paquete
    val asignado = super.asignarPaqueteAux(paquete)
    if (asignado) {
      // Si se asigna correctamente, imprime un mensaje de éxito
      println(s"Paquete ${paquete.id} asignado a la ruta de Ana (coche)")
    } else {
      // Si no se puede asignar, imprime un mensaje de error
      println(s"Error: No se pudo asignar el paquete ${paquete.id} a la ruta de Ana (coche)")
    }
  }

  // Sobrescribe la función entregarPaquete de la clase Repartidor
  override def entregarPaquete(paquete: Paquete, firmaODni: String): Unit = {
    // Llama a la función entregarPaquete de la clase base para registrar la entrega
    super.entregarPaquete(paquete, firmaODni)
    // Imprime un mensaje indicando que Ana entregó el paquete
    println(s"Paquete ${paquete.id} entregado por Ana en coche.")
  }

  // Sobrescribe la función obtenerPaquetesAsignados de la clase Repartidor
  override def obtenerPaquetesAsignados: List[Paquete] = super.obtenerPaquetesAsignados
}