package net.kim9xd.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.kim9xd.domain.User;
import net.kim9xd.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserCotroller {
	private List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/form")
	public String form(Model model) {
		return "/user/form";
	}
	
	@PostMapping("")
	public String create(User user) {
		System.out.println("user : " + user);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/{id}/form")
	public String updatedForm(@PathVariable Long id, Model model) {
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "/user/updatedForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser) {
		
		User user = userRepository.findById(id).get();
		user.update(newUser);
		
		userRepository.save(user);
		
		
		return "redirect:/users";
	}
}
