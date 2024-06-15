package com.example.BTTHWebsite2.repository;

import com.example.BTTHWebsite2.model.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, String> {

}
