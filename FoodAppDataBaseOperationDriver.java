package edu.airbnb.foodapputillayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

import edu.airbnb.foodappdao.FoodItemsDataBaseOperation;
import edu.airbnb.foodappdto.FoodItems;

public class FoodAppDataBaseOperationDriver {

	public static void main(String[] args) {

		System.out.println("**********************************");
		System.out.println("Welcome to Food App");
		System.out.println("**********************************");
		FoodItemsDataBaseOperation fb=new FoodItemsDataBaseOperation();
		Scanner sc= new  Scanner(System.in);

		while(true)
		{
			System.out.println("Please select if you are \n1.Owner \n2.User \n3.Logout");
			int choice=sc.nextInt(); 

			switch(choice)
			{
			case 1:
			{
				while(true)
				{
					System.out.println("------------------------------");
					System.out.println("Please select from the below options \n1.Add food items \n2.View All Food Items \n3.Remove Food Items \n4.Logout");
					System.out.println("------------------------------");
					int select=sc.nextInt(); 

					if(select==1)
					{
						while(true)
						{
							System.out.println("Please confirm if you would like to add or go back to previous menu by selecting \na.Proceed \nb.Exit");
							char ch1=sc.next().charAt(0);
							if(ch1=='a')
							{
								FoodItems f= new FoodItems(); 
								System.out.println("Please enter the food id");
								f.setFood_id(sc.nextInt());
								System.out.println("Please enter the food name");
								f.setFood_name(sc.next());
								System.out.println("Please enter the price");
								f.setPrice(sc.nextDouble());
								System.out.println("Please enter the Rating");
								f.setRating(sc.nextDouble());

								boolean is_added=fb.addFoodItems(f);
								if(is_added)
									System.out.println("\nItems added successfully");
								else 
									System.out.println("Items not added!!");
							}else if(ch1=='b')
							{
								break;
							}else
								System.out.println("Invalid choice");
						}
					}else if(select ==2)
					{
						ArrayList<FoodItems> food_list=fb.viewAllFoodItems(); 

						if(food_list.isEmpty())
						{
							System.out.println("No food items in the list!!");
						}else
						{
							System.out.println("----------------------------");
							for(int i=0; i<food_list.size();i++)
							{
								FoodItems f1= food_list.get(i);
								System.out.println(f1.getFood_id()+"\t\t"+f1.getFood_name()+"\t\t"+f1.getPrice()+"\t\t"+f1.getRating()+"\n");
							}
							System.out.println("----------------------------");
						}
					}else if(select==3)
					{
						FoodItems f= new FoodItems(); 
						System.out.println("Please enter the id you would like to delete");
						f.setFood_id(sc.nextInt());
						boolean is_removed=fb.removeFoodItems(f);
						if(is_removed)
						{
							System.out.println("Food item removed successfully!!!");
						}else
							System.out.println("No such item found!!");
					}else if(select==4)
					{
						System.out.println("Thank you");
						break;
					}else 
						System.out.println("Invalid choice");

				}
			}
			break;

			case 2:
			{
				while(true)
				{
					System.out.println("------------------------------");
					System.out.println("Please select from the below options \n1.View All Food Items \n2.Order Food \n3.Logout");
					System.out.println("------------------------------");
					int ch1=sc.nextInt();
					if(ch1==1)
					{
						System.out.println("Please select if you would like to view Menu on basis of rating or price \n1.Rating \n2.Price");
						int ch2=sc.nextInt(); 
						PriorityQueue<FoodItems> sort_list=fb.viewFoodBasedOnRating(ch2);	
						System.out.println("-------------------------");
						
						if(sort_list.isEmpty())
						{
							System.out.println("No records found");
						}else {
							Iterator<FoodItems> i=sort_list.iterator();
							while (i.hasNext()) {
								FoodItems foodItems = (FoodItems) i.next();
								System.out.println(foodItems);
						}
						System.out.println("---------------------");
						
							
						}
						
//						if(ch2==1)
//						{
//							
//						}
//
//						ArrayList<FoodItems> food_list=fb.viewAllFoodItems();
//						if(food_list.isEmpty())
//						{
//							System.out.println("No food items in the list!!");
//						}else
//						{
//							System.out.println("----------------------------");
//							for(int j=0; j<food_list.size();j++)
//							{
//								FoodItems f1= food_list.get(j);
//								System.out.println(f1.getFood_id()+"\t\t"+f1.getFood_name()+"\t\t"+f1.getPrice()+"\t\t"+f1.getCustomerRating()+"\n");
//							}
//							System.out.println("----------------------------");
//						}
					}

					else if(ch1==2)
					{
						ArrayList<FoodItems> food_list=fb.viewAllFoodItems();
						if(food_list.isEmpty())
						{
							System.out.println("No food items in the list!!");
						}else
						{
							System.out.println("----------------------------");
							for(int i=0; i<food_list.size();i++)
							{
								FoodItems f1= food_list.get(i);
								System.out.println(f1.getFood_id()+"\t\t"+f1.getFood_name()+"\t\t\t\t\t"+f1.getPrice()+"\t\t"+f1.getCustomerRating()+"\n");
							}

							double price=0; 
							ArrayList<FoodItems> food_list1=new ArrayList<>(); 
							FoodItems f1=null;
							while(true)
							{
								System.out.println("Please enter the food name u would like to order");
								String choice2=sc.next();
								f1=fb.searchFoodItem(choice2);

								if(f1!=null)
								{
									f1.setFood_name(choice2);	
									System.out.println("Please enter the quantity");
									int quantity=sc.nextInt(); 
									price=price+(f1.getPrice()*quantity);
									f1.setPrice(price);
									FoodItems food_list2=fb.orderFood(f1);
									food_list1.add(f1);
									System.out.println("You have placed order for : "+ f1.getFood_name()+"\nThe price is "+price);
									
									System.out.println("Do you want to remove any item from your cart \nA.Yes \nB.No");
									char ch3=sc.next().charAt(0); 
									if(ch3=='A')
									{
										System.out.println("Please enter the food name");
										String name=sc.next(); 
										f1.setFood_name(name);
										boolean is_removed=fb.removeFoodItems(f1);
										if(is_removed)
											System.out.println("Item removed successfully");
									}
									
									System.out.println("Do you want to add food to your cart? \nY \nN");
									char ch=sc.next().charAt(0); 
									if(ch=='Y')
									{

									}else if(ch=='N')
									{
										break;
									}
									

								}else 
									System.out.println("Food item doesnt exist!!");
							}
							System.out.println("Your cart contains");
							System.out.println("-------------------------------");
							System.out.println("Food Name"+"\t\t"+"Food Price");
							for (int i=0; i<food_list1.size();i++)
							{
								FoodItems f3= food_list1.get(i); 
								System.out.println(f3.getFood_name()+"\t\t"+f3.getPrice());
							}
							
							
							boolean b=true;
							while(b)
							{
								System.out.println("The bill amount is Rs: "+price);
								System.out.println("Please enter the bill amount again");
								double bill1=sc.nextDouble();
								if(price==bill1)
								{
									System.out.println("Your order has been placed successfully");
									System.out.println("Would u like to place rating out of 5 for the ordered dish \n1.Yes \n2.No");
									b=false;
									int opt=sc.nextInt(); 
									if(opt==1)
									{
										boolean is_updated=false; 
										for(int i=0; i<food_list1.size(); i++)
										{
											FoodItems f2=food_list1.get(i); 
											System.out.println("Please enter the rating for the dish "+ f2.getFood_name());
											double rating=f2.getCustomerRating();
											f2.setCustomerRating((rating+sc.nextDouble())/2);
											is_updated=fb.feedback(f1);
										}
										if(is_updated)
										{
											System.out.println("Have a happy meal!!");
											System.out.println("Thank You!! Visit again");
										}else
											System.out.println("Thank You!!");

									}else
										System.out.println("Have a happy meal!!");
								}else
									System.out.println("Please retry");
							}
						}

					}else if(ch1==3)
					{
						break;
					}
				}
				break;
			}


			case 3:
			{
				System.out.println("\tThank You");
				System.exit(0);
			}
			}
		}
	}
}

