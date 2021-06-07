package edu.cs544.team5.services;

import edu.cs544.team5.domain.BarcodeRecord;
import edu.cs544.team5.repository.BarcodeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Transactional
@Service
public class BarcodeServiceImpl implements BarcodeService {
    private final BarcodeRepository barcodeRepository;

    public BarcodeServiceImpl(BarcodeRepository barcodeRepository) {
        this.barcodeRepository = barcodeRepository;
    }

    @Override
    public BarcodeRecord create(@Valid BarcodeRecord barcodeRecord) {
        return barcodeRepository.save(barcodeRecord);
    }
}
