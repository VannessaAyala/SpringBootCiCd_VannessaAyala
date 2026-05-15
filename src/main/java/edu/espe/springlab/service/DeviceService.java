package edu.espe.springlab.service;

import edu.espe.springlab.dto.DeviceRequestData;
import edu.espe.springlab.dto.DeviceResponse;

public interface DeviceService {

    //Crear un dispositivo a partir del DTO validado
    DeviceResponse create(DeviceRequestData request);

    //Cambiar estado del dispositivo
    DeviceResponse deactivate(Long id);

    //Obtener estadisticas del inventario
    DeviceResponse stats();
}