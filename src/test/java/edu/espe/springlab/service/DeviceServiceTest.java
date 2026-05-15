package edu.espe.springlab.service;

import edu.espe.springlab.domain.Device;
import edu.espe.springlab.dto.DeviceRequestData;
import edu.espe.springlab.repository.DeviceRepository;
import edu.espe.springlab.service.impl.DeviceServiceImpl;
import edu.espe.springlab.web.advice.ConflictException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(DeviceServiceImpl.class)
public class DeviceServiceTest {

    @Autowired
    private DeviceServiceImpl service;

    @Autowired
    private DeviceRepository repository;

    @Test
    void shouldNotAllowDuplicateSerial() {

        // Crear y guardar un dispositivo existente
        Device existing = new Device();
        existing.setName("Laptop");
        existing.setSerial("ABC-001");
        existing.setCategory("Computadoras");
        existing.setStock(10);
        existing.setAvailable(true);

        repository.save(existing);

        // Crear la solicitud con el mismo serial
        DeviceRequestData req = new DeviceRequestData();
        req.setName("Otra Laptop");
        req.setSerial("ABC-001");
        req.setCategory("Computadoras");
        req.setStock(5);

        // Verificar que el servicio lanza excepción por serial duplicado
        assertThatThrownBy(() -> service.create(req)).isInstanceOf(ConflictException.class);
    }
}