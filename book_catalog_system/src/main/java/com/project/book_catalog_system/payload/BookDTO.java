package com.project.book_catalog_system.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String author;

    @Min(value = 1800, message = "Date should be more than or equal to 1800")
    @Max(value = 2027, message = "Date should be less than or equal to current year")
    private Integer yearPublished;

    private String genre;

}
