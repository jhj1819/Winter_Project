package shoeMarket;

public class Shoeslist {

	String name;
	String brand;
	String shoesCategory;
	String sex;
	String image;
	int num_of_purchases;
	int price;

	String info;

	public Shoeslist(String name, String brand, String shoesCategory, String sex, int price) {
		this.name = name;
		this.brand = brand;
		this.shoesCategory = shoesCategory;
		this.sex = sex;
		this.price = price;
	}

	public Shoeslist(String name, String brand, String shoesCategory, String sex, String image, int num_of_purchases,
			int price) {
		this.name = name;
		this.brand = brand;
		this.shoesCategory = shoesCategory;
		this.sex = sex;
		this.image = image;
		this.num_of_purchases = num_of_purchases;
		this.price = price;
	}

	public String toString() {
		return "제품명 : " + name + "\n브랜드 : " + brand + "\n분류 : " + shoesCategory + "\n성별 : " + sex + "\n가격 : " + price
				+ "원";
	}

	void output() {
		System.out.println(toString());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getshoesCategory() {
		return shoesCategory;
	}

	public void setshoesCategory(String shoesCategory) {
		this.shoesCategory = shoesCategory;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getNum_of_purchases() {
		return num_of_purchases;
	}

	public void setNum_of_purchases(int num_of_purchases) {
		this.num_of_purchases = num_of_purchases;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
