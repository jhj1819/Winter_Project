package shoeMarket;

public class Purchaselist {
	String name;
	String brand;
	String shoesCategory;
	String sex;
	String image;
	int num_of_purchases;
	int price;
	String id;

	public Purchaselist(String name, String brand, String id, int price) {
		this.name = name;
		this.brand = brand;
		this.id = id;
		this.price = price;

	}

	public Purchaselist(String name, String brand, String shoesCategory, String sex, int price, String id) { // 추가된 필드
																												// 반영
		this.name = name;
		this.brand = brand;
		this.shoesCategory = shoesCategory;
		this.sex = sex;
		this.price = price;
		this.id = id;
	}

	public String toString() {
		return "상품 이름 : " + this.name + " - 브랜드 : " + this.brand + " - 가격 : " + this.price;

	}
}
