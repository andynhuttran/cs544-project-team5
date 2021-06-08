package edu.cs544.team5.service;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.dto.BarcodeRecordCreationDto;
import edu.cs544.team5.dto.BarcodeRecordReadDto;
import edu.cs544.team5.repository.BarcodeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Transactional
@Service
@RequiredArgsConstructor
public class BarcodeServiceImpl implements BarcodeService {
    private final BarcodeRepository barcodeRepository;
    private final ModelMapper mapper;

    @Override
    public BarcodeRecordReadDto create(@Valid BarcodeRecordCreationDto barcodeRecordCreationDto) {
        BarcodeRecord barcodeRecord = mapper.map(barcodeRecordCreationDto, BarcodeRecord.class);
        return mapper.map(barcodeRepository.save(barcodeRecord), BarcodeRecordReadDto.class);
    }

    @Override
    public Page<BarcodeRecord> fetchAll(Pageable request) {
        return barcodeRepository.findAll(request);
    }
}
