package com.tienda.controller;

import com.tienda.domain.Rol;
import com.tienda.service.RolService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/role")
public class RolController {

    private final RolService rolService;
    private final MessageSource messageSource;

    public RolController(RolService rolService, MessageSource messageSource) {
        this.rolService = rolService;
        this.messageSource = messageSource;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = rolService.getRoles();
        model.addAttribute("roles", lista);
        model.addAttribute("totalRoles", lista.size());
        return "/role/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Rol rol, RedirectAttributes redirectAttributes) {
        rolService.save(rol);
        redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));
        return "redirect:/role/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idRol, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            rolService.delete(idRol);
        } catch (IllegalArgumentException e) {
            titulo = "error";
            detalle = "role.error01";
        } catch (IllegalStateException e) {
            titulo = "error";
            detalle = "role.error02";
        } catch (Exception e) {
            titulo = "error";
            detalle = "role.error03";
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/role/listado";
    }

    @GetMapping("/modificar/{idRol}")
    public String modificar(@PathVariable("idRol") Integer idRol, Model model, RedirectAttributes redirectAttributes) {
        try {
            Rol rol = rolService.getRol(idRol);
            model.addAttribute("rol", rol);
            return "/role/modifica";
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("role.error01", null, Locale.getDefault()));
            return "redirect:/role/listado";
        }
    }
}
