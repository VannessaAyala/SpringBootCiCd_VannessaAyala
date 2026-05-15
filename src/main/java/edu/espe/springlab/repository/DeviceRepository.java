package edu.espe.springlab.repository;

import edu.espe.springlab.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    //Buscar dispositivo por serial
    Optional<Device> findBySerial(String serial);
    //Responder si existe dispositivo con ese serial
    boolean existsBySerial(String serial);
    //Contar dispositivos disponibles
    long countByAvailable(Boolean available);
}