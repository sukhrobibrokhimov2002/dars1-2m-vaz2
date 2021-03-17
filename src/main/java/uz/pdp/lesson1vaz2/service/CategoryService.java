package uz.pdp.lesson1vaz2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1vaz2.entity.Category;
import uz.pdp.lesson1vaz2.entity.Language;
import uz.pdp.lesson1vaz2.payload.ApiResult;
import uz.pdp.lesson1vaz2.payload.CategoryDto;
import uz.pdp.lesson1vaz2.repository.CategoryRepository;
import uz.pdp.lesson1vaz2.repository.LanguageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResult add(CategoryDto categoryDto) {
        List<Language> languageList = new ArrayList<>();
        List<Integer> languageIds = categoryDto.getLanguageList();
        for (Integer languageId : languageIds) {
            Optional<Language> optionalLanguage = languageRepository.findById(languageId);
            if (!optionalLanguage.isPresent())
                return new ApiResult("Language not found", false);
            languageList.add(optionalLanguage.get());
        }
        Category category = new Category();
        category.setDescription(categoryDto.getDescription());
        category.setLanguageList(languageList);
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new ApiResult("Successfully added", true);
    }

    public List<Category> getAll() {
        List<Category> all = categoryRepository.findAll();
        return all;
    }

    public Category getOneById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return null;
        return optionalCategory.get();
    }

    public ApiResult delete(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResult("Successfully deleted", true);
        } catch (Exception e) {
            return new ApiResult("Error in deleting", false);
        }
    }

    public ApiResult edit(Integer id, CategoryDto categoryDto) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new ApiResult("Category not found", false);

        List<Language> editedLanguageList = new ArrayList<>();
        List<Integer> languageIds = categoryDto.getLanguageList();
        for (Integer languageId : languageIds) {
            Optional<Language> optionalLanguage = languageRepository.findById(languageId);
            if (!optionalLanguage.isPresent()) return new ApiResult("Language not found", false);
            editedLanguageList.add(optionalLanguage.get());
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setLanguageList(editedLanguageList);
        categoryRepository.save(category);
        return new ApiResult("Successfully edited", true);


    }
}
