package br.ufjf.dcc193.luidgisarto.trb3.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.ufjf.dcc193.luidgisarto.trb3.models.Anotacao;
import br.ufjf.dcc193.luidgisarto.trb3.models.Usuario;
import br.ufjf.dcc193.luidgisarto.trb3.repository.AnotacaoRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.UsuarioRepository;

@Controller
@RequestMapping("anotacoes")
public class AnotacaoController {
    @Autowired
    AnotacaoRepository anotacaoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @RequestMapping({ "/" })
    public ModelAndView listar() {

        List<Anotacao> anotacoes = anotacaoRepository.findAll();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("anotacao/index");

        mv.addObject("anotacoes", anotacoes);

        return mv;
    }

    @RequestMapping("/nova-anotacao")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView();

        Anotacao anotacao = new Anotacao();

        anotacao.setDataInclusao(new Date());

        List<Usuario> usuarios = usuarioRepository.findAll();

        mv.setViewName("anotacao/form");

        mv.addObject("anotacao", anotacao);
        mv.addObject("opcaoUsuarios", usuarios);

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();

        Anotacao anotacao = anotacaoRepository.getOne(id);
        List<Usuario> usuarios = usuarioRepository.findAll();

        mv.setViewName("anotacao/form");

        mv.addObject("anotacao", anotacao);
        mv.addObject("opcaoUsuarios", usuarios);

        return mv;
    }

    @GetMapping("/excluir/{id}")
    public RedirectView excluir(@PathVariable Integer id) {
        Anotacao anotacao = anotacaoRepository.getOne(id);
        anotacaoRepository.delete(anotacao);
        return new RedirectView("/anotacoes/");
    }

    @PostMapping("salvar")
    public ModelAndView salvar(@Valid Anotacao anotacao, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("anotacao/form");
            mv.addObject("anotacao", anotacao);
            return mv;
        }
        anotacao.setDataAlteracao(new Date());
        anotacaoRepository.save(anotacao);
        mv.setViewName("redirect:/anotacoes/");
        return mv;
    }
}
