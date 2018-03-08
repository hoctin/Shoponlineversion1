package shoponline.dao;

import shoponline.entity.Product;
import shoponline.model.PaginationResult;
import shoponline.model.ProductInfo;

public interface ProductDAO {
  public Product findProduct(String code);

  public ProductInfo findProductInfo(String code);

  public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage);

  public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName);

  public void save(ProductInfo productinfo);
}
