package com.mockcompany.webapp.services;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import com.mockcompany.webapp.data.ProductItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SearchService {

    private final ProductItemRepository productItemRepository;

    public SearchService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public Collection<ProductItem> SearchLogic(String query) {
        Iterable<ProductItem> allItems = this.productItemRepository.findAll();
        List<ProductItem> itemList = new ArrayList<>();

        boolean added = false;
        boolean exactSearch = false;

        if(query.contains("\"")) {
            query = query.replace("\"", "");
        }

        for(ProductItem item : allItems) {
            added = false;
            // TODO: Figure out if the item should be returned based on the query parameter!
            if(item.getName().toLowerCase().contains(query.toLowerCase()) && added == false && exactSearch == false) {
                added = true;
                boolean matchesSearch = true;
                itemList.add(item);
            }
            if(item.getDescription().toLowerCase().contains(query.toLowerCase()) && added == false && exactSearch == false) {
                added = true;
                boolean matchesSearch = true;
                itemList.add(item);
            }
            if(item.getName().toLowerCase().equals(query.toLowerCase()) && added == false && exactSearch == true) {
                added = true;
                boolean matchesSearch = true;
                itemList.add(item);
            }
            if(item.getDescription().toLowerCase().contains(query.toLowerCase()) && added == false && exactSearch == true) {
                added = true;
                boolean matchesSearch = true;
                itemList.add(item);
            }
        }

        return itemList;
    }

    public int countMatchingItems(String query) {
        return SearchLogic(query).size();
    }

}
