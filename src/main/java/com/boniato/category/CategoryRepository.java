package com.boniato.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lee on 2017. 5. 5..
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
