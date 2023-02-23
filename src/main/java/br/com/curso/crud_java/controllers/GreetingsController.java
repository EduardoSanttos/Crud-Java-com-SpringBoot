package br.com.curso.crud_java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.crud_java.model.Usuario;
import br.com.curso.crud_java.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

	@Autowired // Injeção de dependencia
	private UsuarioRepository usuarioRepository;

	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {

		return "Hello " + name + "!";
	}

	@GetMapping(value = "listarTodos")
	@ResponseBody // Retorna os dados para o corpo da resposta
	public ResponseEntity<List<Usuario>> listUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);// retorna um json
	}

	@PostMapping(value = "salvar") // Mapeia a URL
	@ResponseBody // Descrição da resposta
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { // Recebi os dados para salvar

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> deletar(@RequestParam Long idUser) {

		usuarioRepository.deleteById(idUser);

		return new ResponseEntity<String>("Usuario deletado com sucesso!", HttpStatus.OK);
	}
	
	@PutMapping(value = "atualizar") // Mapeia a URL
	@ResponseBody // Descrição da resposta
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { // Recebi os dados para salvar

		if(usuario.getId() == null) {
			
			return new ResponseEntity<String>("Id não informado!", HttpStatus.OK);
		}
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);

	}
	
	@GetMapping(value = "buscarUsuarioId") 
	@ResponseBody
	public ResponseEntity<Usuario> buscarUsuarioId(@RequestParam(name = "idUser") Long idUser) {

		Usuario user = usuarioRepository.findById(idUser).get();

		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorNome") 
	@ResponseBody
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "name") String name) {

		List<Usuario> user = usuarioRepository.buscarPorNome(name.trim().toUpperCase());

		return new ResponseEntity<List<Usuario>>(user, HttpStatus.OK);
	}
}







