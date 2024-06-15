package com.example.BTTHWebsite2.service;

import com.example.BTTHWebsite2.model.PhongBan;
import com.example.BTTHWebsite2.repository.PhongBanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongBanRepository;

    // Retrieve all departments from the database
    public List<PhongBan> getAllPhongBans() {
        return phongBanRepository.findAll();
    }

    // Retrieve a department by its id
    public Optional<PhongBan> getPhongBanById(String id) {
        return phongBanRepository.findById(id);
    }

    // Add a new department to the database
    public PhongBan addPhongBan(PhongBan phongBan) {
        return phongBanRepository.save(phongBan);
    }

    // Update an existing department
    public PhongBan updatePhongBan(@NotNull PhongBan phongBan) {
        PhongBan existingPhongBan = phongBanRepository.findById(phongBan.getMaPhong()).orElseThrow(() -> new IllegalStateException("PhongBan with ID " + phongBan.getMaPhong() + " does not exist."));
        existingPhongBan.setTenPhong(phongBan.getTenPhong());
        return phongBanRepository.save(existingPhongBan);
    }

    // Delete a department by its id
    public void deletePhongBanById(String id) {
        if (!phongBanRepository.existsById(id)) {
            throw new IllegalStateException("PhongBan with ID " + id + " does not exist.");
        }
        phongBanRepository.deleteById(id);
    }
}