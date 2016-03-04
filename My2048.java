import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

//PB11210041 毛翼婷
public class My2048 extends JFrame{
	private Button B[];
	private Label L[][];
	private int Nums[][];
	private int BlankLeft;
	private float MColor[];
	private Frame f;
	public My2048(){
		super("My2048_PB11210041");
		f=(Frame)this;
		MColor=new float[16];
		for(int i=0;i<16;i++)MColor[i]=(float)0.0625*(i+1);
		Nums=new int[4][4];
		BlankLeft=16;
		B=new Button[4];
		B[0]=new Button("Left");
		B[1]=new Button("Right");
		B[2]=new Button("Up");
		B[3]=new Button("Down");
		for(int i=0;i<4;i++)B[i].addActionListener(new listener());
		L=new Label[4][4];
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
			L[i][j]=new Label("",Label.CENTER);
			L[i][j].setBackground(Color.getHSBColor((float)0.13, (float)0, (float)0.9));
			L[i][j].setFont(new Font("Serif",Font.BOLD,28));
			//L[i].setSize(70,70);
			}
		
		Container c=getContentPane();
		c.setLayout(new BorderLayout(3,3));
		JPanel jp1=new JPanel();
		jp1.setLayout(new GridLayout(4,4,3,3));
		jp1.setBackground(Color.white);
		jp1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"2048"));
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			jp1.add(L[i][j]);
		JPanel jp3=new JPanel();
		jp3.setLayout(new GridLayout(3,5));
		for(int i=0;i<15;i++) {
			if(i==2)jp3.add(B[2]);
			else if(i==6)jp3.add(B[0]);
			else if(i==8)jp3.add(B[1]);
			else if(i==12)jp3.add(B[3]);
			else jp3.add(new Label(""));
		}
		jp3.setBackground(Color.lightGray);
		jp3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Operate"));
		c.add(jp1,BorderLayout.CENTER);
		c.add(jp3,BorderLayout.SOUTH);
		setSize(340,420);
		show();
		RamdGenerate();
		Display();
	}
	
	//结束时弹出对话框
	class NotiDlg implements ActionListener{
		Frame f = new Frame("Dialog owner");
		Dialog D;
		Label message=new Label("Game is Over");
		Button BRestart=new Button("Restart");
		Button BEnd=new Button("Exit");
		Panel p1=new Panel();
		Panel p2=new Panel();
		NotiDlg(Frame own)
		{
			BRestart.addActionListener(this);
			BEnd.addActionListener(this);
			D=new Dialog(own,"",true);
			p1.add(message);
			message.setFont(new Font("Serif",Font.PLAIN,21));
			p2.add(BRestart);
			p2.add(BEnd);
			D.add(p1,BorderLayout.CENTER);
			D.add(p2,BorderLayout.SOUTH);
			D.setSize(250,130);
			D.setVisible(true);
		}
		
		public void actionPerformed(ActionEvent e){
			D.dispose();
			if(e.getActionCommand()=="Restart"){
				BlankLeft=16;
				Nums=new int[4][4];	
				RamdGenerate();
				Display();
			}
			else
				System.exit(0);
		}
	}
	
	//执行用户移动操作
	private class listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			int change=0;
			if(e.getSource()==B[0]){
				for(int i=0;i<4;i++){
					int Fold=0;
					int temp=0;
					int No=0;
					int num;
					for(int j=0;j<4;j++){
						num=Nums[i][j];
						if(num!=0){
							if(temp==num&&Fold==0){
								Nums[i][No-1]=num+1;
								Fold=1;
								BlankLeft++;
								change=1;
							}
							else{
								temp=num;
								if(No!=j&&change==0)change=1;
								Nums[i][No]=temp;
								No++;
							}
						}
					}
					while(No<4){
						Nums[i][No]=0;
						No++;
					}
				}
			}
			if(e.getSource()==B[1]){
				for(int i=0;i<4;i++){
					int Fold=0;
					int temp=0;
					int No=3;
					int num;
					for(int j=3;j>-1;j--){
						num=Nums[i][j];
						if(num!=0){
							if(temp==num&&Fold==0){
								Nums[i][No+1]=num+1;
								Fold=1;
								BlankLeft++;
								change=1;
							}
							else{
								temp=num;
								if(No!=j&&change==0)change=1;
								Nums[i][No]=temp;
								No--;
							}
						}
					}
					while(No>-1){
						Nums[i][No]=0;
						No--;
					}
				}
			}
			if(e.getSource()==B[2]){
				for(int j=0;j<4;j++){
					int Fold=0;
					int temp=0;
					int No=0;
					int num;
					for(int i=0;i<4;i++){
						num=Nums[i][j];
						if(num!=0){
							if(temp==num&&Fold==0){
								Nums[No-1][j]=num+1;
								Fold=1;
								BlankLeft++;
								change=1;
							}
							else{
								temp=num;
								if(No!=i&&change==0)change=1;
								Nums[No][j]=temp;
								No++;
							}
						}
					}
					while(No<4){
						Nums[No][j]=0;
						No++;
					}
				}
			}
			if(e.getSource()==B[3]){
				for(int j=0;j<4;j++){
					int Fold=0;
					int temp=0;
					int No=3;
					int num;
					for(int i=3;i>-1;i--){
						num=Nums[i][j];
						if(num!=0){
							if(temp==num&&Fold==0){
								Nums[No+1][j]=num+1;
								Fold=1;
								BlankLeft++;
								change=1;
							}
							else{
								temp=num;
								if(No!=i&&change==0)change=1;
								Nums[No][j]=temp;
								No--;
							}
						}
					}
					while(No>-1){
						Nums[No][j]=0;
						No--;
					}
				}
			}
			//System.out.print("  "+BlankLeft);
			
			if(BlankLeft>0&&change==1)RamdGenerate();	
			Display();
			if(BlankLeft==0)checkEnd();
		}
	}
	
	//判断是否结束游戏
	private void checkEnd(){
		int a=0;
		
		for(int i=0;i<4;i++){
			if(a==1)break;
			int temp=0;
			for(int j=0; j<4;j++)
			{
				if(Nums[i][j]==temp){
					a=1;
					break;
				}
				temp=Nums[i][j];
			}
		}
		for(int j=0;j<4;j++){
			if(a==1)break;
			int temp=0;
			for(int i=0; i<4;i++)
			{
				if(Nums[i][j]==temp){
					a=1;
					break;
				}
				temp=Nums[i][j];
			}
		}
		if(a==0){
			NotiDlg noti=new NotiDlg(f);
		}
	}
	
	//更新显示
	private void Display(){ 
		int a;
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++){
				a=Nums[i][j];
				if(a==0){
					L[i][j].setText("");
					L[i][j].setBackground(Color.getHSBColor((float)0.13, 0, (float)0.96));
				}
				else{ 
					L[i][j].setText(""+(int)Math.pow(2, a));
				    L[i][j].setBackground(Color.getHSBColor((float)0.13, MColor[a], (float)0.95));
				}
			}
	}
	
	//随机产生2或4
	private void RamdGenerate(){
		int RNum,RPosition,round,state;
        double ranNum=Math.random(); //产生0-1的随机数
        if(ranNum<0.9)RNum=1; 
        else RNum=2;
		RPosition=new Random().nextInt(BlankLeft); //产生0到BlankLeft-1的整数
		RPosition++;
		round=0;
		state=0;
		for(int i=0;i<4;i++){ //第RPosition个空格处放随机数RNum
			if(state==1)break;
			for(int j=0;j<4;j++){
				if(Nums[i][j]==0)round++;
				if(RPosition==round){
					Nums[i][j]=RNum;
					state=1;
					BlankLeft--;
					break;
					
				}
			}	
		}
		
	}
	public static void main(String args[]){
		My2048 jp=new My2048();
		jp.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
}

