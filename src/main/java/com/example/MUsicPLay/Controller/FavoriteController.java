package com.example.MUsicPLay.Controller;

import com.example.MUsicPLay.Model.Content;
import com.example.MUsicPLay.Model.Favorite;
import com.example.MUsicPLay.Model.User;
import com.example.MUsicPLay.Repository.ContentRepository;
import com.example.MUsicPLay.Service.ContentService;
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
    @GetMapping("/favorite/search")
    public List<Favorite> getFavoriteHistory(@RequestParam Long userId, @RequestParam String type, @RequestParam String mood) {
        return favoriteService.findHistory(userId, type, mood);
    }
    @GetMapping("/favorite/delete/search")
    public List<Favorite>  deleteFavorite(@RequestParam Long  userId, @RequestParam String idPage) {
        List<Favorite> favoritesToDelete = favoriteService.findByUserIdAndIdPage(userId, idPage);
        for (Favorite favorite : favoritesToDelete) {
            favoriteService.deleteFavorite(favorite.getFavorite_id());
        }
        return favoriteService.findByUserIdAndIdPage(userId, idPage);
    }
    @GetMapping("/favorite/all")
    public List<Favorite> getAllFavorite() {
        return favoriteService.getAllFavorite();
    }
    @GetMapping("/favorite/all/user_id")
    public List<Favorite> findFavoriteByFavoriteID(@RequestParam Long user_id) {
        return favoriteService.findFavoriteByFavoriteID(user_id);
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
