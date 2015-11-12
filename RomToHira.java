public class RomToHira
{
    public static void main(String[] args) {
	
	if(args.length != 0) 
	{
	    for (int i = 0; i <= args.length - 1; i++)
	    {
		args[i].toLowerCase();
	    }
	    
	    char[][] charArray = splitWords(args);
	    
	    parseChars(charArray);
	    
	    System.out.println();
	}
	else
	{
	    System.out.println("Please enter a phrase " + 
				    "in roumaji after the call.");
	    System.out.println("Ex: \"java RomToHira suteki da ne\"");
	}
    }
    
    public static void parseChars(char[][] cA)
    {
	for(int i = 0; i <= cA.length - 1; i++)
	   {
		for (int j = 0; j <= cA[i].length - 1; j++)
		{
		    char c = cA[i][j];
		    if(c != '\0')
		    {
			char c2 = cA[i][j + 1];
			char c3 = cA[i][j + 2];
			if (isCompoundConsonant(c) && (c2 == 'y' || c2 == 'h'))
			{
			    printCompoundSyllable(c, c2, c3);
			    j += 2;
			}
			else if (isVowel(c))
			{
			    printStandaloneVowel(c);
			}
			else if (isStandardConsonant(c) && isVowel(c2))
			{
			    printStandardSyllable(c, c2);
			    j += 1;
			}
			else if (isStandardConsonant(c) && isStandardConsonant(c2))
			{
			    System.out.print('\u3063');
			}
			else if (c == 'n') 
			{
			    System.out.print('\u3093');
			}
		    }
		    
		}
	   }
    }
    
    // kya, sha, mya, nya, cha
    // ki, gi, shi, ji
    public static void printCompoundSyllable(char c, char c2, char c3)
    {
	char syllable = '\u304d'; // start at ki
	
	switch(c)
	{
	    // k is default
	    case 'g':
		syllable += 1;
	    break;
	    case 's':
		syllable += 1;
	    break;
		
	}
	
    }
    
    public static void printStandardSyllable(char c, char c2)
    {
	char syllable = '\u304b';
	
	switch (c)
	{
	    case 'g':
		syllable += 1;
	    break;
	    case 's':
		syllable += 10;
	    break;
	    case 'z':
		syllable += 11;
	    break;
	    case 't':
		syllable += 20;
	    break;
	    case 'd':
		syllable += 21;
	    break;
	    case 'n':
		syllable += 30;
	    break;
	    case 'h':
		syllable += 35;
	    break;
	    case 'f':
		syllable += 35;
	    break;
	    case 'b':
		syllable += 36;
	    break;
	    case 'p':
		syllable += 37;
	    break;
	    case 'm':
		syllable += 50;
	    break;
	    case 'y':
		syllable += 56;
	    break;
	    case 'r':
		syllable += 61;
	    break;
	    case 'w':
		syllable += 66;
	    break;
	}
	
	if(c == 'n' || c == 'm' || c == 'r')
	{
	    switch (c2)
	    {
		case 'i':
		    syllable += 1;
		break;
		case 'u':
		    syllable += 2;
		break;
		case 'e':
		    syllable += 3;
		break;
		case 'o':
		    syllable += 4;
		break;
	    }
	}
	else if (c == 'h' || c == 'b' || c == 'p' || c == 'f')
	{
	    switch (c2)
	    {
		case 'i':
		    syllable += 3;
		break;
		case 'u':
		    syllable += 6;
		break;
		case 'e':
		    syllable += 9;
		break;
		case 'o':
		    syllable += 12;
		break;
	    }
	}
	else if (c == 'y')
	{
	    switch (c2)
	    {
		case 'u':
		    syllable += 2;
		break;
		case 'o':
		    syllable += 4;
		break;
	    }
	}
	else if (c == 'w')
	{
	    switch (c2)
	    {
		case 'i':
		    syllable += 1;
		break;
		case 'e':
		    syllable += 2;
		break;
		case 'o':
		    syllable += 3;
		break;
	    }
	}
	else
	{
	    switch (c2)
	    {
		case 'i':
		    syllable += 2;
		break;
		case 'u':
		    syllable += 4;
		break;
		case 'e':
		    syllable += 6;
		break;
		case 'o':
		    syllable += 8;
		break;
	    }
	}
	
	
	
 	if(syllable >= '\u3063')
 	{
 	    syllable += 1;
	}
	if(syllable >= '\u308e')
	{
	    syllable += 1;
	}
	
	System.out.print(syllable);
	
    }
    
    public static void printStandaloneVowel(char c)
    {
	char vowel = '\u3042';
	
	switch (c)
	{
	    case 'i':
		vowel += 2;
	    break;
	    case 'u':
		vowel += 4;
	    break;
	    case 'e':
		vowel += 6;
	    break;
	    case 'o':
		vowel += 8;
	    break;
	}
	
	System.out.print(vowel);
    }
    
    
    
    public static char[][] splitWords(String[] words)
    {
	
	char[][] cArray = new char[32][255];
	
	for(int i = 0; i <= words.length - 1; i++)
	{
	    //System.out.print(words[i] + " ");
	    
	    for(int j = 0; j <= words[i].length() - 1; j++)
	    {
		cArray[i][j] = words[i].charAt(j);
	    }
	    //System.out.println();
	}
	
	return cArray;
    }
    
    public static boolean isVowel(char c)
    {
	return (c == 'a' || c == 'i' || c == 'u' || c == 'e' || c == 'o');
    }
    
    public static boolean isStandardConsonant(char c)
    {
	return (c == 'k' || c == 's' || c == 't' || c == 'h' || c == 'm' 
	     || c == 'n' || c == 'b' || c == 'p' || c == 'r' || c == 'g' 
	     || c == 'z' || c == 'd' || c == 'y' || c == 'w' || c == 'f'
	     || c == 'c');
    }
    public static boolean isCompoundConsonant(char c)
    {
	return (c == 'k' || c == 's' || c == 't' || c == 'h' || c == 'm' 
	     || c == 'n' || c == 'b' || c == 'p' || c == 'r' || c == 'g' 
	     || c == 'z' || c == 'd');
    }
    public static boolean isIrregularComponent(char c)
    {
	// shi, sha, dzu
	return (c == 's' || c == 'h' || c == 'z');
    }
}










/*
    http://www.unicode.org/charts/PDF/U3040.pdf
    3042, 3044, 3046, 3048, 304A
    
    
    GA - KA = 1
    ZA - SA = 1
    SA - KA = 10
    TA - SA = 10
    
    
    >> RULES <<
    
    if index + 1 in word = vowel or N,
	print standalone vowel at index
    
    
    :: Standalone Vowels
    3041 - 304a
    small A
    A
    small I
    I
    ...

    :: K / G
    304b - 3054
    KA
    GA
    KI
    GI
    ...
    
    :: S / Z
    3055 - 305E
    ^ See above
    
    :: T / D
    305F - 3069
    ^ See above
    SMALL TSU is 3063, and DZU and beyond is +1
    
    :: N
    306A - 306E
    NA, NI, NU, NE, NO
    
    :: H / B / P
    3070 - 307D
    HA, BA, PA
    HI, BI, PI
    ...
    
    :: M
    307E - 3082
    
    :: Y
    3083 - 3088
    ya, YA, yu, YU, yo, YO
    
    :: R
    3089 - 308D
    
    :: W
    308E - 3092
    wa, WA, WI, WE, WO
    
    N - 3093
    VU - 3094
 
 
*/
