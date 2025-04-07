package com.any.simulados.v1.admin.usuarios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(Usuario entrada) {

        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuario(@PathVariable("id") String id) {

        return null;
    }

    @GetMapping
    public ResponseEntity<Usuario> obterUsuarios() {

        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable("id") String id) {

        return null;
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizarUsuario(Usuario entrada) {
        return null;
    }
}
