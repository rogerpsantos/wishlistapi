package com.jaya.api.repository;

import com.jaya.api.domain.dto.AddressDTO;
import com.jaya.api.domain.dto.ProductDTO;
import com.jaya.api.domain.dto.UserDTO;
import com.jaya.api.domain.dto.WishlistDTO;
import com.jaya.api.domain.enums.Category;
import com.jaya.api.domain.model.Product;
import com.jaya.api.domain.model.User;
import com.jaya.api.domain.model.Wishlist;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotBlank;
import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
@ActiveProfiles("test")
class IWishlistRepositoryTest {

    @Autowired
    private IWishlistRepository wishlistRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IProductRepository productRepository;

    private static final BigDecimal PRICE_TEST = new BigDecimal(799.00);
    private static final Integer TOTAL_SIZE = 20;

    @Test
    @DisplayName("Search for a Wishlist that belongs to a certain user")
    void findWishListForUser() {
        var newuser =  new User(addUser("João da Silva", "joao.silva@gmail.com", "0123456789", "23891506597"));
        var newproduct = new Product((addProduct("Smartphone Samsung Galaxy A04s 64GB Branco 4G - Octa-Core 4GB RAM 6.5' Câm. Tripla + Selfie 5MP",
                                              "Para você que está a procura de um novo smartphone para dar aquele upgrade no seu dia a dia no trabalho ou para navegar nas redes sociais, precisa conhecer o Samsung Galaxy A04s na cor branca. Ele possui 64GB de armazenamento interno para guardar diversos aplicativos, fotos, vídeos e jogos com a possiblidade de aumentar através de cartão de memória, 4GB de memória RAM e processador Octa-Core para evitar aquelas travadas durante o uso. A tela dele é infinita de 6,5' com resolução HD+ e 90Hz de taxa de atualização, a câmera traseira é tripla de 50MP + 2MP + 2MP com abertura F1.8 + F2.4 + F2.4 e Zoom Digital de até 10X e a câmera frontal, é de 5.0MP com Abertura F2.2 e Flash Frontal. E ainda, é Dual Chip com tecnologia 4G, oferece leitor de impressão digital, reconhecimento facial e bateria de 5000mAh. Este smartphone não acompanha fone de ouvido.",
                                              PRICE_TEST,
                                              Category.SMARTPHONES)));
        List<Product> prod = new ArrayList<>();
        prod.add(newproduct);

        var user = this.userRepository.save(newuser);
        var product = this.productRepository.save(newproduct);

        Wishlist wishlist = null;
        if(isNotBlank(user.getName())) {
            wishlist = this.wishlistRepository.findWishListForUser(user.getId());
        }

        if(prod != null && wishlist != null){
            System.out.println("########### "+wishlist);
            if(wishlist.getProduct().size() < TOTAL_SIZE){
                wishlist.getProduct().add(this.productRepository.findById(product.getId()).get());
                var addwishlist = this.wishlistRepository.save(wishlist);
            }
        } else {
            wishlist = new Wishlist(user, prod);
            this.wishlistRepository.save(wishlist);
        }

        var userForWishList = this.wishlistRepository.findWishListForUser(user.getId());
        assertThat(userForWishList).isNotNull();
    }



    @Test
    @DisplayName("Search Product in Wishlist of user")
    void findProductInWishList() {
        var newuser =  new User(addUser("João da Silva", "joao.silva@gmail.com", "0123456789", "23891506597"));
        var newproduct = new Product((addProduct("Smartphone Samsung Galaxy A04s 64GB Branco 4G - Octa-Core 4GB RAM 6.5' Câm. Tripla + Selfie 5MP",
                "Para você que está a procura de um novo smartphone para dar aquele upgrade no seu dia a dia no trabalho ou para navegar nas redes sociais, precisa conhecer o Samsung Galaxy A04s na cor branca. Ele possui 64GB de armazenamento interno para guardar diversos aplicativos, fotos, vídeos e jogos com a possiblidade de aumentar através de cartão de memória, 4GB de memória RAM e processador Octa-Core para evitar aquelas travadas durante o uso. A tela dele é infinita de 6,5' com resolução HD+ e 90Hz de taxa de atualização, a câmera traseira é tripla de 50MP + 2MP + 2MP com abertura F1.8 + F2.4 + F2.4 e Zoom Digital de até 10X e a câmera frontal, é de 5.0MP com Abertura F2.2 e Flash Frontal. E ainda, é Dual Chip com tecnologia 4G, oferece leitor de impressão digital, reconhecimento facial e bateria de 5000mAh. Este smartphone não acompanha fone de ouvido.",
                PRICE_TEST,
                Category.SMARTPHONES)));
        List<Product> prod = new ArrayList<>();
        prod.add(newproduct);

        var user = this.userRepository.save(newuser);
        var product = this.productRepository.save(newproduct);

        Wishlist wishlist = null;
        if(isNotBlank(user.getName())) {
            wishlist = this.wishlistRepository.findWishListForUser(user.getId());
        }

        if(prod != null && wishlist != null){
            if(wishlist.getProduct().size() < TOTAL_SIZE){
                wishlist.getProduct().add(this.productRepository.findById(product.getId()).get());
                var addwishlist = this.wishlistRepository.save(wishlist);
            }
        } else {
            wishlist = new Wishlist(user, prod);
            this.wishlistRepository.save(wishlist);
        }

        boolean isExist = false;
        if(this.wishlistRepository.findProductInWishList(user.getId(), product.getId()) != null) {
            isExist = true;
        } else isExist = false;

        assertThat(isExist).isTrue();
    }

    private WishlistDTO addWishlistTest(User user, List<Product> products) {
        return new WishlistDTO(
                user,
                products,
                new Date()
        );
    }

    private ProductDTO addProduct(String name, String descriptionn, BigDecimal price, Category category) {
        return new ProductDTO(
                name, descriptionn, price, category
        );
    }

    private UserDTO addUser (String name, String email, String telephone, String cpf){
        return new UserDTO (
                name,
                email,
                telephone,
                cpf,
                address()
        );
    }

    private AddressDTO address() {
        return new AddressDTO(
                "Avenida dos Palista",
                "Bela Vista",
                "01310904",
                "São Paulo",
                "SP",
                null,
                "1219"
        );
    }



}