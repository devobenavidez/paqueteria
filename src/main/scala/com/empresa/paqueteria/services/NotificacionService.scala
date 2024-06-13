package com.empresa.paqueteria.services

import com.empresa.paqueteria.models.Paquete

// El objeto NotificacionService proporciona métodos para enviar notificaciones
object NotificacionService {
  // Función para enviar una notificación de que un paquete ha sido entregado
  def enviarNotificacion(paquete: Paquete): Unit = {
    // Imprime un mensaje de notificación en la consola
    println(s"Notificación: El paquete con ID ${paquete.id} ha sido entregado.")
    // Implementación de envío de notificación a través de móvil
    // (Aquí iría el código para enviar la notificación real al móvil del cliente)
  }
}
