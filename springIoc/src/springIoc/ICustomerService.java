package springIoc;

public interface ICustomerService {
	
	//Bu interface'i implemente eden CustomerManager yani müşteri ile alakalı işleri yapan classlar
	//bu add metodunu içermek zorunda. Bu add metodu parametre olarak ICustomerDal tipinde bir nesne
	//alacak ve o nesne üzerinden veritabanı işlemlerinin yapıldığı metodlara kodlarımız dallancak
	//Mainde kodların dallanmasını sağlamak için kullanılacak bu metod.
	void add();

}
