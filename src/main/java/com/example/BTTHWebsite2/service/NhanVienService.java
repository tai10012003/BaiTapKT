package com.example.BTTHWebsite2.service;

import com.example.BTTHWebsite2.model.NhanVien;
import com.example.BTTHWebsite2.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;

    // Retrieve all employees with pagination
    public Page<NhanVien> getAllNhanViens(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return nhanVienRepository.findAll(pageable);
    }

    // Retrieve an employee by its id
    public Optional<NhanVien> getNhanVienById(String id) {
        return nhanVienRepository.findById(id);
    }

    // Add a new employee to the database
    public NhanVien addNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    // Update an existing employee
    public NhanVien updateNhanVien(@NotNull NhanVien nhanVien) {
        NhanVien existingNhanVien = nhanVienRepository.findById(nhanVien.getMaNV())
                .orElseThrow(() -> new IllegalStateException("NhanVien with ID " + nhanVien.getMaNV() + " does not exist."));
        existingNhanVien.setTenNV(nhanVien.getTenNV());
        existingNhanVien.setPhai(nhanVien.getPhai());
        existingNhanVien.setNoiSinh(nhanVien.getNoiSinh());
        existingNhanVien.setLuong(nhanVien.getLuong());
        existingNhanVien.setPhongBan(nhanVien.getPhongBan());
        return nhanVienRepository.save(existingNhanVien);
    }

    // Delete an employee by its id
    public void deleteNhanVienById(String id) {
        if (!nhanVienRepository.existsById(id)) {
            throw new IllegalStateException("NhanVien with ID " + id + " does not exist.");
        }
        nhanVienRepository.deleteById(id);
    }
}

