package springIoc;

public class MsSqlCustomerDal implements ICustomerDal{
	
	//MsSql veri tabanına ait işlemler bu class'ın altında yapılır.
	
	//connectionString'e values.properties dosyasindan deger atamasi gerceklesecek applicationContext.xml dosyasında
	//bu atama işleminin gerçekleşmesi için atama işleminin gerçekleşeceği class'da da bu özelliği belirtmeliyim.
	String connectionString;
	
	//Bu class ICustomerDal'ı implemente ettiği için ICustomerDal'Da bulunan add metodunu override etti.
	@Override
	public void add() {
		
		//Bu metodun altında veritabanı işlemleri gerçekleşecek.
		
		//Bu class'ın özelliği olan connectionString'i kullanıyorum.
		System.out.println("Connection String : "+this.connectionString);
		System.out.println("MsSql veritabanina eklendi.");
	}
	
	//connectionString'e bu class üzerinden de bir atama işlemi yapılması lazım bu işlemin 
	//otomatik olarak applicationContext.xml dosyası ile birlikte gerçekleşmesi için 
	//setter'a ihtiyaç var.
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

}
