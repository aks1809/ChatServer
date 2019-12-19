import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements WindowListener,KeyListener,ActionListener{
Frame f;
List l;
TextField t;
Button b;
String str;
Socket s;
int flag=0;
static ServerSocket ss;
public Server() {
	// TODO Auto-generated constructor stub
	f=new Frame("Server");
	l=new List();
	b=new Button("Send");
	t=new TextField();
	f.setLayout(null);
	f.add(b);
	f.add(t);
	f.add(l);
	t.addKeyListener(this);
	f.addWindowListener(this);
	b.addActionListener(this);
	l.setBounds(10, 40, 380, 290);
	t.setBounds(10, 340, 300, 50);
	b.setBounds(320, 340,70,50);
	f.setResizable(false);
	f.setSize(400,400);
	f.setVisible(true);
	try {
		ss=new ServerSocket(1600);
		s=ss.accept();
		while(true){
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			str=ois.readObject().toString();
			if(l.getItemCount()==0) {
				l.add(str);
			}	
			else {
				if(l.getItem(l.getItemCount()-1).compareTo("")==0||l.getItem(l.getItemCount()-1).compareTo("Typing...")==0)
					l.remove(l.getItemCount()-1);
				if(str.compareTo("")==0||str.compareTo("Typing...")==0)
					l.add(str);
				else
					l.add("Friend:-"+str);
			}
		}
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e.getMessage());
	}
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			if(t.getText().length()>0)
				oos.writeObject("Typing...");
			else
				oos.writeObject("");
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2.getMessage());
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		try {
			Server.ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(t.getText());
			if(l.getItemCount()>0) {
				if(l.getItem(l.getItemCount()-1).compareTo("")==0||l.getItem(l.getItemCount()-1).compareTo("Typing...")==0) {
					l.remove(l.getItemCount()-1);
					l.add("Me:-"+t.getText());
					l.add(str);
				}
				else
					l.add("Me:-"+t.getText());
			}
			else {
				l.add(str);
			}
			t.setText("");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
