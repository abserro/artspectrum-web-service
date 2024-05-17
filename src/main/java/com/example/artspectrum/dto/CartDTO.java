package com.example.artspectrum.dto;

import com.example.artspectrum.entities.Cart;
import com.example.artspectrum.entities.CartItem;
import com.example.artspectrum.entities.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
	private User user;
	private List<CartItem> cartItems;
	public Cart toCart(){
		Cart cart = new Cart();
        cart.setUser(user);
        cart.setCartItems(cartItems);
        return cart;
	}
}
