public class DataTypePreSuf {
	public static void main( String[] args ) {

		System.out.println( 0XF );

		System.out.println( 010 );

		System.out.println( 0b1001 );

		int no = 1_000_000;
		System.out.println( no );

		long l = 100L;
		System.out.println( l );

		float fd = 98.6f;
		System.out.println( fd );
		
		
		int i = 45;				
		
		long o = 67876976;

		short j = 6;
		
		float f = 67.8F;
		
		double d = 89.8998976564543F;
		
		char c = 'z';
		
		String str = "Hello ?";
		
		System.out.println("------------------");
		
		System.out.println(str);
		System.out.println(c);
		System.out.println(d);
		System.out.println(f);
		System.out.println(j);
		System.out.println(o);
		System.out.println(i);
		System.out.println("------------------");
		System.out.println(str+" "+c +",  "+ d +" "+ f +" "+ j +" "+ o +" "+i);	
		System.out.println("------------------");	

	}
}
