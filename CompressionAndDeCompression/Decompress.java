public class Decompress {

  public static boolean isDigit(char c) {
    return (c > '0' && c < '9');
  }

  public static int getIntegerBehindIndex(int index, String str) {
    int ret = 0;
    int firstIndex = index - 1;
    while(firstIndex >= 0 && isDigit(str.charAt(firstIndex))) {
      firstIndex--;
    }
    for(int i = firstIndex + 1; i < index; i++) {
      ret = ret * 10 + (str.charAt(i) - '0');
    }
    return ret;
  }
  
  public static String deCompress(int l, int r, String str) {
    String deCompressedString = "";
    int firstPointer = l + 1, secondPointer = l + 1;
    while(firstPointer < r) {
      while(firstPointer < r && str.charAt(firstPointer) != '[') {
        firstPointer++;
      }
      if(firstPointer == r) {
        deCompressedString += str.substring(secondPointer, r);
      }
      while(secondPointer < r && str.charAt(secondPointer) != ']') {
        secondPointer++;
      }
      System.out.println(firstPointer + " " + secondPointer);
      if(secondPointer < r) deCompressedString += deCompress(firstPointer, secondPointer, str);
      firstPointer = secondPointer + 1;
      secondPointer++;
    }
    if(deCompressedString.length() == 0) {
      deCompressedString = str.substring(l + 1, r);
    } 
    int repeatations = getIntegerBehindIndex(l, str);
    String returnString = "";
    for(int i = 0; i < repeatations; i++) {
      returnString += deCompressedString;
    }
    return returnString;
  }
  
  public static void main(String[] args) {
    String test = "2[3[abc]4[ab]c]";
    System.out.println(deCompress(1, test.length() - 1, test));
  }
}