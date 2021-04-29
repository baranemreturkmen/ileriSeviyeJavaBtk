package springIoc;

public class CustomerManager implements ICustomerService{
	
	//Bu class'ın altında müşteri ile alakalı kodlar yazılır.
	
	//ICustomerDal tipinde bir özellik oluşturuldu bağımlılığı önlemek adına 
	//özelliğim interface tipinde.
	ICustomerDal customerDal;

	
	//constructor injection
	//applicationContext.xml dosyasında constructor injection'a ait parametreler verilir.
	public CustomerManager(ICustomerDal customerDal) {
		
		//Parametre olarak applicationContext.xml dosyasında hangi veritabanı objesi verilirse customerDal 
		//parametresi o veritabanı objesini getirir ve this.customerDal'a yani bu class'da oluşturduğumuz
		//customerDal özelliğine veritabanı objesini atar.
		this.customerDal = customerDal;
	}

	//setter injection
	//Eğer applicationContext.xml dosyasında setter injection'a ait parametreler verilirse setter injection
	//yapılır. Genelde constructor injection tercih edilir.
//	public void setCustomerDal(ICustomerDal customerDal) {
//		this.customerDal = customerDal;
//	}
	
	//Bu sınıf ICustomerService interface'ini implemente ettiğinden dolayı ICustomerService interface'ine
	//ait gövdesiz metodları/imzaları kullanmak zorunda. Bu durumda override işlemi ortaya çıkıyor.
	@Override
	public void add() {
		
		//Müşteri gerekli şartları sağlıyorsa eğer müşteriye ait bilgiler ilgili veri tabanına eklenir.
		//İlgili veritabanından kast edilen applicationContext.xml'de verilen ve constructorda bu sınıfın
		//özelliği olan customerDal'a atılan veritabanı objesidir. customerDal özelliği ICustomerDal interface'i
		//tipinde bir obje olduğu için ICustomerDal'ın sahip olduğu metodlara sahiptir. applicationContext.xml'de
		//verilen nesnelerde ICustomerDal tipinde nesneler olduğu için ve o nesnelerde class yapısında ICustomerDal'ı
		//implemente ettikleri için add metoduna onlarda sahipler.(Hem ICustomerService hem de ICustomerDal add imzasına
		//sahip ICustomerService'de ki add gelecekte oluşacak müşteri sınıflarımın Mainde add metodu ile customerDal.add()
		//metodunu çağırmasını sağlayacak. ICustomerDal'da ki add metodu kendisini implemente eden class'larda veri tabanı 
		//işlemlerinin yapılması için gerekli yani ICustomerDal'Daki add metodu kodların ICustomerManager tipindeki classlar
		//üzerinden dallanmasını sağlıyorken ICustomerDal'daki add kendisini implemente eden classlarda veri tabanı işleri için
		//kullanılıyor.)
		customerDal.add();
		
	}
	
	

}
