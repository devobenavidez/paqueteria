package com.empresa.paqueteria.models

abstract class Repartidor(codigosPostalesPermitidos: Set[String]) {
  println(s"Constructor de Repartidor - tamaño de codigosPostalesPermitidos: ${codigosPostalesPermitidos.size}")
  require(codigosPostalesPermitidos.size <= 4, "No se pueden asignar más de 4 códigos postales a un repartidor")
  println("Pasó la validación de require")

  protected val pesoMaximoPermitido: Double

  private var paquetesAsignados: List[Paquete] = List()

  def asignarPaquete(paquete: Paquete): Unit = {
    val asignado = asignarPaqueteAux(paquete)
    if (asignado) {
      println(s"Paquete ${paquete.id} asignado a la ruta de ${this.getClass.getSimpleName}")
    } else {
      println(s"Error: El paquete ${paquete.id} no pudo ser asignado.")
    }
  }

  protected def asignarPaqueteAux(paquete: Paquete): Boolean = {
    println(s"Intentando asignar paquete ${paquete.id} con peso ${paquete.peso}")
    if (codigosPostalesPermitidos.contains(paquete.codigoPostal)) {
      val pesoTotal = paquetesAsignados.map(_.peso).sum + paquete.peso
      println(s"Peso total con el nuevo paquete: $pesoTotal")
      if (pesoTotal <= pesoMaximoPermitido) {
        paquetesAsignados = paquete :: paquetesAsignados
        return true
      } else {
        println(s"Error: El paquete ${paquete.id} excede el peso máximo permitido para este repartidor")
      }
    } else {
      println(s"Error: Código postal ${paquete.codigoPostal} no permitido para este repartidor")
    }
    false
  }

  def entregarPaquete(paquete: Paquete): Unit

  def obtenerPaquetesAsignados: List[Paquete] = paquetesAsignados
}