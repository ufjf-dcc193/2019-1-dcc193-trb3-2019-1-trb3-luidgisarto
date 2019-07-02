package br.ufjf.dcc193.luidgisarto.trb3;

import java.util.Date;

import br.ufjf.dcc193.luidgisarto.trb3.repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.ufjf.dcc193.luidgisarto.trb3.models.Anotacao;
import br.ufjf.dcc193.luidgisarto.trb3.models.Etiqueta;
import br.ufjf.dcc193.luidgisarto.trb3.models.Usuario;
import br.ufjf.dcc193.luidgisarto.trb3.models.Item;
import br.ufjf.dcc193.luidgisarto.trb3.repository.AnotacaoRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.EtiquetaRepository;
import br.ufjf.dcc193.luidgisarto.trb3.repository.UsuarioRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

		UsuarioRepository usuarioRepository = ctx.getBean(UsuarioRepository.class);

		EtiquetaRepository etiquetaRepository = ctx.getBean(EtiquetaRepository.class);

		AnotacaoRepository anotacaoRepository = ctx.getBean(AnotacaoRepository.class);

		ItemRepository itemRepository = ctx.getBean(ItemRepository.class);

		for (int i = 1; i <= 5; i++) {
			usuarioRepository.save(new Usuario("Usuário " + i, "teste", "Usuário " + i, "usuario" + i + "@gmail.com"));
			etiquetaRepository.save(new Etiqueta("Etiqueta " + i, "Etiqueta " + i, "www.etiqueta" + i + ".com.br"));
			anotacaoRepository.save(new Anotacao("Anotação" + i, "Anotação " + i, "www.anotacao" + i + ".com.br",
					new Date(), new Date(), usuarioRepository.findAll().get(0)));

			Etiqueta etiqueta = etiquetaRepository.findAll().get(i-1);
			Anotacao anotacao = anotacaoRepository.findAll().get(i-1);

			itemRepository.save(new Item("Item " + i, etiqueta,anotacao));
		}
	}

}
