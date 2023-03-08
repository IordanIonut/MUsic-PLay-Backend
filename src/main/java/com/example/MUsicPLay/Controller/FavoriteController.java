package com.example.MUsicPLay.Controller;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Model.Favorite;
import com.example.MUsicPLay.Service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;
    @GetMapping("/favorite/all")
    public List<Favorite> getAllFavorite() {
        return favoriteService.getAllFavorite();
    }
    @GetMapping("/favorite/get/{id}")
    public Favorite getFavoriteById(@PathVariable Long id) {
        return favoriteService.getFavoriteById(id);
    }
    @PostMapping("/favorite/add")
    public Favorite addFavorite(@RequestBody Favorite user) {
        Favorite savedContent = favoriteService.addFavorite(user);
        return new ResponseEntity<>(savedContent, HttpStatus.CREATED).getBody();
    }
    @PutMapping("/favorite/update")
    public Favorite updateFavorite(@RequestBody Favorite user) {
        return favoriteService.updateFavorite(user);
    }
    @DeleteMapping("/favorite/delete/{id}")
    public void deleteFavorite(@PathVariable Long id) {
        favoriteService.deleteFavorite(id);
    }
}
