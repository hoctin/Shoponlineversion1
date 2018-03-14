package shoponline.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    //Redirect to shoppingCart page;
    return "redirect:/shoppingCart";
  }

  public String removeProductHandler(HttpServletRequest request, Model model,
      @RequestParam(value = "code", defaultValue = "") String code) {
    Product product = null;
    if (code != null && code.length() > 0) {
      product = productDAO.findProduct(code);
    }
    if (product != null) {
      CartInfo cartInfo = Utils.getCartInSeeion(request);
      ProductInfo productInfo = new ProductInfo(product);
      cartInfo.removeProduct(productInfo);
    }
    //Redirect to shoppingCart page;
    return "redirect:/shoppingCart";
  }

  //POST: Update quantity of products in cart.
  @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
  public String shoppingCartUpdateQty(HttpServletRequest request, Model model,
      @ModelAttribute("cartForm") CartInfo cartForm) {
    CartInfo cartInfo = Utils.getCartInSeeion(request);
    cartInfo.updateQuantity(cartForm);
    //Redirect to ahoppingCart page;
    return "redirect:/shoppingCart";
  }

  //GET: Show Cart
  public String shoppingCartHandler(HttpServletRequest request, Model model) {
    CartInfo myCart = Utils.getCartInSeeion(request);
    model.addAttribute("cartForm", myCart);
    return "shoppingCart";
  }

  //GET:Enter customer information.
  public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
    CartInfo cartInfo = Utils.getCartInSeeion(request);
    //Cart is empty
    if (cartInfo.isEmpty()) {
      return "redirect:/shoppingCart";
    }
    CustomerInfo customerInfo = cartInfo.getCustomerInfo();
    if (customerInfo == null) {
      customerInfo = new CustomerInfo();
    }
    model.addAttribute("customerForm", customerInfo);
    return "shoppingCartCustomer";
  }

  //POST:Save customer information
  public String shoppingCartCustomerSave(HttpServletRequest request, Model model,
      @ModelAttribute("customerForm") @Validated CustomerInfo customerForm, BindingResult result,
      final RedirectAttributes redirectAttributes) {
    //If has Errors.
    if (result.hasErrors()) {
      customerForm.setValid(false);
      //Forward to reenter customer info
      return "shoppingCartCustomer";
    }
    customerForm.setValid(true);
    CartInfo cartInfo = Utils.getCartInSeeion(request);
    cartInfo.setCustomerInfo(customerForm);
    //Redirect to Confirmation page
    return "redirect:/shoppingCartConfirmation";
  }

  //GET: review Cart to confirm.
  @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
  public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
    CartInfo cartInfo = Utils.getCartInSeeion(request);
    //Cart have no products.
    if (cartInfo.isEmpty()) {
      //Redirect to shoppingCart page.
      return "redirect:/shoppingCart";
    } else if (!cartInfo.isValidCustomer()) {
      //Enter customer info
      return "redirect:/shoppingCartCustomer";
    }
    return "shoppingCartConfirmation";
  }

  //POST : Send Cart(Save).
  public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
    CartInfo cartInfo = Utils.getCartInSeeion(request);
    //Cart have no products.
    if (cartInfo.isEmpty()) {
      //Redirect to shoppingCart page.
      return "redirect:/shoppingCart";
    } else if (!cartInfo.isValidCustomer()) {
      //Enter customer info.
      return "redirect:/shoppingCartCustomer";
    }
    try {
      orderDAO.saveOrder(cartInfo);
    } catch (Exception e) {
      //Need: Propagation.NEVER?
      return "shoppingCartConfirmation";
    }
    //Remove Cart In Session
    Utils.removeCartInSession(request);
    //Store Last ordered cart to Session.
    Utils.storeLastOrseredCartInSession(request, cartInfo);
    //Redirect to successful page.
    return "redirect:/shoppingCartFinalize";
  }

  @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
  public String shoppingCartFinalize(HttpServletRequest request, Model model) {
    CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
    if (lastOrderedCart == null) {
      return "redirect:/shoppingCart";
    }
    return "shoppingCartFinalize";
  }

  public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
      @RequestParam("code") String code) throws IOException {
    Product product = null;
    if (code != null) {
      product = this.productDAO.findProduct(code);
    }
    if (product != null && product.getImage() != null) {
      response.setContentType("image/jpeg,image/jpg,image/png,image/gif");
      response.getOutputStream().write(product.getImage());
    }
    response.getOutputStream().close();
  }
}
