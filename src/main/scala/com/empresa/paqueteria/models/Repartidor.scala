package com.empresa.paqueteria.models

abstract class Repartidor(codigosPostalesPermitidos: Set[String]) {
  println(s"Constructor de Repartidor - tamaño de codigosPostalesPermitidos: ${codigosPostalesPermitidos.size}")
  require(codigosPostalesPermitidos.size <= 4, "No se pueden asignar más de 4 códigos postales a un repartidor")
  println("Pasó la validación de require")

  private var paquetesAsignados: List[Paquete] = List()

  def asignarPaquete(paquete: Paquete): Unit = {
    if (codigosPostalesPermitidos.contains(paquete.codigoPostal)) {
      paquetesAsignados = paquete :: paquetesAsignados
      println(s"Paquete ${paquete.id} asignado")
    } else {
      println(s"Error: Código postal ${paquete.codigoPostal} no permitido para este repartidor")
    }
  }

  def entregarPaquete(paquete: Paquete): Unit

  def obtenerPaquetesAsignados: List[Paquete] = paquetesAsignados
}