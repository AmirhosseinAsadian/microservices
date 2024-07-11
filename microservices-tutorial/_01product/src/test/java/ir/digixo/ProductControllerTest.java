package ir.digixo;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ir.digixo.controller.ProductController;
import ir.digixo.dto.ProductDTO;
import ir.digixo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetProduct() throws Exception {
        String productName = "Test Product";
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(productName);

        when(productService.getProduct(productName)).thenReturn(productDTO);

        mockMvc.perform(get("/api/v1/product/getProduct")
                        .param("productName", productName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).getProduct(productName);
    }

    @Test
    public void testSaveProduct() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");

        mockMvc.perform(post("/api/v1/product/saveProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Product\",\"description\":\"Test Description\",\"price\":19.99}"))
                .andExpect(status().isOk());

        verify(productService, times(1)).saveProduct(any(ProductDTO.class));
    }
}
