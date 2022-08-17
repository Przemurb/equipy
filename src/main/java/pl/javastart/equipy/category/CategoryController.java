package pl.javastart.equipy.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping ("names")
    List<String> findAllCategoryNames () {
        return categoryService.allCategories()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }
}
