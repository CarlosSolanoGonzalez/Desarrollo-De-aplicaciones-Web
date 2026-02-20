package com.tienda.controller;

import com.tienda.service.ProductoService;
import com.tienda.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public IndexController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    public String listado(Model model) {

        var productos = productoService.getProductos(true); // false = trae todos con categoria (join fetch)
        model.addAttribute("productos", productos);

        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);

        return "/index";
    }

    @GetMapping("/consultas/{idCategoria}")
    public String consultas(@PathVariable Integer idCategoria, Model model) {
        var categoriaOpt = categoriaService.getCategoria(idCategoria);

        if (categoriaOpt.isEmpty()) {
            model.addAttribute("productos", java.util.Collections.EMPTY_LIST);

        } else {
            model.addAttribute("productos", categoriaOpt.get().getProductos());
        }
        
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        
        model.addAttribute("idCategoriaSeleccionada", idCategoria);

        return "/index";
    }

}
