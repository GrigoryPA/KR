import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Display2D {
	protected static int[][] display; 
	protected static int N=700; 
	protected static int M=500;
	private static int[] ZeroZero= {0,0};
	private static JFrame frame;
	private static JLabel label;
	
	public  Display2D() {
		display=new int[N][M];
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++) {
				display[i][j]=0;
			}
		return;
	}
	
	public static void AddPointOnDisplay(int x, int y, int color) {
		display[ZeroZero[0]+x][ZeroZero[1]+y]=color;
	}
	
	public void AddCoordinateAxes() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(i==0 || j==0) display[i][j]=1;
				if((i==1||i==2) && j%10==0) display[i][j]=1;
				if((j==1||j==2) && i%10==0) display[i][j]=1;
			}
		}
	}
	
	
	public void CreateAndOpenImage() {
		File file = new File("./src/convas.png");
		try {
			BufferedImage image = ImageIO.read(file);
			
			Color Color0 = new Color(0, 0, 0);
			Color Color1 = new Color(255, 255, 255);
			Color Color2 = new Color(50, 255, 50);
			
			for(int x=0;x<image.getWidth();x++) 
				for(int y=0;y<image.getHeight();y++) {
					if(display[x][M-y-1]==0)image.setRGB(x, y, Color0.getRGB());
					if(display[x][M-y-1]==1)image.setRGB(x, y, Color1.getRGB());
					if(display[x][M-y-1]==2)image.setRGB(x, y, Color2.getRGB());
			}
			
			AddOnFrame(image);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Не удалось открыть изображение!");
		}
		
	}
	
	
	public static void AddOnFrame(BufferedImage image){
	   if(frame==null){
	       frame=new JFrame();
	       frame.setTitle("stained_image");
	       frame.setSize(image.getWidth(), image.getHeight());
	       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	       label=new JLabel();
	       label.setIcon(new ImageIcon(image));
	       frame.getContentPane().add(label,BorderLayout.CENTER);
	       frame.setLocationRelativeTo(null);
	       frame.pack(); 
	       frame.setVisible(true);
	   }else label.setIcon(new ImageIcon(image));
	}
}
