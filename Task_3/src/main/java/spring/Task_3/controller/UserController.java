package spring.Task_3.controller;




import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.Task_3.model.User;
import spring.Task_3.service.UserService;


@Controller
@RequestMapping ("/users")
public class UserController {

    private final UserService userServiceImpl; //заменил на файнал

    @Autowired
    public UserController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public String allUsers (Model model) {
        model.addAttribute("users", userServiceImpl.findAll());
        return "users/index";
    }

    @GetMapping("/{id}" )
    public String show (@PathVariable("id") int id, Model model){
        model.addAttribute("user", userServiceImpl.findOne(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";

        userServiceImpl.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", userServiceImpl.findOne(id));

        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {  //можно убрать часть кода
        if (bindingResult.hasErrors())
            return "users/edit";

        userServiceImpl.update(user);
        return "redirect:/users";
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return "redirect:/users";
    }
}
