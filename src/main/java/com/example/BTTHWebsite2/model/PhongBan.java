package com.example.BTTHWebsite2.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phongban")
public class PhongBan {

    @Id
    @Column(name = "Ma_Phong", length = 2, nullable = false)
    private String maPhong;

    @Column(name = "Ten_Phong", length = 30, nullable = false)
    private String tenPhong;

    @OneToMany(mappedBy = "phongBan")
    private List<NhanVien> nhanViens;
}