package com.boniato.category;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Lee on 2017. 5. 5..
 * Categoty Data Transter Object
 */
@Data
public class CategoryDto {

	private Long id;
	@NotBlank
	private String name;

}
