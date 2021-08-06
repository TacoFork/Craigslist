package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    public static long idCounter;
    ArrayList<Listing> listingsList = new ArrayList<>();

    @RequestMapping("/")
    public String homePage(Model model){
        model.addAttribute("listings", listingsList);
        return "index";
    }

    @GetMapping("/addListing")
    public String addListingPage(Principal principal, Model model){
        Listing listing = new Listing();
        idSetter(listing);
        model.addAttribute("listing", listing);
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        model.addAttribute("action", "/listingAdded");
        return "addlisting";
    }

    @PostMapping("/listingAdded")
    public String listingAdded(@Valid Listing listing, BindingResult result, Model model, Principal principal){
        if(result.hasErrors()){
            String username = principal.getName();
            model.addAttribute("user", userRepository.findByUsername(username));
            model.addAttribute("action", "/listingAdded");
            return "addlisting";
        }
        else {
            listingsList.add(listing);
            return "redirect:/viewListings";
        }
    }

    @RequestMapping("/viewListings")
    public String viewUserListing(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        model.addAttribute("listingsList", listingsList);
        return "/viewlistings";
    }

    @GetMapping("/updateListing/{userId}/{id}")
    public String updateListing(@PathVariable long userId, @PathVariable long id, Model model){
        model.addAttribute("action", "/listingUpdated");
        for (Listing listing : listingsList){
            if (listing.getId() == id || listing.getUserId() == userId){
                model.addAttribute("listing", listing);
                break;
            }
        }
        return "addlisting";
    }

    @PostMapping("/listingUpdated")
    public String listingupdated(@Valid Listing listing, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addlisting";
        } else {
            for (Listing listingUpdate : listingsList) {
                if (listing.getId() == listingUpdate.getId()) {
                    listingUpdate.setTitle(listing.getTitle());
                    listingUpdate.setAuthor(listing.getAuthor());
                    listingUpdate.setDescription(listing.getDescription());
                    listingUpdate.setPhone(listing.getPhone());
                    listingUpdate.setPostedDate(listing.getPostedDate());
                    break;
                }

            }
        }
        return "redirect:/viewListings";
    }

    @RequestMapping("/deleteListing/{userId}/{id}")
    public String deleteListing(@PathVariable long userId, @PathVariable long id){
        for (Listing listing : listingsList){
            if (listing.getId() == id || listing.getUserId() == userId){
                listingsList.remove(listing);
                break;
            }
        }
        return "redirect:/viewListings";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "redirect:/login?logout=true";
    }

    static void idSetter(Listing listing){
        idCounter += 1;
        listing.setId(idCounter);
    }
}
