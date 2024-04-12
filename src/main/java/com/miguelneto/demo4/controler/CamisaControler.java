package com.miguelneto.demo4.controler;

import com.miguelneto.demo4.entidades.Camisa;
import com.miguelneto.demo4.repositorio.CamisaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/Camisa")
public class CamisaControler {
    @Autowired
    private CamisaRepositorio repositorio;
    @GetMapping
    public List<Camisa> ListarTodasCamisas() {
        return repositorio.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Camisa>  getCamisa(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(repositorio.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public Camisa adicionarCamisa(@RequestBody Camisa novo) {
        return repositorio.save(novo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamisa(@PathVariable Long id) {
        try {
            repositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Camisa> atualizarCamisa(@PathVariable Long id,@RequestBody Camisa camisaNovaDados) {
        try {
            Camisa camisaAntiga = repositorio.findById(id).get();
            camisaAntiga.setTipo(camisaNovaDados.getTipo());
            camisaAntiga.setMarca(camisaNovaDados.getMarca());
            camisaAntiga.setCor(camisaNovaDados.getCor());
            camisaAntiga.setTamanho(camisaNovaDados.getTamanho());
            camisaAntiga.setPreco(camisaNovaDados.getPreco());


            return new ResponseEntity<>(repositorio.save(camisaAntiga),HttpStatus.OK);

        } catch  (NoSuchElementException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

            }
}
