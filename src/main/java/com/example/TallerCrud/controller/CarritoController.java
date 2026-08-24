package com.example.TallerCrud.controller;

import com.example.TallerCrud.entity.Carrito;
import com.example.TallerCrud.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path="/carrito")
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;

    @PostMapping(path="/add")
    public @ResponseBody String agregarProducto (@RequestParam String producto,
                                                 @RequestParam Integer cantidad,
                                                 @RequestParam BigDecimal precio) {
        Carrito c = new Carrito();
        c.setProducto(producto);
        c.setCantidad(cantidad);
        c.setPrecio(precio);
        carritoRepository.save(c);
        return "Producto agregado al carrito";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Carrito> obtenerCarrito() {
        return carritoRepository.findAll();
    }
}