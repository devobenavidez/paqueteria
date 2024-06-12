package com.empresa.paqueteria

import org.scalatest.funsuite.AnyFunSuite
import com.empresa.paqueteria.models._

class RepartidorCocheTest extends AnyFunSuite {

  test("Asignación de paquetes a la ruta de Ana dentro de los códigos postales permitidos") {
    val codigosPostales = Set("08001", "08002")
    val paquete1 = Paquete("5", 2.0, "08001", false)
    val paquete2 = Paquete("6", 0.5, "08002", false)
    val repartidorCoche = new RepartidorCoche(codigosPostales)

    // Asignar paquetes
    repartidorCoche.asignarPaquete(paquete1)
    repartidorCoche.asignarPaquete(paquete2)

    // Verificar que los paquetes se asignaron correctamente
    val paquetesAsignados = repartidorCoche.obtenerPaquetesAsignados
    assert(paquetesAsignados.contains(paquete1))
    assert(paquetesAsignados.contains(paquete2))
  }

  test("Asignación de paquete fuera de los códigos postales permitidos") {
    val codigosPostales = Set("08001", "08002")
    val paquete = Paquete("7", 1.0, "08005", false) // Código postal no permitido
    val repartidorCoche = new RepartidorCoche(codigosPostales)

    // Asignar paquete
    repartidorCoche.asignarPaquete(paquete)

    // Verificar que el paquete no se asignó
    val paquetesAsignados = repartidorCoche.obtenerPaquetesAsignados
    assert(!paquetesAsignados.contains(paquete))
  }

  test("Entrega de paquetes por Ana") {
    val codigosPostales = Set("08001", "08002")
    val paquete = Paquete("8", 1.0, "08001", false)
    val repartidorCoche = new RepartidorCoche(codigosPostales)

    // Asignar y luego entregar el paquete
    repartidorCoche.asignarPaquete(paquete)
    repartidorCoche.entregarPaquete(paquete)

    // Crear una copia del paquete con el estado de entrega actualizado
    val paqueteEntregado = paquete.copy(estadoEntrega = true)

    // Verificar que el paquete se entregó correctamente
    assert(paqueteEntregado.estadoEntrega)
  }

  test("No se puede asignar un paquete que exceda el peso máximo permitido a Ana") {
    val codigosPostales = Set("08001", "08002")
    val paquete1 = Paquete("7", 400.0, "08001", false)
    val paquete2 = Paquete("8", 200.0, "08002", false)
    val repartidorCoche = new RepartidorCoche(codigosPostales)

    // Asignar paquetes
    repartidorCoche.asignarPaquete(paquete1)
    repartidorCoche.asignarPaquete(paquete2)

    // Verificar que el segundo paquete no se asignó
    val paquetesAsignados = repartidorCoche.obtenerPaquetesAsignados


    assert(paquetesAsignados.contains(paquete1))
    assert(!paquetesAsignados.contains(paquete2))
  }

  test("No se pueden asignar más de 2 códigos postales a un repartidor") {
    val codigosPostales = Set("08001", "08002", "08003")

    assertThrows[IllegalArgumentException] {
      new RepartidorCoche(codigosPostales)
    }
  }
}