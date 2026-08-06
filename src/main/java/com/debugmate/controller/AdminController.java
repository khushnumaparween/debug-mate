package com.debugmate.controller;

import com.debugmate.dto.ErrorResponseDTO;
import com.debugmate.entity.ErrorDetail;
import com.debugmate.entity.ErrorEntity;
import com.debugmate.repository.ErrorRepository;
import com.debugmate.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ErrorRepository repository;
    private final ErrorService service;

    // ---------------- ADD PAGE ----------------
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("error", new ErrorResponseDTO());
        return "admin-add";
    }

    // ---------------- SAVE / UPDATE ----------------
    @PostMapping("/save")
    public String save(@ModelAttribute ErrorResponseDTO dto,
                       RedirectAttributes redirectAttributes) {

        service.saveOrUpdate(dto);
        redirectAttributes.addFlashAttribute("message", "Saved successfully");

        return "redirect:/admin/list";
    }

    // ---------------- LIST ----------------
    @GetMapping("/list")
    public String listErrors(Model model) {

        model.addAttribute("errors", service.getAllErrors());
        return "admin-list";
    }

    // ---------------- DELETE ----------------
    @GetMapping("/delete/{id}")
    public String deleteError(@PathVariable Long id) {

        repository.deleteById(id);
        return "redirect:/admin/list";
    }

    // ---------------- EDIT PAGE ----------------
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {

        ErrorEntity error = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error not found"));

        ErrorDetail detail = error.getDetail();

        if (detail == null) {
            detail = new ErrorDetail();
        }

        model.addAttribute("error", error);
        model.addAttribute("detail", detail);

        return "admin-edit";
    }
}