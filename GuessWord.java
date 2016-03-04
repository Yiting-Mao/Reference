import java.util.Random;
import java.util.Scanner;

class Word{
	static String words[]={"apple","banana","canny","damp","electricity","foreign","girlfriend","hospital","internet","lobster"};
	private String temp; //��ǰҪ�µĵ���
	private char[] GuessState; //������ʾ�û�Ŀǰ�µĽ��
	private int Unguess; //δ���е���Ŀ
	private int leng;
	public char[] getWord(){return GuessState;}
	public int getUnguess(){return Unguess;}
	public int getLeng(){return leng;}
	public String getWordtoGuess(){return temp;}
	public void RandomFill(){ //�������һ����ĸ
			int number = new Random().nextInt(Unguess);
			char b;
			int j=0,i=0;
			while(GuessState[j*2]!='_')j++;
			while(i<number)
			{
				j++;
				i++;
				while(GuessState[j*2]!='_')j++;
			}		
		    b=temp.charAt(j);
		    int pos=temp.indexOf(b);
		    Fillin(b, pos);			
		}
	
	
	public void GuessDecision(char input){ //�ж��û��µĶԴ�
		int pos=temp.indexOf(input);
		if (pos==-1) return;
		else Fillin(input, pos);
		
	}
	private void Fillin(char fill, int start){
		for(int j=start;j<leng;j++){
			char a=temp.charAt(j);
			if(a==fill&&GuessState[j*2]=='_'){
				GuessState[j*2]=fill;
				Unguess--;	
			}						
		}	
	}
	
	public Word(){
		int number = new Random().nextInt(10);
		//java.util.Random r=new java.util.Random();
		//num=r.nextInt(10);
		leng=words[number].length();
		Unguess=leng;
		temp=words[number].intern(); //���Ƶ���
		GuessState= new char[leng*2];
		for(int j=0;j<leng;j++){
			GuessState[j*2]='_';
		}
			
		}
}

public class GuessWord {

	public static void main(String[] args) {
		Word c= new Word();
		int Unguess=c.getUnguess();
		char[] guessState=c.getWord();
		//String word;
		//word=c.getWordtoGuess();	
		//System.out.println(word);
		Scanner scan= new Scanner(System.in);
		char input;
		System.out.println(guessState);
		while(Unguess>0){
			
			System.out.println("Please guess a letter from 'a' to 'z' or press'0' for a clue");
			
			input=scan.next().charAt(0);
			if(input=='0')c.RandomFill();
			else if(input<'a'||input>'z')System.out.println("Wrong input");
			else c.GuessDecision(input);
			System.out.println(guessState);
			Unguess=c.getUnguess();
			
		}
		System.out.println("Congudations! You have figured out all the letters");		
	scan.close();
		// TODO Auto-generated method stub

	}

}
