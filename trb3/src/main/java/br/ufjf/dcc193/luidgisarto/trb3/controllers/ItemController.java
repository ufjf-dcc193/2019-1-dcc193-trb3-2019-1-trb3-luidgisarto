package br.ufjf.dcc193.luidgisarto.trb3.controllers;

import br.ufjf.dcc193.luidgisarto.trb3.models.Anotacao;
import br.ufjf.dcc193.luidgisarto.trb3.models.Etiqueta;
import br.ufjf.dcc193.luidgisarto.trb3.models.Item;
import br.ufjf.dcc193.luidgisarto.trb3.repository.AnotacaoRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.EtiquetaRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.ItemRepository;
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
@RequestMapping("itens")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    AnotacaoRepository anotacaoRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

    @RequestMapping({ "/" })
    public ModelAndView listar() {

        List<Item> itens = itemRepository.findAll();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("item/index");

        mv.addObject("itens", itens);

        return mv;
    }

    @RequestMapping("/novo-item")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView();

        Item item = new Item();

        List<Anotacao> anotacoes = anotacaoRepository.findAll();

        List<Etiqueta> etiquetas = etiquetaRepository.findAll();

        mv.setViewName("item/form");

        mv.addObject("item", item);
        mv.addObject("opcoesAnotacao", anotacoes);
        mv.addObject("opcoesEtiquetas", etiquetas);

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();

        Item item = itemRepository.getOne(id);

        mv.setViewName("item/form");

        mv.addObject("item", item);

        return mv;
    }

    @GetMapping("/excluir/{id}")
    public RedirectView excluir(@PathVariable Integer id) {
        Item item = itemRepository.getOne(id);
        itemRepository.delete(item);
        return new RedirectView("/itens/");
    }

    @PostMapping("salvar")
    public ModelAndView salvar(@Valid Item item, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()) {
            mv.setViewName("item/form");
            mv.addObject("item", item);
            return mv;
        }
        itemRepository.save(item);
        mv.setViewName("redirect:/itens/");
        return mv;
    }
}
