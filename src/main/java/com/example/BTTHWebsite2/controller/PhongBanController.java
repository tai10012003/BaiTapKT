package com.example.BTTHWebsite2.controller;

import com.example.BTTHWebsite2.model.PhongBan;
import com.example.BTTHWebsite2.service.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/phongbans")
public class PhongBanController {

    @Autowired
    private PhongBanService phongBanService;

    // Display a list of all departments
    @GetMapping
    public String showPhongBanList(Model model) {
        List<PhongBan> phongBans = phongBanService.getAllPhongBans();
        model.addAttribute("phongBans", phongBans);
        return "phongbans/phongban-list";
    }

    // For adding a new department
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("phongBan", new PhongBan());
        return "phongbans/add-phongban";
    }

    @PostMapping("/add")
    public String addPhongBan(@Valid PhongBan phongBan, BindingResult result) {
        if (result.hasErrors()) {
            return "phongbans/add-phongban";
        }
        phongBanService.addPhongBan(phongBan);
        return "redirect:/phongbans";
    }

    // For editing a department
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        PhongBan phongBan = phongBanService.getPhongBanById(id).orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
        model.addAttribute("phongBan", phongBan);
        return "phongbans/update-phongban";
    }

    @PostMapping("/update/{id}")
    public String updatePhongBan(@PathVariable String id, @Valid PhongBan phongBan, BindingResult result) {
        if (result.hasErrors()) {
            return "phongbans/update-phongban";
        }
        phongBan.setMaPhong(id);
        phongBanService.updatePhongBan(phongBan);
        return "redirect:/phongbans";
    }

    // Handle request to delete a department
    @GetMapping("/delete/{id}")
    public String deletePhongBan(@PathVariable String id) {
        phongBanService.deletePhongBanById(id);
        return "redirect:/phongbans";
    }
}