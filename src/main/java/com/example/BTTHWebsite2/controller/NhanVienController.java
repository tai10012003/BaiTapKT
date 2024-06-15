package com.example.BTTHWebsite2.controller;

import com.example.BTTHWebsite2.model.NhanVien;
import com.example.BTTHWebsite2.service.NhanVienService;
import com.example.BTTHWebsite2.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private PhongBanService phongBanService;

    @GetMapping("/nhanviens")
    public String showNhanVienList(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   Model model) {
        Page<NhanVien> nhanVienPage = nhanVienService.getAllNhanViens(page, size);
        model.addAttribute("nhanViens", nhanVienPage.getContent());
        model.addAttribute("nhanVienPage", nhanVienPage);
        return "nhanviens/nhanvien-list";
    }

        // For adding a new employee
    @GetMapping("/nhanviens/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("phongBans", phongBanService.getAllPhongBans());
        return "nhanviens/add-nhanvien";
    }

    @PostMapping("/nhanviens/add")
    public String addNhanVien(@Valid NhanVien nhanVien, BindingResult result) {
        if (result.hasErrors()) {
            return "nhanviens/add-nhanvien";
        }
        nhanVienService.addNhanVien(nhanVien);
        return "redirect:/nhanviens";
    }

    // For editing an employee
    @GetMapping("/nhanviens/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        NhanVien nhanVien = nhanVienService.getNhanVienById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        model.addAttribute("nhanvien", nhanVien);
        model.addAttribute("phongBans", phongBanService.getAllPhongBans());
        return "nhanviens/update-nhanvien";
    }

    @PostMapping("/nhanviens/update/{id}")
    public String updateNhanVien(@PathVariable String id, @Valid NhanVien nhanVien, BindingResult result) {
        if (result.hasErrors()) {
            return "nhanviens/update-nhanvien";
        }
        nhanVien.setMaNV(id);
        nhanVienService.updateNhanVien(nhanVien);
        return "redirect:/nhanviens";
    }

    // Handle request to delete an employee
    @GetMapping("/nhanviens/delete/{id}")
    public String deleteNhanVien(@PathVariable String id) {
        nhanVienService.deleteNhanVienById(id);
        return "redirect:/nhanviens";
    }
}

