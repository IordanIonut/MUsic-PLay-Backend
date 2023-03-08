package com.example.MUsicPLay.Service;

import com.example.MUsicPLay.Model.Favorite;
import com.example.MUsicPLay.Repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    public List<Favorite> getAllFavorite() {
        return favoriteRepository.findAll();
    }
    public Favorite getFavoriteById(Long id) {
        return favoriteRepository.findById(id).orElse(null);
    }
    public Favorite addFavorite(Favorite user) {
        return favoriteRepository.save(user);
    }
    public Favorite updateFavorite(Favorite user) {
        Favorite existingUser = favoriteRepository.findById(user.getFavorite_id()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        return favoriteRepository.save(user);
    }
    public void deleteFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }
}
