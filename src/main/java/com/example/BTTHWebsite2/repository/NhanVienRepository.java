package com.example.BTTHWebsite2.repository;

import com.example.BTTHWebsite2.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {

}

