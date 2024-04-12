package com.miguelneto.demo4.controler;

import com.miguelneto.demo4.entidades.Calca;
import com.miguelneto.demo4.entidades.Camisa;
import com.miguelneto.demo4.repositorio.CalcaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/Calca")
public class CalcaControler {
    @Autowired
    private CalcaRepositorio repositorio;
    @GetMapping
    public List<Calca> ListarTodasCalcas() {
        return repositorio.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Calca>  getCamisa(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(repositorio.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public Calca adicionarCalca(@RequestBody Calca novo) {
        return repositorio.save(novo);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalca(@PathVariable Long id) {
        try {
            repositorio.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
@PutMapping("/{id}")
    public ResponseEntity<Calca> alterarCalcaPorId(@PathVariable Long id, @RequestBody Calca calcaNovaDados) {
        try {
            Calca calcaAntiga = repositorio.findById(id).get();
            calcaAntiga.setNome(calcaNovaDados.getNome());
            calcaAntiga.setCor(calcaNovaDados.getCor());
            calcaAntiga.setTamanho(calcaNovaDados.getTamanho());
            calcaAntiga.setPreco(calcaNovaDados.getPreco());


            return new ResponseEntity<>(repositorio.save(calcaAntiga),HttpStatus.OK);

        } catch  (NoSuchElementException exception){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
