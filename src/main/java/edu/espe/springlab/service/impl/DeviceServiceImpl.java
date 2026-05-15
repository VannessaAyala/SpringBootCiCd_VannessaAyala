package edu.espe.springlab.service.impl;

import edu.espe.springlab.domain.Device;
import edu.espe.springlab.dto.DeviceRequestData;
import edu.espe.springlab.dto.DeviceResponse;
import edu.espe.springlab.repository.DeviceRepository;
import edu.espe.springlab.service.DeviceService;
import edu.espe.springlab.web.advice.ConflictException;
import edu.espe.springlab.web.advice.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository repo;

    public DeviceServiceImpl(DeviceRepository repo) {this.repo = repo;}

    @Override
    public DeviceResponse create(DeviceRequestData request) {

        if(repo.existsBySerial(request.getSerial())) {
            throw new ConflictException("El serial ya esta registrado");
        }

        if(request.getStock() < 0) {
            throw new RuntimeException("Stock invalido");
        }

        Device device = new Device();
        device.setName(request.getName());
        device.setSerial(request.getSerial());
        device.setCategory(request.getCategory());
        device.setStock(request.getStock());
        device.setAvailable(true);

        Device saved = repo.save(device);
        return toResponse(saved);
    }

    @Override
    public DeviceResponse deactivate(Long id) {

        Device device = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo no encontrado"));

        device.setAvailable(false);

        return toResponse(repo.save(device));
    }

    @Override
    public DeviceResponse stats() {

        DeviceResponse r = new DeviceResponse();
        r.setStock((int) repo.count());

        return r;
    }

    private DeviceResponse toResponse(Device device){

        DeviceResponse r = new DeviceResponse();

        r.setId(device.getId());
        r.setName(device.getName());
        r.setSerial(device.getSerial());
        r.setCategory(device.getCategory());
        r.setStock(device.getStock());
        r.setAvailable(device.getAvailable());

        return r;
    }
}