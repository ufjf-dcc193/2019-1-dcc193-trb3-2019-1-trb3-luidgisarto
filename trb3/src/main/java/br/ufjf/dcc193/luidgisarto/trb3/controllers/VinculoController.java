package br.ufjf.dcc193.luidgisarto.trb3.controllers;

import br.ufjf.dcc193.luidgisarto.trb3.models.Anotacao;
import br.ufjf.dcc193.luidgisarto.trb3.models.Etiqueta;
import br.ufjf.dcc193.luidgisarto.trb3.models.Item;
import br.ufjf.dcc193.luidgisarto.trb3.models.Vinculo;
import br.ufjf.dcc193.luidgisarto.trb3.repository.AnotacaoRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.EtiquetaRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.ItemRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.VinculoRepository;
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
@RequestMapping("vinculos")
public class VinculoController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    AnotacaoRepository anotacaoRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

    @Autowired
    VinculoRepository vinculoRepository;

    @RequestMapping({ "/" })
    public ModelAndView listar() {

        List<Vinculo> vinculos = vinculoRepository.findAll();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("vinculo/index");

        mv.addObject("vinculos", vinculos);

        return mv;
    }

    @RequestMapping("/novo-vinculo/{id}")
    public ModelAndView novo(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();

        Item item = itemRepository.findById(id).orElse(null);

        List<Item> itens = itemRepository.findAll();
        List<Anotacao> anotacoes = anotacaoRepository.findAll();
        List<Etiqueta> etiquetas = etiquetaRepository.findAll();

        Vinculo vinculo = new Vinculo();

        mv.setViewName("vinculo/form");

        mv.addObject("vinculo", vinculo);
        mv.addObject("item", item);
        mv.addObject("opcaoItens", itens);
        mv.addObject("opcoesAnotacao", anotacoes);
        mv.addObject("opcoesEtiquetas", etiquetas);

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();

        List<Item> itens = itemRepository.findAll();
        List<Anotacao> anotacoes = anotacaoRepository.findAll();
        List<Etiqueta> etiquetas = etiquetaRepository.findAll();

        Vinculo vinculo = vinculoRepository.findById(id).orElse(null);

        mv.setViewName("vinculo/form");

        mv.addObject("vinculo", vinculo);
        mv.addObject("opcaoItens", itens);
        mv.addObject("opcoesAnotacao", anotacoes);
        mv.addObject("opcoesEtiquetas", etiquetas);

        return mv;
    }

    @GetMapping("/excluir/{id}")
    public RedirectView excluir(@PathVariable Integer id) {
        Vinculo vinculo = vinculoRepository.findById(id).orElse(null);
        vinculoRepository.delete(vinculo);
        return new RedirectView("/itens/");
    }

    @PostMapping("salvar")
    public ModelAndView salvar(@Valid Vinculo vinculo, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()) {
            mv.setViewName("vinculo/form");
            mv.addObject("vinculo", vinculo);
            return mv;
        }
        vinculo.adicionarItem(vinculo.getItemOrigem(), vinculo.getItemDestino());
        vinculoRepository.save(vinculo);
        mv.setViewName("redirect:/itens/editar/" + vinculo.getItemOrigem().getId());
        return mv;
    }
}
