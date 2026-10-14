package villagegaulois;

//Bonnes pratiques Exception
public class VillageSansChefException extends Exception{
	public VillageSansChefException() {
        super();
    }

    public VillageSansChefException(String message) {
        super(message);
    }
}	
