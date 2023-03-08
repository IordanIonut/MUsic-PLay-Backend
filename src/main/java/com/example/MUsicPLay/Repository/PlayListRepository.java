package com.example.MUsicPLay.Repository;

import com.example.MUsicPLay.Model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PlayListRepository  extends JpaRepository<PlayList,Long> {

}