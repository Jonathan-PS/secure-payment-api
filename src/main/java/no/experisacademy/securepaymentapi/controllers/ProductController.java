package no.experisacademy.securepaymentapi.controllers;

import no.experisacademy.securepaymentapi.models.Product;
import no.experisacademy.securepaymentapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductRepository repository;

    @GetMapping("/products")
    public List<Product> findAllProducts(){
        List<Product> customers = repository.findAll();

        return customers;
    }

    @GetMapping("/products/{productId}")
    public Product findProductById(@PathVariable long productId){
        Optional<Product> product = repository.findById(productId);
        try{
            return product.get();
        }catch(Exception e){
            System.out.println("Product Not found");
            return null;
        }


    }

    /*@GetMapping("/products/{productId}")
    public Product findProductById(@PathVariable long productId, HttpServletRequest req){
        Optional<Product> product = repository.findById(productId);
        req.getSession().setAttribute("cart", myCart);

        return product.get();
    }*/

    @PostMapping("/createProduct")
    public String create(@RequestBody Product product){
        repository.save(new Product(product.getProductName(), product.getDescription(), product.getPriceEach(), product.getQuantity(), product.getImageURL(), true));

        return "Product is created";
    }
}
