package shoponline.utils;

import javax.servlet.http.HttpServletRequest;

import shoponline.model.CartInfo;

public class Utils {
  public static CartInfo getCartInSeeion(HttpServletRequest request) {
    CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");
    if (cartInfo == null) {
      request.getSession().setAttribute("myCart", cartInfo);
    }
    return cartInfo;
  }

  public static void removeCartInSession(HttpServletRequest request) {
    request.getSession().removeAttribute("myCart");
  }

  public static void storeLastOrseredCartInSession(HttpServletRequest request, CartInfo cartInfo) {
    request.getSession().setAttribute("lastOrderedCart", cartInfo);
  }

  public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
    return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
  }
}
