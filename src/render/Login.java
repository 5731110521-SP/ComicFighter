package render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.Player;
import input.InputUtility;
import logic.MyException;

public class Login extends JPanel{
	private JTextField tf;
	private JLabel l;
	private String Name;
	public static Player player1,player2;
	private int player;
	
	public Login(int i) {
//		super(new GridBagLayout());
		super(new BorderLayout());
		this.setPreferredSize(new Dimension(640,480));
		this.setBackground(Color.GRAY);
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		
		JPanel p0 = new JPanel();
		p0.setLayout(new FlowLayout(FlowLayout.CENTER));
		p0.setPreferredSize(new Dimension(640,80));
		p0.setBackground(Color.LIGHT_GRAY);
		JLabel login = new JLabel("LOGIN");
		login.setFont(new Font("Broadway",0,80));
		login.setForeground(Color.BLACK);
		p0.add(login);
		this.add(p0,BorderLayout.NORTH);
		
		Name="";
		l = new JLabel("Enter Player"+i+"'s name : ");
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Britannic Bold", 0, 30));
		tf = new JTextField("");
		tf.setFont(new Font("Tahoma", 0, 30));
		tf.setPreferredSize(new Dimension(300, 50));
		player=i;
		
		JPanel p1 = new JPanel();
		p1.setPreferredSize(new Dimension(640,160));
		p1.setLayout(new GridBagLayout());
		p1.setBackground(Color.GRAY);
		p1.add(l);
		p1.add(tf);
		this.add(p1,BorderLayout.CENTER);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.CENTER));
		p2.setPreferredSize(new Dimension(640,240));
		p2.setBackground(Color.GRAY);
		JLabel b = new JLabel();
		ImageIcon c = new ImageIcon();
		c.setImage(Resource.enter2);
		b.setIcon(c);
		p2.add(b);
		this.add(p2,BorderLayout.SOUTH);
		
//		add(l);
//		add(tf);
//		add(b);
		b.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				InputUtility.mouseLeftRelease();
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				InputUtility.mouseLeftDown();
				Resource.buttonSound.play();
			}
		});
		
		KeyListener k = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				InputUtility.setKeyPressed(arg0.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				InputUtility.setKeyPressed(arg0.getKeyCode(), true);
			}
		};
		
		this.addKeyListener(k);
		tf.addKeyListener(k);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.WHITE);
//		g2d.setFont(new Font("Tahoma", 0, 20));
//		FontMetrics fm = g2d.getFontMetrics();
//		Rectangle2D r2 = fm.getStringBounds("- Enter -", g2d);
//		g2d.drawString("- Enter -", GameScreen.width/2-(int)r2.getWidth()/2, GameScreen.height/2+80);
	}

	public boolean update() throws MyException{
		if(InputUtility.isLeftClickTriggered() || InputUtility.getKeyPressed(KeyEvent.VK_ENTER)){
			InputUtility.setKeyPressed(KeyEvent.VK_ENTER, false);
			InputUtility.updateInputState();
				if(tf.getText().equals("")){
					throw new MyException(this,l);
				}else{
//					setVisible(false);
					Name=tf.getText();
					if(player==1){
						player1=new Player(1,Name);
					}else{
						player2=new Player(2,Name);
					}
					return true;
				}
		}
		return false;
		
	}

	public String getName() {
		return Name;
	}
}
