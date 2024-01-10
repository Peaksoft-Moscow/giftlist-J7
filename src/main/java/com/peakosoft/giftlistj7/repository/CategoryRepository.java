package com.peakosoft.giftlistj7.repository;

import com.peakosoft.giftlistj7.model.entities.Category;
import com.peakosoft.giftlistj7.model.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
