package edu.airbnb.foodappdto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class FoodItems implements Serializable, Comparable{

	private int food_id;
	private String food_name; 
	double price; 
	double rating;
	double customer_rating;
	
	
	public FoodItems() {
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public double getCustomerRating() {
		return customer_rating;
	}
	public void setCustomerRating(double customer_rating) {
		this.customer_rating = customer_rating;
	}
	
	@Override
	public String toString() {
		return food_id+"\t" +food_name+"\t"+ price+"\t"+customer_rating+"\n";
	}
	@Override
	public int hashCode() {
		return Objects.hash(customer_rating, food_id, food_name, price, rating);
	}
	@Override
	public boolean equals(Object obj) {
		FoodItems f= (FoodItems)obj; 
		
		if((this.food_id==(f.food_id)) && ((this.food_name).equals(f.food_name)) && (this.price == f.price) && (this.rating == f.rating) && (this.customer_rating==f.customer_rating))
			return true; 
		else 
			return false;
	} 
	
	public int compareTo(Object o)
	{
		FoodItems f=(FoodItems)o;
		return (int) (this.customer_rating-f.customer_rating);
	}
	
		
	
	
}
