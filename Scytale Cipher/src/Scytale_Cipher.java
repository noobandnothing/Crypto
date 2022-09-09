
public class Scytale_Cipher {
	public static String scytaleEncode_try(String plainText , int numberOfRows) {
		String encodedText = "";
		if(numberOfRows >= plainText.length() || numberOfRows <= 0) {
			return plainText;
		}else {
			while(plainText.length() % numberOfRows != 0) {
				plainText += " ";
			}
			// numOfCols =  20 / 4(numberOfRows input) = 5;
			int numOfCols = plainText.length()/numberOfRows;
			/*sp = space in word As =added space
			      C0 C1 C2 C3 C4
			   R0 t  h  i  s  sp 
			   R1 i  s  sp s  o     
			   R2 m  e  sp t  e
               R3 x  t  As As As
               
			      C0 C1 C2 C3 C4				   
			   R0 0  1  2  3  4
		   	   R1 5  6  7  8  9
		       R2 10 11 12 13 14
		       R3 15 16 17 18 19
		       F(R,C) index = (R *5) + (C *1)
			 */
			
			for(int col = 0; col < numOfCols; col++) {
				for(int row = 0 ; row < numberOfRows;row++) {
					encodedText += plainText.charAt(row*numOfCols+col*1);
				}
			}
		}
		return encodedText;
	}

	public static String scytaleEncode(String plainText , int numberOfRows) {
		String encodedText = "";
		if(numberOfRows >= plainText.length() || numberOfRows <= 0) {
			return plainText;
		}else {
			/*
			 *  plainText length 17  not / , add " "
			 *  plainText length 18  not / , add " "
			 *  plainText length 19  not / , add " "
			 *  plainText length 20  not / , Stop
			 */ 
			while(plainText.length() % numberOfRows != 0) {
				plainText += " ";
			}
			// numOfCols =  20 / 4(numberOfRows input) = 5;
			int numOfCols = plainText.length()/numberOfRows;
			/*sp = space in word As =added space
			      C1 C2 C3 C4 C5
			   R1 t  h  i  s  sp 
			   R2 i  s  sp s  o     
			   R3 m  e  sp t  e
               R4 x  t  As As As
			 */
			for(int i = 0; i < numOfCols; i++) {
				for(int y = i ; y < plainText.length();y+= numOfCols) {
					encodedText += plainText.charAt(y);
				}
			}
		}
		return encodedText;
	}

	public static String scytaledecode(String encoded_text , int numberOfRows) {
		String decodedText = "";
		// numOfCols =  20 / 4(numberOfRows input) = 5;
		int numOfCols = encoded_text.length()/numberOfRows;
		/* Rotate {numOfCols = Pre_vnumOfRows  & numOfRows = Prev_numOfCols}
		 * then send num of colums as numrows
		 */
		/*sp = space in word As =added space
		      C1 C2 C3 C4 C5   Decode     C1 C2 C3 C4
		   R1 t  h  i  s  sp   Rotate  R1 t  i  m  x
		   R2 i  s  sp s  o   =======> R2 h  s  e  t
		   R3 m  e  sp t  e            R3 i  sp sp As
           R4 x  t  As As As           R4 s  s  t  As
		 							   R5 sp o  e  As
		*/
		decodedText = scytaleEncode(encoded_text, numOfCols);
		decodedText= decodedText.trim();
		return decodedText;
	}
	
	public static void main(String[] args) {
		String test = "This is some text : I am the president JOE BIDEN";
		System.out.println(test);
		System.out.println(test.length());
		String encoded = scytaleEncode(test,4);
		System.out.println(encoded);
		System.out.println(encoded.length());
		String decoded = scytaledecode(encoded, 4);
		System.out.println(decoded);
		System.out.println(decoded.length());
		//
		String encoded_custom = scytaleEncode_try(test,4);
		System.out.println(encoded_custom);
		System.out.println(encoded_custom.length());
		String decoded_custom = scytaleEncode_try(test,4);
		System.out.println(decoded_custom);
		System.out.println(decoded_custom.length());		

	}

}
