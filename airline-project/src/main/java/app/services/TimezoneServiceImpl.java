package app.services;

import app.dto.TimezoneDTO;
import app.entities.Timezone;
import app.repositories.TimezoneRepository;
import app.services.interfaces.TimezoneService;
import app.mappers.TimezoneMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimezoneServiceImpl implements TimezoneService {

    private final TimezoneRepository timezoneRepository;
    private final TimezoneMapper timezoneMapper = Mappers.getMapper(TimezoneMapper.class);

    @Transactional
    @Override
    public Timezone saveTimezone(TimezoneDTO timezoneDTO) {
        var timezone = timezoneMapper.convertToTimezone(timezoneDTO);
        return timezoneRepository.save(timezone);
    }

    @Override
    @Transactional
    public Timezone updateTimezone(TimezoneDTO timezoneDTO) {
        var timezone = timezoneMapper.convertToTimezone(timezoneDTO);
        return timezoneRepository.save(timezone);
    }

    @Override
    public Page<Timezone> getAllPagesTimezones(int page, int size) {
        return timezoneRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Timezone> getTimezoneById(Long id) {
        return timezoneRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteTimezoneById(Long id) {
        timezoneRepository.deleteById(id);
    }
}