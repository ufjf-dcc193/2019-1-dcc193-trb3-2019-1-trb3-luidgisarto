package br.ufjf.dcc193.luidgisarto.trb3.controllers;

import br.ufjf.dcc193.luidgisarto.trb3.models.Anotacao;
import br.ufjf.dcc193.luidgisarto.trb3.models.Etiqueta;
import br.ufjf.dcc193.luidgisarto.trb3.models.Item;
import br.ufjf.dcc193.luidgisarto.trb3.models.Usuario;
import br.ufjf.dcc193.luidgisarto.trb3.repository.AnotacaoRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.EtiquetaRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.ItemRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.UsuarioRepository;
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
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @RequestMapping({ "/" })
    public ModelAndView listar() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/index");

        mv.addObject("usuarios", usuarios);

        return mv;
    }

    @RequestMapping("/novo-usuario")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView();

        Usuario usuario = new Usuario();

        mv.setViewName("usuario/form");

        mv.addObject("usuario", usuario);

        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        ModelAndView mv = new ModelAndView();

        Usuario usuario = usuarioRepository.getOne(id);

        mv.setViewName("usuario/form");

        mv.addObject("usuario", usuario);

        return mv;
    }

    @GetMapping("/excluir/{id}")
    public RedirectView excluir(@PathVariable Integer id) {
        Usuario usuario = usuarioRepository.getOne(id);
        usuarioRepository.delete(usuario);
        return new RedirectView("/usuarios/");
    }

    @PostMapping("salvar")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()) {
            mv.setViewName("usuario/form");
            mv.addObject("usuario", usuario);
            return mv;
        }
        usuarioRepository.save(usuario);
        mv.setViewName("redirect:/usuarios/");
        return mv;
    }
}
