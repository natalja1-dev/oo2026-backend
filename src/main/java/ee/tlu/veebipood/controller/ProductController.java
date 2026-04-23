package ee.tlu.veebipood.controller;

import ee.tlu.veebipood.entity.Product;
import ee.tlu.veebipood.repository.ProductRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") //turvaviga, päris arendustes seda ei teeks
//@CrossOrigin(origins = "http://localhost:5173"
@RestController
public class ProductController {

    //localhost:8080
    //application.properties server.port=8090
    /*@GetMapping("products")
    public String helloWorld(){
        return "Hello World!";
    }*/
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProducts(){
        return  productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getOneProduct(@PathVariable Long id){
        return  productRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product){
        if (product.getId() != null){
            throw new RuntimeException("Cannot add product with id");
        }
        productRepository.save(product); //siin salvestab
        return productRepository.findAll(); //siin uuenenud seis
    }

    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product){
        // File -> Settings -> Plugins -> Install
        if (product.getId()== null){
            throw new RuntimeException("Cannot edit without ID");
        }
        if (!productRepository.existsById(product.getId())){
            throw new RuntimeException("Product does not exist");
        }
        productRepository.save(product); //siin salvestab
        return productRepository.findAll(); //siin uuenenud seis
    }//put id-ga, post id-ta
}