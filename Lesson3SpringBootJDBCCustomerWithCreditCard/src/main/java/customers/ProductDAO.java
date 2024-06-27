package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from product",namedParameters);
        jdbcTemplate.update("DELETE from supplier",namedParameters);
    }

    public void save(Product product) {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("productnumber", product.getProductNumber());
        namedParameters.put("name",product.getName());
        namedParameters.put("price", product.getPrice());

        jdbcTemplate.update("INSERT INTO product VALUES ( :productnumber, :name, :price)", namedParameters);

        // save supplier
        Map<String,Object> namedParameterscc = new HashMap<String,Object>();
        namedParameterscc.put("productNumber", product.getProductNumber());
        namedParameterscc.put("suppliernumber", product.getSupplier().getSupplierNumber());
        namedParameterscc.put("name", product.getSupplier().getName());
        namedParameterscc.put("phone", product.getSupplier().getPhone());
        jdbcTemplate.update("INSERT INTO supplier VALUES ( :suppliernumber, :name, :phone, :productNumber)",namedParameterscc);
    }



    public Product findProductById(int productNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                        + "productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getInt("price")));

        Supplier supplier = getSupplierForProduct(product.getProductNumber());
        product.setSupplier(supplier);
        return product;

    }
    
    public List<Product> getAllProducts(){
        List<Product> products = jdbcTemplate.query("SELECT * FROM product",
                new HashMap<String, Product>(),
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getInt("price")));

        for (Product product: products){
            Supplier supplier = getSupplierForProduct(product.getProductNumber());
            product.setSupplier(supplier);
        }

        return products;
    }


    public Product findProductByName(String name){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("name", name);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                        + "name =:name ",
                namedParameters,
                (rs, rowNum) -> new Product( rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getInt("price")));

        Supplier supplier=getSupplierForProduct(product.getProductNumber());
        product.setSupplier(supplier);

        return product;

    }

    public String removeProduct(int productNumber) {
        // Constructing the SQL query to delete the product
        String sqlProduct = "DELETE FROM product WHERE productNumber = :productNumber";

        // Constructing the SQL query to delete the supplier
        String sqlSupplier = "DELETE FROM supplier WHERE productNumber = :productNumber";

        // Creating parameter map for named parameters
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productNumber", productNumber);

        // Executing the delete operation for the supplier using JdbcTemplate
        int rowsAffectedSupplier = jdbcTemplate.update(sqlSupplier, namedParameters);

        // Executing the delete operation for the product using JdbcTemplate
        int rowsAffectedProduct = jdbcTemplate.update(sqlProduct, namedParameters);

        if (rowsAffectedProduct > 0 && rowsAffectedSupplier > 0) {
            return "Product and associated supplier with product number " + productNumber + " removed successfully.";
        } else if (rowsAffectedProduct > 0) {
            return "Product with product number " + productNumber + " removed successfully, but no associated supplier was found.";
        } else {
            return "No product found with product number " + productNumber + ".";
        }
    }


    Supplier getSupplierForProduct(int productNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productNumber", productNumber);
        Supplier supplier = jdbcTemplate.queryForObject("SELECT * FROM supplier WHERE "
                        + "productNumber =:productNumber ",
                namedParameters,
                (rs, rowNum) -> new Supplier( rs.getInt("suppliernumber"),
                        rs.getString("name"),
                        rs.getString("phone")));

        return supplier;
    }

}
