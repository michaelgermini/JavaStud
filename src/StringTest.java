public class StringTest {
	public static void main( String[] args ) {

		// // TEST 1
		System.out.println("// // TEST 1");
		char[] ca = { 'j', 'a', 'v', 'a' };
		String caStr = new String( ca );
		System.out.println( caStr );
		
		// // TEST 2
		System.out.println("// // TEST 2");	
		String sv = "JAVA";
		System.out.println( sv );
		
		// // Test 3:
		System.out.println("// // TEST 3");
		System.out.println( "JAVA3" );
		
		// // TEST 4:
		System.out.println("// // TEST 4");
		String h1 = "HELLO String";
		int l = h1.length( );
		System.out.println( l );

		// // TEST 5:
		System.out.println("// // TEST 5");
		System.out.println( "HELLO Test5".length( ) );

		// // TEST 6:
		System.out.println("// // TEST 6");
		System.out.println( "HELLO".toLowerCase( ) );
		System.out.println( "hello".toUpperCase( ) );
		
		// // TEST 7:
		System.out.println("// // TEST 7");
		System.out.println( "HELLO".indexOf('L', 8 ) );

		// // TEST 8:
		System.out.println("// // TEST 8");
		String j = "JAVA";
		System.out.println( j.concat( " Student" ) );
		System.out.println( j );

		// // TEST 9:
		System.out.println("// // TEST 9");
		String s9 = "HELLO";
		s9 = s9.concat( " WORLD" );
		System.out.println( s9 );
		
		// // TEST 10:
		System.out.println("// // TEST 10");
		String str = "BSC";
		String rs = str.concat( "COMP SC" );
		System.out.println( rs );
		
		// // TEST 11:
		System.out.println("// // TEST 11");
		String s1 = "Java string split method";
		String[] words = s1.split( "\\s" );
		for ( int i = 0; i < words.length; i++ ) {
		System.out.println( words[ i ] );
		}
		
		// // TEST 12:
		System.out.println("// // TEST 12");
		String s = "HELLO";
		System.out.println( s );
		System.out.println( s.toString( ) );
		
		// // TEST 13:
		System.out.println("// // TEST 13");
		String str1 = "Hello";
		String str2 = "Java String";
		char who = '!';
		System.out.println(str1+" "+str2+" "+who);

	}
}
