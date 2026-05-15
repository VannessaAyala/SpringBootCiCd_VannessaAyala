package edu.espe.springlab.repository;

import edu.espe.springlab.domain.Device;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository repository;

    @Test
    void shouldSaveAndFindDeviceBySerial() {
        // Crear el dispositivo correctamente
        Device d = new Device();
        d.setName("Laptop");
        d.setSerial("ABC-001");
        d.setCategory("Computadoras");
        d.setStock(10);
        d.setAvailable(true);

        // Guardar en la base de datos en memoria (H2)
        repository.save(d);

        // Buscar por serial
        var result = repository.findBySerial("ABC-001");

        // Verificar que el resultado existe y coincide
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Laptop");

    }
}