package pack;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void sort(Product[] prods){
		MergeSort.sort(prods);
	}
	
	public Product find_with_asin(Product[] prods, String asin){
		int ret = BinarySearch.indexOf(prods, asin);
		
		if(ret == -1){
			return null;
		}else{
			return prods[ret];
		}
	}
	
	

}
