package Market;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class ShoppingCart {
	private int cartId;
	private int userId;
	private Date cartdate;
	private int pid;
	private String title;
	private int price;
	private int total;
	private int count;
	private List<CartItem> cartItems;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return cartdate;
	}

	public void setDate(Date date) {
		this.cartdate = date;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	private List<CartItem> items;

	public ShoppingCart() {
		items = new ArrayList<>();
	}

	public void addItem(CartItem item) {
		items.add(item);
	}

	public void removeItem(CartItem item) {
		items.remove(item);
	}

	public List<CartItem> getItems() {
		return items;
	}

	public Date getCartdate() {
		return cartdate;
	}

	public void setCartdate(Date cartdate) {
		this.cartdate = cartdate;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public String getTitle() {
		return title;
	}

	public int getPrice() {
		return price;
	}

	public int getTotal() {
		return total;
	}

	public int getCount() {
		return count;
	}

	public void setTitle(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setPrice(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setTotal(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setCount(int int1) {
		// TODO Auto-generated method stub
		
	}

	public void setInfo(String string, int int1) {
		// TODO Auto-generated method stub
		
	}

}
