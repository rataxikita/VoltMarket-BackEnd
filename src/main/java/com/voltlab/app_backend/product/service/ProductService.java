package com.voltlab.app_backend.product.service;

import com.voltlab.app_backend.product.dto.CategoryResponse;
import com.voltlab.app_backend.product.dto.CreateProductRequest;
import com.voltlab.app_backend.product.dto.ProductResponse;
import com.voltlab.app_backend.product.dto.UpdateProductRequest;
import com.voltlab.app_backend.product.model.Product;
import com.voltlab.app_backend.product.model.category.Category;
import com.voltlab.app_backend.product.repository.CategoryRepository;
import com.voltlab.app_backend.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Genera un SKU automáticamente basado en la categoría
     */
    private String generateSku(Long categoryId) {
        // Obtener categoría
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        
        // Map de prefijos por nombre de categoría
        String prefix = switch(category.getNombre().toUpperCase()) {
            case "BATERÍAS", "BATERIAS" -> "BAT";
            case "FRENOS" -> "FRE";
            case "RUEDAS Y NEUMÁTICOS", "NEUMÁTICOS", "NEUMATICOS", "RUEDAS" -> "RUE";
            case "MOTORES" -> "MOT";
            case "LUCES", "ILUMINACIÓN", "ILUMINACION" -> "LUZ";
            case "MANUBRIOS", "MANILLAR Y ACCESORIOS", "MANILLAR" -> "MAN";
            case "ELECTRÓNICA", "ELECTRONICA", "ELECTRÓNICA Y CONTROLADORES" -> "ELE";
            case "ACCESORIOS", "HERRAMIENTAS Y ACCESORIOS", "HERRAMIENTAS" -> "ACC";
            default -> "GEN"; // General
        };
        
        // Buscar el último SKU con este prefijo
        List<Product> productsWithPrefix = productRepository.findAll().stream()
                .filter(p -> p.getSku() != null && p.getSku().startsWith(prefix + "-"))
                .toList();
        
        int maxNumber = 0;
        for (Product p : productsWithPrefix) {
            try {
                String numberPart = p.getSku().substring(prefix.length() + 1);
                int number = Integer.parseInt(numberPart);
                if (number > maxNumber) {
                    maxNumber = number;
                }
            } catch (Exception e) {
                // Ignorar SKUs mal formateados
            }
        }
        
        // Generar nuevo SKU con formato de 3 dígitos (001, 002, etc.)
        return String.format("%s-%03d", prefix, maxNumber + 1);
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(this::toResponse).toList();
    }

    public List<ProductResponse> findActive() {
        return productRepository.findByActivoTrue().stream().map(this::toResponse).toList();
    }

    public List<ProductResponse> search(String q) {
        if (q == null || q.isBlank()) return findAll();
        return productRepository
                .findByNombreContainingIgnoreCaseOrMarcaContainingIgnoreCase(q, q)
                .stream().map(this::toResponse).toList();
    }

    public List<ProductResponse> findByCategory(Long categoryId) {
        return productRepository.findByCategoria_Id(categoryId).stream().map(this::toResponse).toList();
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toResponse(product);
    }

    @Transactional
    public ProductResponse create(CreateProductRequest request) {
        Product product = new Product();
        
        // Si no se proporciona SKU, generarlo automáticamente
        String sku = request.getSku();
        if (sku == null || sku.isBlank()) {
            Long categoryId = request.getCategoryId();
            if (categoryId != null) {
                sku = generateSku(categoryId);
            }
        } else if (productRepository.existsBySku(sku)) {
            throw new RuntimeException("El SKU ya existe");
        }
        
        applyCommon(product, request.getCategoryId(), request.getCategoryNombre(), 
                    request.getNombre(), request.getDescripcion(), request.getPrecio(), 
                    request.getStock(), request.getMarca(), request.getImageUrl(), 
                    sku, request.getActivo());
        
        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    @Transactional
    public ProductResponse update(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        applyCommon(product, request.getCategoryId(), request.getCategoryNombre(), request.getNombre(), request.getDescripcion(), request.getPrecio(), request.getStock(), request.getMarca(), request.getImageUrl(), request.getSku(), request.getActivo());
        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productRepository.deleteById(id);
    }

    // CRUD categoría simple
    public List<CategoryResponse> listCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryResponse(c.getId(), c.getNombre(), c.getDescripcion()))
                .toList();
    }

    // Helpers
    private void applyCommon(Product product,
                             Long categoryId,
                             String categoryNombre,
                             String nombre,
                             String descripcion,
                             java.math.BigDecimal precio,
                             Integer stock,
                             String marca,
                             String imageUrl,
                             String sku,
                             Boolean activo) {
        if (nombre != null && !nombre.isEmpty()) product.setNombre(nombre);
        if (descripcion != null) product.setDescripcion(descripcion);
        if (precio != null) product.setPrecio(precio);
        if (stock != null) product.setStock(stock);
        if (marca != null) product.setMarca(marca);
        if (imageUrl != null) product.setImageUrl(imageUrl);
        if (sku != null) product.setSku(sku);
        if (activo != null) product.setActivo(activo);

        if (categoryId != null) {
            Category c = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            product.setCategoria(c);
        } else if (categoryNombre != null && !categoryNombre.isBlank()) {
            Category c = categoryRepository.findByNombre(categoryNombre)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            product.setCategoria(c);
        }
    }

    private ProductResponse toResponse(Product product) {
        Long catId = product.getCategoria() != null ? product.getCategoria().getId() : null;
        String catNombre = product.getCategoria() != null ? product.getCategoria().getNombre() : null;
        return new ProductResponse(
                product.getId(),
                product.getNombre(),
                product.getDescripcion(),
                product.getPrecio(),
                product.getStock(),
                product.getMarca(),
                product.getImageUrl(),
                product.getSku(),
                product.getActivo(),
                catId,
                catNombre
        );
    }
}


