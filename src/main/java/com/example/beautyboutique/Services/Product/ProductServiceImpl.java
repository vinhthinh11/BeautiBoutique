package com.example.beautyboutique.Services.Product;
import com.example.beautyboutique.Exception.ResourceNotFoundException;
import com.example.beautyboutique.Models.Product;
import com.example.beautyboutique.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl  implements  ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product saveAfterCheck(Product product) {
        if (productRepository.existsByProductName(product.getProductName())) {
            System.out.println("Product name already exists");
            return null;
        }
        return productRepository.save(product);
    }

    public Product delete(Integer id) {
        Product productToDelete = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        productRepository.delete(productToDelete);
        return productToDelete;
    }

    @Override
    public Product get(Integer id, Integer pageNumber, Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
            Page<Product> productPage = productRepository.findAll(pageable);
            if (pageNumber > productPage.getTotalPages()) {
                throw new ResourceNotFoundException("Page number exceeds total pages");
            }

            List<Product> products = productPage.getContent();
            if (id > products.size()) {
                throw new ResourceNotFoundException("Product with id " + id + " not found on the requested page");
            }

            return products.get(id - 1); // id - 1 vì id bắt đầu từ 1 trong yêu cầu người dùng, nhưng danh sách sản phẩm bắt đầu từ index 0
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
    }


    @Override
    public List<Product> findAll(Integer pageNumber, Integer pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
            Page<Product> productPage = productRepository.findAll(pageable);

            return productPage.getContent();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving products", e);
        }
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id "+ id +" not found "));
    }

    @Override
    public List<Product> findByName(String productName, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Product> productsPage = productRepository.findByProductNameContaining(productName, pageable);
        if (productsPage.isEmpty()) {
            throw new ResourceNotFoundException("Product with name '" + productName + "' not found");
        }

        return productsPage.getContent();
    }


    @Override
    public  List<Product> findProductBycCategoryId(int categoryId) {
        return this.productRepository.findProductByCategoryId(categoryId);
    }

}
