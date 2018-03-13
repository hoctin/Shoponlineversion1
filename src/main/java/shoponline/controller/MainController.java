package shoponline.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import shoponline.dao.OrderDAO;
import shoponline.dao.ProductDAO;
import shoponline.entity.Product;
import shoponline.model.CartInfo;
import shoponline.model.CustomerInfo;
import shoponline.model.PaginationResult;
import shoponline.model.ProductInfo;
import shoponline.utils.Utils;
import shoponline.validator.CustomerInfoValidator;

/**
 * @author hieu.tpk
 */
@Controller
@Transactional
@EnableWebMvc
public class MainController {
  @Autowired
  private OrderDAO orderDAO;
  @Autowired
  private ProductDAO productDAO;
  @Autowired
  private CustomerInfoValidator customerInfoValidator;

  public void myInitBinder(WebDataBinder dataBinder) {
    Object target = dataBinder.getTarget();
    if (target == null) {
      return;
    }
    if (target.getClass() == CartInfo.class) {
      return;
    } else if (target.getClass() == CustomerInfo.class) {
      dataBinder.setValidator(customerInfoValidator);
    }
  }

  @RequestMapping("/403")
  public String accessDenied() {
    return "/403";
  }

  @RequestMapping("/")
  public String home() {
    return "index";
  }

  @RequestMapping("/productList")
  public String listProductHandler(Model model, @RequestParam(value = "name", defaultValue = "") String likeName,
      @RequestParam(value = "page", defaultValue = "1") int page) {
    final int maxResult = 5;
    final int maxNavigationPage = 10;
    PaginationResult<ProductInfo> result = productDAO.queryProducts(page, maxResult, maxNavigationPage, likeName);
    model.addAttribute("paginationProducts", result);
    return "productList";
  }

  public String listProductHandler(HttpServletRequest request, Model model,
      @RequestParam(value = "code", defaultValue = "") String code) {
    Product product = null;
    if (code != null && code.length() > 0) {
      product = productDAO.findProduct(code);
    }
    if (product != null) {
      CartInfo cartInfo = Utils.getCartInSeeion(request);
      ProductInfo productInfo = new ProductInfo(product);
      cartInfo.addProduct(productInfo, 1);
    }
    return "redirect:/shoppingCart";
  }

  public String removeProductHandler(HttpServletRequest request, Model model,
      @RequestParam(value = "code", defaultValue = "") String code) {
    Product product = null;
    if (code != null && code.length() > 0) {
      product = productDAO.findProduct(code);
    }
    if (product != null) {

    }
    return "redirect:/shoppingCart";
  }
}
