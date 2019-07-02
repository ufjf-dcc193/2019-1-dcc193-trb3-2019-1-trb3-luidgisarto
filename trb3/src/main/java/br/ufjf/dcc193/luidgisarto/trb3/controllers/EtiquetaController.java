package br.ufjf.dcc193.luidgisarto.trb3.controllers;

import br.ufjf.dcc193.luidgisarto.trb3.models.Etiqueta;
import br.ufjf.dcc193.luidgisarto.trb3.repository.EtiquetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("etiquetas")
public class EtiquetaController {
    @Autowired
    EtiquetaRepository etiquetaRepository;

    @RequestMapping({ "/" })
    public ModelAndView listar() {

        List<Etiqueta> etiquetas = etiquetaRepository.findAll();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("etiqueta/index");

        mv.addObject("etiquetas", etiquetas);

        return mv;
    }

    @RequestMapping("/nova-etiqueta")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView();

        Etiqueta etiqueta = new Etiqueta();

        mv.setViewName("etiqueta/form");

        mv.addObject("etiqueta", etiqueta);

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();

        Etiqueta etiqueta = etiquetaRepository.getOne(id);

        mv.setViewName("etiqueta/form");

        mv.addObject("etiqueta", etiqueta);

        return mv;
    }

    @GetMapping("/excluir/{id}")
    public RedirectView excluir(@PathVariable Integer id) {
        Etiqueta etiqueta = etiquetaRepository.getOne(id);
        etiquetaRepository.delete(etiqueta);
        return new RedirectView("/etiquetas/");
    }

    @PostMapping("salvar")
    public ModelAndView salvar(@Valid Etiqueta etiqueta, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()) {
            mv.setViewName("etiqueta/form");
            mv.addObject("etiqueta", etiqueta);
            return mv;
        }
        etiquetaRepository.save(etiqueta);
        mv.setViewName("redirect:/etiquetas/");
        return mv;
    }
}
